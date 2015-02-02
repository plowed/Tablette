package com.pjs.tablette;

import java.util.ArrayList;
import java.util.List;

import tcp.TCPEcoute;
import tcp.TCPManager;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

@SuppressLint({ "NewApi", "ClickableViewAccessibility" })
public class MainActivity extends Activity {

	private int currentColor;
	private DrawView draw;
	private LinearLayout layoutVertical;
	private LinearLayout layoutHorizontal;
	private List<BoutonCouleur> boutons;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		currentColor = Color.BLACK;

		this.boutons = new ArrayList<BoutonCouleur>();
		this.layoutVertical = (LinearLayout) findViewById(R.id.linearVertical);
		this.layoutHorizontal = (LinearLayout) findViewById(R.id.linearHorizontal);

		this.draw = new DrawView(this);

		this.boutons.add(new BoutonCouleur(this, "JAUNE", Color.YELLOW, 0));
		this.boutons.add(new BoutonCouleur(this, "CYAN", Color.CYAN, 1));
		this.boutons.add(new BoutonCouleur(this, "ROUGE", Color.RED, 2));

		for(int i=0; i<this.boutons.size(); i++){
			this.boutons.get(i).setOnTouchListener(new MultiTouchListener(this, this.boutons.get(i)));
			layoutHorizontal.addView(
					this.boutons.get(i), 
					new LayoutParams(
							LayoutParams.WRAP_CONTENT, 
							LayoutParams.WRAP_CONTENT)
					);
		}

		this.layoutVertical.addView(draw);

		TCPManager.getInstance().setActivite(this);
		TCPEcoute ecoute = new TCPEcoute();
		ecoute.execute();
	}










	/****** OSEF ****/






	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			TCPManager.getInstance().envoiMessage("reset");
		}
		return super.onOptionsItemSelected(item);
	}


	public int getCurrentColor(){
		return this.currentColor;
	}

	public void setCurrentColor(int color){
		this.currentColor = color;
		this.draw.invalidate();
	}


	public void setColorOfBouton(int bouton) {
		// TODO Auto-generated method stub
		if(this.boutons.size() > bouton && bouton >= 0){
			this.setCurrentColor(this.boutons.get(bouton).getColor());
		}
	}


	public void retirerBouton(BoutonCouleur bouton){
		this.layoutHorizontal.removeView(bouton);
	}

	public void remettreBouton(int bouton) {
		// TODO Auto-generated method stub
		if(bouton >= 0 && bouton < this.boutons.size()){
			this.layoutHorizontal.removeView(this.boutons.get(bouton));

			BoutonCouleur newBouton = new BoutonCouleur(this, this.boutons.get(bouton));
			newBouton.setOnTouchListener(new MultiTouchListener(this, newBouton));
			this.layoutHorizontal.addView(newBouton);
			this.boutons.set(bouton, newBouton);
		}
	}
}
