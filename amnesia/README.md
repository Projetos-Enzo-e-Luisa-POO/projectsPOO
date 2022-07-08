# Projeto `Amnésia`

# Descrição Resumida do Projeto/Jogo

Amnésia é um jogo single player do tipo escape room. Nele, o jogador é desafiado a concluir puzzles baseados em abstrações e interações com objetos inicialmente desconhecidos dispostos numa sala.

Conforme o jogador investiga esses objetos, ele adquire as interfaces necessárias para interagir com eles e, a partir disso, é possível inferir a ordem correta de interações a serem feitas para que o puzzle da sala seja concluído.

Quando isso acontece, o jogador avança para a próxima sala do jogo e segue nesse processo de aprendizado contínuo!

# Equipe
* `Enzo Guimarães Campos` - `247069`
* `Luísa Freitas Colafati` - `247249`

# Arquivo Executável do Jogo

> [amnesia.jar](https://github.com/Projetos-Enzo-e-Luisa-POO/projectsPOO/blob/main/amnesia/amnesia.jar)

# Slides do Projeto

## Slides da Prévia
> [Apresentação Prévia](https://docs.google.com/presentation/d/1KBlFpWyvK7-cOQYOfY1X7e4mozWhmyj8pmOMxseyHHM/edit?usp=sharing)

## Slides da Apresentação Final
> [Apresentação Final](https://docs.google.com/presentation/d/1OaMNmC3FvYEbifLXxK6CaPXF228vnRcS-5bQyYWqcU8/edit?usp=sharing)

# Relatório de Evolução

## Nascimento da ideia do jogo

A ideia do jogo originou-se num brainstorm a respeito de conceitos básicos de Orientação de Objetos (OO), como abstração e interfaces, e de como poderíamos ilustrá-los num jogo lúdico e educativo.

Inicialmente, pensamos em desenvolver três salas/puzzles que abusavam das várias possibilidades que a arquitetura planejada ofereceria: criação de vários objetos e interfaces; definição de diferentes objetivos e caminhos a serem seguidos numa mesma sala; “adaptabilidade” do jogo para dar ao player a liberdade de escolher como ele desejaria interagir com o ambiente ao seu redor.

## Planejamento da arquitetura e da interface gráfica

Após algumas pesquisas iniciais, também decidimos usar o framework LibGDX no desenvolvimento do projeto. Mas como ele era desconhecido para nossa dupla e nós ainda não tínhamos praticado a construção de interfaces gráficas em Java, achamos mais interessante investir na construção de uma arquitetura mais focada no backend e no funcionamento interno do jogo.

Desde as primeiras reuniões de planejamento de arquitetura, definimos que nossas principais features seriam:
- Inicialização do jogo a partir de arquivos .csv;
- Customização de binds (vínculos entre teclas do teclado e comandos do jogo) num menu inicial;
- Implementação de uma função de Save do estado do jogo, que acarretaria a criação de um banco de flags que armazenaria as configurações atuais do jogo num arquivo .csv, a ser usado na inicialização do jogo;
- Implementação de um observador geral do jogo chamado “StoryTeller”, que seria responsável pela progressão do jogo, interações com o jogador e transições entre salas;
- Implementação da arquitetura MVC, com ênfase no Model e nas diversas interfaces que cada componente interagível da sala implementaria, bem como nas suas interações com o protagonista e/ou sala do jogo

### Primeiro diagrama geral do funcionamento do jogo

![Primeiro diagrama de fucionamento do projeto](assets/README/arquiteturaInicial.jpeg)

## Primeiras dificuldades

### *Interface gráfica*

Logo nos deparamos com o primeiro impasse no desenvolvimento do projeto: nosso Model estava praticamente pronto, mas não conseguíamos testá-lo pois ainda não tínhamos estruturado o básico da interface gráfica do jogo.

Nesse ponto, voltamos nossas atenções para o frontend. Percebendo que levaria um bom tempo para aprendermos funcionalidades mais avançadas do framework libGDX, optamos por usar seus recursos mais simples, que já havíamos estudado, para construirmos as interfaces gráficas mínimas necessárias para que o jogo fosse executável e testável.

Foi aí que surgiu a estruturação das classes Curtain, Menu, Settings, Loading e Level, que chamamos internamente de “framework backbone" (“espinha dorsal do framework”).

### Diagrama de herança e relações dos primeiros elementos implementados no Model

--

### *Complexidade das salas do jogo*

Ao mesmo tempo, percebemos que a criação de uma sala despendia muito tempo, entre criação de interfaces, elementos interagíveis e, principalmente, as imagens características de cada objeto/elemento do jogo que decidimos fazer a mão, a fim de agregarmos valor à customização e exclusividade de nossa interface gráfica.

Decidimos, então, focar nossos esforços na construção de uma sala “modelo”, que serviria somente para colocarmos em prática todos os recursos implementados. Isto é, ela foi nossa “proof of concept” (POC) sobre a arquitetura planejada para o jogo.

## Features adaptadas e/ou não finalizadas completamente

### *Leitura e escrita de arquivos*

Num primeiro momento, implementamos métodos de algumas classes nativas do Java em nossa própria classe de leitura e escrita de arquivos.

Dias depois, no entanto, descobrimos que o framework libGDX também possui funções nativas para fazer a leitura e escrita de arquivos, de mais fácil implementação que os métodos do Java - a quantidade de linhas de código foi reduzida consideravelmente, sem contar que as funções do libGDX já tratam exceções que podem surgir duma manipulação indevida de arquivos, retornando uma excpection customizada (GdxRuntimeException).

Por isso, acabamos refatorando nossos códigos de leitura e escrita de arquivo para substituirmos as classes nativas do Java pelas funções do libGDX - retrabalho que nos tomou tempo útil de desenvolvimento.

### *Dimensionamento de imagens*

Ao longo do desenvolvimento, também tivemos retrabalhos na renderização de imagens do jogo. Tivemos que testar diferentes formas de exibí-las na tela até que encontrássemos uma que nos permitisse ajustar as imagens às dimensões dinâmicas da tela do jogo, que mudavam a cada resize de janela.

### *"Problema do cast"*
Sem dúvidas, esse foi nosso maior desafio, cuja solução se deu através da criação de uma classe castCenter, detalhada no destaque respectivo

### *Ausência da função de Save do jogo*

Conseguimos implantar a configuração de Binds na tela de Settings acessada via Menu principal, que permitiu a alteração de binds do jogo.

Entretando acabamos não conseguindo completar a função de save do estado atual do jogo, para que ele pudesse ser recuperado numa próxima partida. Na verdade, chegamos a desenvolver grande parte dessa função, mas não tivemos tempo suficiente para registrar o inventário do jogo em nosso banco de flags, nem para salvarmos uma lista com as interfaces descobertas pelo player.

# Conclusões e Trabalhos Futuros

Nossa maior lição foi perceber a importância de manejar melhor nosso tempo, bem como de adaptar melhor nossas ideias para o tempo disponível de desenvolvimento do projeto.

Ter consciência e humildade para reconhecer nossas limitações técnicas também é de suma importância para o projeto. Saber exatamente quais são as ferramentas disponíveis para seu desenvolvimento desde o início direciona melhor o pensamento arquitetural, permitindo a estruturação detalhada dos componentes do projeto ou, ainda, a consideração da curva de aprendizado necessária para que consigamos usar uma nova ferramenta no projeto.

# Diagramas finais

Ao final do projeto, fizemos dois diagramas contemplando toda a arquitetura implementada.

O primeiro deles mostra as relações de herança e implementação de interfaces no jogo:

<inserir diagrama aqui>

Já o segundo mostra as relações entre componentes, sendo ele a versão completa dos diagramas apresentados nas seções finais:

<inserir diagrama aqui>

Recomendamos o acesso a esses diagramas apenas para consultas direcionadas em que você queira saber especificamente de que classes uma determinada subclasse herda, ou então com que classes/interfaces outra classe se relaciona.

Falamos isso porque, dada a complexidade do projeto, seus diagramas finais acabaram ficando bem poluídos.
  
# Destaques de código
  
> Entremos agora em maiores detalhes sobre algumas implementações específicas

## Leitor de arquivos e tratamento de exceções

Como explicado anteriormente nesta documentação, optamos por implementar funções nativas do libGDX que fazem a leitura e escrita de arquivos em nossa classe de manipulação de arquivos .csv chamada ```FileManagment```.

Como as funções do libGDX retornam uma exception customizada (```GdxRuntimeException```) caso o libGDX tenha problemas no acesso e/ou manipulação de um arquivo, nós também decidimos criar exceptions customizadas com mensagens mais explicativas sobre o tipo de problema identificado pelo libGDX, a depender do tipo de ação que tentamos executar com um dado arquivo.

Ao todo, foram criadas 3 exceptions customizadas: ambas extendem a classe Excpetion, nativa do Java, e chamam o construtor da classe Excpetion, passando uma mensagem personalizada sobre o erro como parâmetro, em seus próprios construtores.

Tal estrutura está exemplificada nos seguintes trechos de código referentes:

1. Método ```getFileContent``` da classe ```FileManagment```, que lê e retorna o conteúdo de um arquivo separado por vírgulas:

![Função de leitura de arquivos](assets/README/funcaoLeituraArquivo.png)

2. Exception customizada disparada em caso de erros na execução do método ```getFileContent```:

![Tratamento de exceções](assets/README/tratamentoErro.png)

## Settings e BindDepot

No menu inicial do jogo, temos uma tela de configurações de binds (Settings), que mostra uma lista com todos os comandos de jogo disponíveis e o valor atual que estamos associando a ele, o qual, na prática, é uma tecla do teclado que irá acionar um determinado comando do jogo durante sua execução (chamamos esse vínculo de bind).

![Tela de configurações de binds](assets/README/settingsScreen.png)

Todas essas informações estão sendo lidas do nosso arquivo ```KeyBindValues.csv``` através da classe ```BindDepot```.

Além disso, temos caixas de texto disponíveis na tela para que o jogador possa alterar uma bind do jogo. Caso ele faça uma alteração e pressione a tecla escape, que é a tecla padrão que deve ser usada para sair da tela de Settings e voltar ao menu inicial do jogo, acionamos uma outra classe chamada ```BindConfig``` para salvar as alterações de binds no arquivo ```KeyBindValues.csv```.

Vale também lembrar que esse arquivo é usado durante a execução do jogo na classe ```Level```, para verificarmos se uma tecla digitada pelo usuário está no arquivo ```KeyBindValues.csv``` e, portanto, é uma bind do jogo.

Então toda alteração de binds feita da tela de Settings será automaticamente refletida na próxima partida do jogo. 

## castCenter

### Problema do cast

A proposta do Amnesia gira em torno da ideia de aprendizado progressivo do protagonista do jogo a respeito de interfaces necessárias para que ele interaja com elementos inicialmente desconhecidos espalhados pelas salas do jogo.

Trata-se de uma ideia abstrata e genérica que vinha com um problema: como chamar um método de assinatura desconhecida na interface genérica que deveria conter um elemento na matriz da sala?

Após ponderar manter uma classe-memória com um vetor de objetos do jogo, cada um com suas classes e métodos específicos, entendemos que o tamanho desse vetor e a complexidade dessa classe tenderiam a crescer exponencialmente com as expansões do jogo.

Por isso, achamos melhor fazer os casts de modo controlado e centralizado, e o castCenter foi a materialização dessa solução.

### Solução

Através de uma string “action” que contém o nome do método desejado, declarado por uma interface de nome previsível (action + “I”), e de sobrecarga de métodos, pudemos prever diferentes assinaturas de métodos genéricos no que diz respeito a parâmetros e tipos de retorno.

Com isso, o castCenter conseguiu buscar o método na interface desejada, e fazer um cast genérico para qualquer classe, prevendo os possíveis erros caso um método incoerente para a interface ou objeto em questão fosse chamado.

### Exemplo de método sobrecarregado do castCenter, onde o método “action” retorna um Interactable e não tem nenhum outro parâmetro

~~~java
public class castCenter {
    …
    public Interactable act(Interactable item, String action) {
        Class<?> cRoot, cInterface;
        Method method;
        Interactable aux = null;
        try {
            cRoot = Class.forName("com.badlogic.amnesia.Model.Elements.ViewElement.Interactables." + item.getClassName());
            cInterface = Class.forName("com.badlogic.amnesia.Model.Elements.ViewElement.Interactables.Interfaces." + action + "I");
            method = cRoot.getDeclaredMethod(action);
            aux = (Interactable) method.invoke(cInterface.cast(cRoot.cast(item)));
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | 
                IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return aux;
    }
}
~~~

# Destaques de Orientação a Objetos

## Encapsulamento da Room

### *Contexto de aplicação*

Prezamos pelo bom encapsulamento dos componentes no código, e um ótimo exemplo desse cuidado é a Classe Room, que implementa quatro diferentes interfaces que fornecem os métodos necessários para cada tipo de requisição que outras classes possam pedir, separados por objetivo. Segue o diagrama de classes que explica melhor a construção:

### Diagrama de Classes: Room e interfaces
![Encapsulamento da Room](assets/README/encapsulamentoRoom.png)

## Alguns trechos das interfaces e classes que usam os métodos encapsulados e suas implementações na Room
~~~java
public interface MoveAccess{
    …
    public boolean isWalkable(int ID);
}
~~~
~~~java
public interface RenderAccess {
    public RenderStrategy[][] getCells();
}
~~~
~~~java
public class Room implements MoveAccess, ControlAccess, RenderAccess {

    private Cell[][] space;
    …
    public boolean isWalkable(int cellID){	
        IDTrans t = new IDTrans();
  	    int aux[] = t.IDToPos(cellID);
  	    return this.space[aux[0]][aux[1]].isWalkable();
    }
    …
    @Override
    public RenderStrategy[][] getCells() {
  	    return this.space;
    }
}
~~~
~~~java
public class Movable extends Element {
    …
    protected MoveAccess space;
    …
    public void roomConect(MoveAccess a){
  	    this.space = a;
    }
    …
    protected void moveToCell(int cellID) {
  	    if(this.space.isWalkable(cellID)) this.posID = cellID;
    }
    …
}
~~~
~~~java
public class Level implements Screen {
    …
    private static RenderAccess room;
    …
    public Level(..., RenderAccess room, ...) {
        …
        Level.room = room;
        …
    }
    private void Setup() {
        …
        for (RenderStrategy[] collumn : Level.room.getCells()) {
            for (RenderStrategy c : collumn) {
                c.render(this.batch, this.getImageSize());
            }
        }
        …
    }
    …
}
~~~

## Herança nos elementos da sala (comentar ID)

### *Contexto de aplicação*

Usamos a Herança para servir duas funções: Reúso organizado de código e Delegação de funções que mais de uma classe pode desejar possuir. Para o primeiro objetivo um excelente exemplo é a classe Element, que implementa somente a lógica de possuir um ID próprio, o qual todos os demais elementos possuem, para diversos propósitos, desde identificar o elemento na criação de sala a partir do arquivo .csv, até codificar a posição de uma célula na sala, codificação essa feita pela classe IDTrans. Para o segundo objetivo, exemplificamos a classe Movable, que implementa o movimento de um elemento no espaço celular, cuja função pode ser reutilizada caso queiramos implementar um novo elemento que também se move na sala. Também deixa as classes herdeiras mais simples, com métodos focados em suas implementações específicas.

### Exemplo de indexação e cálculo de IDs das células de uma sala
~~~
26  28  30  32
18  20  22  24
10  12  14  16
2   4   6   8

matriz[1][2] = ID 20 = 2 * (1 + 4 * 2 + 1)
ID 20 = matriz[1][2] = matriz[((20 / 2) - 1) % 4][((20 / 2) - 1) / 4]
~~~

### Diagrama de Classes: Herança dos Elementos

![Herança nos elementos da sala](assets/README/heranca.png)

## Excertos de código das classes Element, IDTrans, Movable e Songster
~~~java
public class Element {

    protected int ID;

    public int getID() {
        return this.ID;
    }
}
~~~
~~~java
public class IDTrans { //IDTrans.l é a largura da sala, atributo estático da classe.
    …
    public int posToID(int[] pos){
        return 2 * (pos[0] + IDTrans.l * pos[1] + 1);
    }
    public int[] IDToPos(int ID){
        int[] aux = { ((ID / 2) - 1) % IDTrans.l, (((ID / 2) - 1) / IDTrans.l)};
        return aux;
    }
}

~~~
~~~java
public class Movable extends Element {
    protected boolean[] orientation = new boolean[4];
    …
    protected void orientate(int direction) {
        for (int i = 0; i < 4; i++)
            this.orientation[i] = false;
        this.orientation[direction] = true;
    }
    …
}
~~~
~~~java
public class Songster extends MovableViewElement{
    …
    @Override
    public void move(int direction, int cellID) {
        super.move(direction, cellID);
        this.f.setSongsterOrientation(this.getOrientation());
        …
    }
}
~~~

# Destaques de Pattern

## Facade

### *Contexto de aplicação*

Usamos o Facade para centralizar na classe MPControl todos os métodos do Model que, de fato, executam um comando do jogo. Nesse sentido, é de responsabilidade do MPControl buscar tais métodos nas classes que representam o protagonista e a sala do jogo.

Então, resta ao Level (nossa classe de interface gráfica) apenas renderizar as imagens do jogo na tela e monitorar as teclas de teclado digitadas pelo jogador.

Caso ele detecte o clique numa bind do jogo, o Level irá apenas delegar a execução do comando associado a essa bind ao MPControl, como ilustra o diagrama abaixo:

![Contexto de aplicação do facade](assets/README/facade.png)

### *Exemplo de código*

## Singleton

### *Contexto de aplicação*

Usado nas seguintes classes de inicialização do jogo, a fim de que elas fossem instaciadas somente uma vez e depois reutilizadas a cada nova partida e/ou construção de uma nova sala durante a partida atual:
- BindDepot
- FlagDepot
- RoomBuilder

### *Exemplo de código*

![Singleton](assets/README/singleton.png)

## Strategy

### *Contexto de aplicação*

Achamos mais sucinto adotar o Strategy para a renderização dos elementos da sala, uma vez que cada um seria renderizado de acordo com características próprias, imagens sobrepostas, ou dependendo da orientação uma imagem diferente, etc. Seguem os elementos do Model que sabem renderizar-se e alguns exemplos de funções.

![Contexto de aplicação do strategy](assets/README/strategy2.png)

## Interface e Alguns métodos render
~~~java
public interface RenderStrategy {
    public void render(Batch batch, float imgSize);
}
~~~
~~~java
public class CompondViewElement extends Element implements RenderStrategy {

    protected ArrayList<String> imgs;
    …
    public void render(Batch batch, float imgSize){
        IDTrans t = new IDTrans();
        for (String img : this.getImgs()) {
            int[] cellPosition = t.IDToPos(this.ID);
            batch.draw(new Texture(
                                    Gdx.files.internal(img)),
                                    cellPosition[0] * imgSize,
                                    cellPosition[1] * imgSize,
                                    imgSize,
                                    imgSize
                                    );
        }
    }
}
~~~
~~~java
public abstract class InteractableElement extends ViewElement implements Interactable {

    protected int posID;
    protected boolean known;
    …
    @Override
    public void render(Batch batch, float imgSize){
        IDTrans t = new IDTrans();
        int[] cellPosition = t.IDToPos(this.posID);
        batch.draw(new Texture(
                                ((this.known) ? Gdx.files.internal(this.getImg()) : Gdx.files.internal("concreteElement/unknownplainobject.png"))),	
                                cellPosition[0] * imgSize,
                                cellPosition[1] * imgSize,
                                imgSize,
                                imgSize
                                );
    }
}
~~~

Decidimos que o método teria assinatura “void render(batch, float)” pois assim cada elemento teria acesso ao batch, responsável por controlar a comunicação com a GPU e um float que constantemente é atualizado em função do tamanho da janela de modo que toda a tela do jogo adapta-se ao tamanho da janela. O pattern se valoriza nas diferentes implementações de métodos de renderização dentro de cada elemento, que conhece sua própria imagem, além das particularidades de cada um, sendo (a partir do diagrama): O protagonista deve saber qual sua orientação e exibir uma imagem correspondente; O inventário deve exibir sua imagem, a depender de qual compartimento está selecionado e caso contenha um objeto, exibir também sua imagem; Os elementos interagíveis devem exibir suas imagens a depender de seus estados e particularidades (aceso ou apagado, ligado ou desligado, etc) e a Célula deve renderizar os elementos contidos nela em ordem, sobrepostos (lajota, mesa, etc).

## Menções honrosas: Memento, Factory e Observer

> Decidimos comentar na documentação sobre alguns patterns que não foram concluídos ou que não estruturamos como pattern por completo, seja por falta de tempo, ou por termos implementado conhecendo pouco a respeito do pattern em si. Passemos brevemente por cada um deles:

### Memento

### *Contexto de aplicação*

Para a função de save, nossa idéia original era armazenar todas as informações necessárias para restaurar o jogo a um determinado ponto na classe FlagDepot, que armazena a sala atual, a posição e orientação do protagonista, e informações sobre cada um dos elementos interagíveis da sala, a saber, seu ID, sua posição e um vetor de booleanas com seus estados, e por fim, informações do inventário do protagonista (não implementada). Logo surge a questão de como armazenar as informações dos interagíveis da sala para geração de um arquivo .csv, e o pattern Memento logo saltou aos olhos. Gerar uma classe que contém o último estado dos elementos a serem registrados era exatamente nossa necessidade, e o pattern foi aplicado, mas a feature não chegou à interface gráfica por muito pouco, faltando somente criar um memento para o inventário e adaptar a criação de sala para considerar as informações por ele geradas e armazenadas.

### Diagrama para a geração de Save implementada

--

### ElementMemento e FlagDepot
~~~java
public class ElementMemento {
    private int ID;
    private int posID;
    private boolean[] status;

    public ElementMemento(int ID, int posID, boolean[] stauts){
        this.ID = ID;
        this.posID = posID;
        this.status = stauts;
    }

    public String toString(){
        return this.ID + ',' + this.posID + ',' + this.status.toString().substring(1,this.status.toString().length() - 1) + ',';
    }
}
~~~
~~~java
public class FlagDepot implements RoomFlag, FlagRead, SongsterFlags, ElementFlags{
    private int roomNumber;
    private int songsterPos;
    private int songsterOrientation;
    private ArrayList<ElementMemento> status = new ArrayList<ElementMemento>();
    …
    private void mementoFactory(Interactable[][] elements){
        for (int i = 0; i < elements.length; i++)
            for (int j = 0; j < elements[i].length; j++)
                if (elements[i][j] != null){
                    IDTrans t = new IDTrans();
                    Interactable a = elements[i][j];
                    int[] pos = {i, j};
                    this.status.add(new ElementMemento(a.getID(), t.posToID(pos), a.getStatus()));
                }
    }

    public String toString(Interactable[][] elements){
        this.mementoFactory(elements);
        return this.roomNumber + ',' + this.songsterPos + ',' + this.songsterOrientation + ',' + this.status.toString();
    }
~~~

### Factory

### *Contexto de aplicação*

Citando também o método acima exemplificado, em determinados momentos, vimos a necessidade de um método que instancia vários outros objetos de uma só vez a partir de um determinado critério (ou não, como vimos acima). Para isso chamamos Factory, que se assemelha ao pattern, mas por não termos nos aprofundado muito no mesmo, não nos preocupamos em garantir a genericidade e reusabilidade dos métodos, mas não deixa de ser notável a similaridade entre eles. São três os métodos: mementoFactory, placeableFactory e interactableFactory, os dois últimos, contidos na classe RoomBuilder, que será exposta nesta seção.

### Classes e assinatura dos métodos

--

### Exemplo: interactableFactory
~~~java
public class RoomBuilder {
    private ElementFlags ef;
    private StoryTeller st;
    …
    private Interactable interactableFactory(int n, int posID, Boolean[] state){
        Interactable aux = null;
        switch(n){
            /*
             * 13: light switch 1
             * 15: Table socket 1
             * 17: Lamp Bulb 1
             */
            case 13:
                aux = new LightSwitch(state, posID, this.ef);
                break;
            case 15:
                if (state[0]) aux = new TableSocket(new LampBulb(false, posID), posID, this.ef, this.st);
                else aux = new TableSocket(state, posID, this.ef, this.st);
                break;
            case 17:
                aux = new LampBulb(state, posID);
                break;
            }
            return aux;
    }
}
~~~

### Observer

### *Contexto de aplicação*

Tendo um banco de flags atualizado sobre o estado do jogo, surgiu a ideia de um agente independente das ações do jogo mas que influenciaria no que o jogador veria e teria disponível para interagir, e esse seria o nosso StoryTeller, responsável por dialogar com o jogador, verificar se o protagonista já terminou o puzzle da sala e notificar a necessidade de renderizar a próxima sala, entre outras funções que deixariam o jogo mais imersivo. Para tal, o pattern Observer é ideal e sempre foi nosso alvo, pois sempre que um elemento da sala alterasse a flag respectiva a si, o StoryTeller perceberia, e daria os triggers necessários para seguir contando a história do jogo. Entretanto, com nossa decisão de manter somente uma sala de demonstração, a história do jogo em si não tinha mais tanta proporção quanto antes, e por isso não dedicamos tanto esforço no StoryTeller quanto era planejado inicialmente.

### Comunicação Implementada Level-StoryTeller-Flags

--

### StoryTeller
~~~java
public class StoryTeller implements connectOverload{
    private ElementFlags ef = FlagDepot.getInstance();
    …
    public boolean checkGame(){
        boolean aux = false;
        if (this.ef.getSwitchFlag() && this.ef.getLampScrewedFlag()){
            this.ts.illuminate();
            aux = true;
        }
        else {
            this.ts.unilluminate();
        }
        return aux;
    }
    …
}
~~~

# Documentação dos Componentes

## Diagrama Geral da Arquitetura do Jogo

![Diagrama de classes jogo](assets/README/arquitetura.png)

## Diagrama Geral de Componentes

> Se você adotou componentes de software, apresente a documentação de componentes conforme o modelo.

## Componente `<Nome do Componente>`

> Resumo do papel do componente e serviços que ele oferece.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

~~~
<Interface em Java.>
~~~

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Exemplo:

### Interface `ITableProducer`

Interface provida por qualquer fonte de dados que os forneça na forma de uma tabela.

~~~java
public interface ITableProducer {
  String[] requestAttributes();
  String[][] requestInstances();
}
~~~

Método | Objetivo
-------| --------
`requestAttributes` | Retorna um vetor com o nome de todos os atributos (colunas) da tabela.
`requestInstances` | Retorna uma matriz em que cada linha representa uma instância e cada coluna o valor do respectivo atributo (a ordem dos atributos é a mesma daquela fornecida por `requestAttributes`.

### Interface `IDataSetProperties`

Define o recurso (usualmente o caminho para um arquivo em disco) que é a fonte de dados.

~~~java
public interface IDataSetProperties {
  public String getDataSource();
  public void setDataSource(String dataSource);
}
~~~

Método | Objetivo
-------| --------
`getDataSource` | Retorna o caminho da fonte de dados.
`setDataSource` | Define o caminho da fonte de dados, informado através do parâmetro `dataSource`.

# Plano de Exceções

## Diagrama da hierarquia de exceções
> Elabore um diagrama com a hierarquia de exceções como detalhado a seguir.

![Hierarquia Exceções](exception-hierarchy.png)

## Descrição das classes de exceção

> Monte uma tabela descritiva seguindo o exemplo:

Classe | Descrição
----- | -----
DivisaoInvalida | Engloba todas as exceções de divisões não aceitas.
DivisaoInutil | Indica que a divisão por 1 é inútil.
DivisaoNaoInteira | Indica uma divisão não inteira.
