package com.pjs.tablette;

import tcp.TCPManager;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


@SuppressLint("ClickableViewAccessibility")
public class MultiTouchListener implements OnTouchListener{

	private float mPrevX;
	private float mPrevY;

	public MainActivity mainActivity;
	private BoutonCouleur bouton;
	private boolean moving = false;

	public MultiTouchListener(MainActivity mainActivity1, BoutonCouleur bouton) {
		this.mainActivity = mainActivity1;
		this.bouton = bouton;
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		float currX,currY;
		int action = event.getAction();

		switch (action ) {

		case MotionEvent.ACTION_DOWN: 
			moving = false;
			mPrevX = event.getRawX();
			mPrevY = event.getRawY();
			break;


		case MotionEvent.ACTION_MOVE:

			moving = true;
			currX = event.getRawX();
			currY = event.getRawY();

			float deltaX = currX - mPrevX;
			float deltaY = currY - mPrevY;

			bouton.setX(bouton.getX()+deltaX);
			bouton.setY(bouton.getY()+deltaY);

			mPrevX = currX;
			mPrevY = currY;


			if(bouton.getX() <= -10){
				Log.v("TabletteAugmentee", "on envoi : "+this.bouton.getIdentifiant());
				TCPManager.getInstance().envoiMessage(""+this.bouton.getIdentifiant());
				this.mainActivity.retirerBouton(this.bouton);
			}
			if(bouton.getX() + bouton.getWidth() >= mainActivity.getWindow().getDecorView().getWidth()){
				Log.v("TabletteAugmentee", "on envoi : "+this.bouton.getIdentifiant());
				TCPManager.getInstance().envoiMessage(""+this.bouton.getIdentifiant());
				this.mainActivity.retirerBouton(this.bouton);
			}
			break;



		case MotionEvent.ACTION_UP:

			if(moving){
				moving = false;
			}else{
				this.mainActivity.setCurrentColor(bouton.getColor());
			}
			break;



		}
		return true;

	}
}
