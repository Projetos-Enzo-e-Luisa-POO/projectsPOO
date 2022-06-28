
## Graphic Interface

Constructed with [libGDX](https://libgdx.com/) opensource framework.

## Project Architecture

Defined by libGDX pattern, in game creation phase:

```
settings.gradle            <- definition of sub-modules. In this caso, we have just: desktop
build.gradle               <- main Gradle build file, defines dependencies and plugins
gradlew                    <- local Gradle wrapper
gradlew.bat                <- script that will run Gradle on Windows
gradle                     <- script that will run Gradle on Unix systems

assets/                    <- contains graphics, images, audio, etc.

core/
    build.gradle           <- Gradle build file for core project
    src/                   <- Source folder for all game's code

desktop/
    build.gradle           <- Gradle build file for desktop project
    src/                   <- Source folder for desktop project, contains LWJGL launcher class
```
# Amnesia

# Estrutura de Arquivos e Pastas

~~~
├── README.md          <- apresentação do projeto
│
├── data               <- dados usados pelo jogo
│
├── src                <- projeto em Java (preferencialmente projeto no Eclipse)
│   │
│   ...
│   │
│   ├── src            <- arquivos-fonte do projeto (.java)
│   │
│   ├── bin            <- arquivos em bytecode (.class)
│   │
│   └── README.md      <- instruções básicas de instalação/execução
│
└── assets             <- mídias usadas no projeto
~~~

Na raiz deve haver um arquivo de nome `README.md` contendo a apresentação do projeto, como detalhado na seção seguinte.

## `data`
* Room1.csv: Arquivo com as informações necessárias para a construção do cenário estático da sala
* KeyBindValues.csv: Arquivo com as binds configuradas pelo jogador no menu de settings (vem com valores default)
* ResetSaveFile: Arquivo com as informações necessárias para se criar um novo jogo
* SaveFile.csv: Arquivo usado na inicialização de flags e interagíveis no jogo

## `notebooks`

Testes ou prototipos relacionados ao projeto que tenham sido executados no Jupyter. Por exemplo, coloque aqui uma cópia dos testes feitos nas Exceptions.

## `src`

Projeto em Java, preferencialmente em Eclipse, incluindo todos os arquivos de dados e bibliotecas necessários para a sua execução. Dentro dessa pasta sugerimos que você mantenha uma estrutura equivalente ao Eclipse, com uma subpasta `src` onde estarão os fontes e outra subpasta `bin` onde estarão os bytecodes.

 Acrescente na raiz um arquivo `README.md` com as instruções básicas de instalação e execução.

## `assets`

* Imagens para exibição relativas a cada objeto visível em seus diferentes estados
* Arquivos necessários para usar fonte customizada dentro de jogo

# Modelo para Apresentação do Projeto

# Projeto `<Amnésia>`

# Descrição Resumida do Projeto/Jogo

> Em Amnésia, o jogador é desafiado a concluir puzzles baseados em abstração e interações. Investigue objetos desconhecidos, interaja com eles e altere todo o ambiente ao seu redor na tentativa de descobrir o que aconteceu e reaprender do zero tudo que esqueceu!

# Equipe
* `<Enzo Guimarães Campos>` - `<247069>`
* `<Luísa Freitas Colafati>` - `<247249>`

# Arquivo Executável do Jogo

> [Amnésia.jar](https://github.com/Projetos-Enzo-e-Luisa-POO/projectsPOO/blob/main/amnesia/amnesia.jar)

# Slides do Projeto

## Slides da Prévia
> [Prévia](https://docs.google.com/presentation/d/1KBlFpWyvK7-cOQYOfY1X7e4mozWhmyj8pmOMxseyHHM/edit?usp=sharing)

## Slides da Apresentação Final
> [Apresentação final](https://docs.google.com/presentation/d/1OaMNmC3FvYEbifLXxK6CaPXF228vnRcS-5bQyYWqcU8/edit?usp=sharing)

# Diagramas

## Diagrama Geral da Arquitetura do Jogo

> Apresente um diagrama geral da arquitetura do jogo. O formato é livre. A escolha de um ou mais estilos arquiteturais será considerado um diferencial.
