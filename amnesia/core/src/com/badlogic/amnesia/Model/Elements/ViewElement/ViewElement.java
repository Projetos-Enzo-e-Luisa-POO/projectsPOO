package com.badlogic.amnesia.Model.Elements.ViewElement;
import com.badlogic.amnesia.Model.Elements.Element;

/*	Elemento de visualização estática
 * Banco de dados e métodos para que uma classe fornecer informações visuais a respeito de si mesma.
 * Não é capaz de alterar view, somente fornece dados e ponteiros
 */
public class ViewElement extends Element {

	private String img;

	public void imgConnect (String img){
		this.img = img;
	}

	public String getImg(){
		return this.img;
	}
    
}