package com.badlogic.amnesia.Model.ControlInterfaces;

/*	Filtro de visão padrão da célula
 * Interface que define as permissões de acesso à cada célula
 * Possui somente os primeiros triggers para as tomadas de decisão dos processos que definem a gameplay
 *	Walkable poupa a avaliação se ID é par && ID > 1
 *	Interactable garante que fatoração do ID vai retornar primos e distingue matriz destino na Room
 */
public interface Placeable {

	public int getID();

	public String getImg();

	public boolean isWalkable();

}