package com.owen.wms.web.music;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;

public class NumberPlayer implements ControllerListener{
	private Iterator<URL> iterator = null;
	private URL currentURL ;
	private Player myPlayer ;
	
	public void playNumber(String input){
		if(input ==null || input.trim().length()<1){
			return;
		}
		List<URL> numbersUrl = new ArrayList<URL>();
		for(int i=0;i<input.length();i++){
			char num = input.charAt(i);
			switch(num){
				case '0' : numbersUrl.add(NumberPlayer.class.getResource("/mp3/10.wav"));break;
				case '1' : case '2' : case '3' : case '4' :
				case '5' : case '6' : case '7' : case '8' : 
				case '9' : numbersUrl.add(NumberPlayer.class.getResource("/mp3/"+num+".wav"));break;
				default : break;
			}
		}
		
		iterator = numbersUrl.iterator();
		if(iterator!=null && iterator.hasNext()){
			this.currentURL = iterator.next();
		}
		this.playNext();
	}
	
	private void playNext() {
		try {
			this.myPlayer = Manager.createPlayer(this.currentURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(this.myPlayer!=null){
			this.myPlayer.addControllerListener(this);
			this.myPlayer.prefetch();
		}
	}

	public void controllerUpdate(ControllerEvent event) {
		if (event instanceof PrefetchCompleteEvent ) {
			this.myPlayer.start();
		}
		
		if (event instanceof EndOfMediaEvent) {
			if(this.iterator.hasNext()){
				this.currentURL = this.iterator.next();
				this.myPlayer.close();
				this.playNext();
			}else{
				this.myPlayer.close();
			}
		}
	}
	
	public static void main(String[] args){
		NumberPlayer np = new NumberPlayer();
		np.playNumber("123xdf4");
	}
}
