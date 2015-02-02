package com.pjs.tablette;

import android.content.Context;
import android.widget.Button;

public class BoutonCouleur extends Button {


	private int couleur;
	private int identifiant;
	
	
	public BoutonCouleur(Context context, String texte, int couleur, int id) {
		super(context);
		this.setText(texte);
		this.couleur = couleur;
		this.identifiant = id;
		
		// TODO Auto-generated constructor stub
	}
	
	public BoutonCouleur(Context context, BoutonCouleur bouton) {
		super(context);
		this.setText(bouton.getText().toString());
		this.couleur = bouton.getColor();
		this.identifiant = bouton.getIdentifiant();
		
		// TODO Auto-generated constructor stub
	}
	
	public int getColor(){
		return this.couleur;
	}

	public int getIdentifiant(){
		return this.identifiant;
	}
	
	public void setIdentifiant(int id){
		this.identifiant = id;
	}
	
	
}
