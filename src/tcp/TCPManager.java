package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;

import com.pjs.tablette.MainActivity;

import android.util.Log;

public class TCPManager {

	private String serveraddress;
	private int port;
	
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	private MainActivity activite;
	
	public TCPManager(){
		
		this.serveraddress="192.168.0.17";
		this.port = 1234;
		this.socket = null;
		this.out = null;
		this.in = null;
		this.activite = null;
		
	}
	
	public void setAdresse(String adresse){
		Log.v("TabletteAugmentee", "adresse : "+adresse);
		this.serveraddress = adresse;
	}
	public void setActivite(MainActivity activity){
		this.activite = activity;
	}
	
	
	public boolean connect(){

		try {
			this.socket = new Socket(this.serveraddress, this.port);
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			
			Log.v("TabletteAugmentee", sw.toString());
			return false;
		}

		return this.isConnected();	
	}
	
	public boolean isConnected(){
		if(socket != null){
			return socket.isConnected();
		}
		return false;
	}

	

	public void envoiMessage(String message){
		out.print(message);
		out.flush();
	}

	public String recevoirMessage(){
		String reponse = null;
		
		try {
			reponse = in.readLine();
			Log.v("TabletteAugmentee", "recu => "+reponse);
		} catch (IOException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("TabletteAugmentee", "erreur ecoute");
		}
		
		
		return reponse;
	}
	
	public void updateCouleur(int bouton){
		this.activite.setColorOfBouton(bouton);
	}
	
	public void remettreBouton(int bouton){
		this.activite.remettreBouton(bouton);
	}


	/** Holder */
	private static class SingletonHolder
	{		
		/** Instance unique non pr�initialis�e */
		private final static TCPManager instance = new TCPManager();
	}

	/** Point d'acc�s pour l'instance unique du singleton */
	public static TCPManager getInstance()
	{
		return SingletonHolder.instance;
	}

}
