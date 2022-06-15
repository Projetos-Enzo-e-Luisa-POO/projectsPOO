/*	Elemento básico do jogo
 * Superclasse mais genérica, define um Elemento básico do jogo.
 * Todo elemento possui um ID:
 * - Elementos vazios (Void) possuem ID 0.
 * - O protagonista possui ID 1.
 * - Células possuem ID da forma 2n, n>0 (2,4,6,8,10,12...)
 *	 sendo n a posição relativa da célula na matriz da sala (da direita para a esquerda, de baixo pra cima, começando em 1)
 * - Elementos interagíveis possuem ID resultante de um produto de números primos p>2 (3,5,7,11...,15,21,33,35...)
	 sendo os primos respectivos às interfaces que o Elemento implementa
 * - Elementos decorativos da sala possuem id menor que 0, sendo que devem ser sobrepostos em ordem descrescente (-1 primeiro, -2 em seguida, etc)     
 * - Demais elementos possuem IDs arbitrários
 */
public class Element {

	protected int ID;

	public int getID() {
		return this.ID;
	}
    
}