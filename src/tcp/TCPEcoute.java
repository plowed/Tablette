package tcp;

import android.os.AsyncTask;
import android.util.Log;

public class TCPEcoute extends AsyncTask<Void, String, Void> {
	private static final String ACTIVATION_BOUTON = "activation";
	private static final String RETOUR_BOUTON = "retour";
	@Override
	protected Void doInBackground(Void... arg0) {
		while(true){
			if(TCPManager.getInstance().isConnected()){
				String msg = TCPManager.getInstance().recevoirMessage();
				if(msg != null)
		            publishProgress(msg);

			}else{
				TCPManager.getInstance().connect();
			}
		}
	}
	
	protected void onProgressUpdate(String... msg) {
		String[] message = msg[0].split("/");
		int bouton = -1;
		try{
			bouton = Integer.parseInt(message[0]);
		}catch(java.lang.NumberFormatException e){
			Log.v("TabletteAugmentee", " mauvais format => "+msg[0]);
			return;
		}
		
		if(message.length > 1){
			if(message[1].contains(ACTIVATION_BOUTON)){
				TCPManager.getInstance().updateCouleur(bouton);
			}else if(message[1].contains(RETOUR_BOUTON)){
				TCPManager.getInstance().remettreBouton(bouton);
			}
		}
	}

}
