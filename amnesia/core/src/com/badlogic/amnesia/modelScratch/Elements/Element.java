package com.badlogic.amnesia.modelScratch.Elements;

/*	Elemento básico do jogo
 * Superclasse mais genérica, define um Elemento básico do jogo.
 * Todo elemento possui um ID:
 * - Elementos vazios (Void) possuem ID 0.
 * - Células possuem ID da forma 2n, n>0 (2,4,6,8,10,12...)
 *	 sendo n a posição relativa da célula na matriz da sala (da direita para a esquerda, de baixo pra cima, começando em 1)   
 * - Demais elementos possuem IDs arbitrários
 */
public class Element {

	protected int ID;

	public int getID() {
		return this.ID;
	}
    
}