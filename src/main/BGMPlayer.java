package main;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BGMPlayer {
	private Thread bGMThread=null;
	private FileInputStream fileInputStream1,fileInputStream2;
	private Thread effectThread=null;
	
	public final String fileAddress1[]={
			"Audio\\BGM.mp3","Audio\\GameOver.mp3"
	};
	public final String fileAddress2[]={
			"Audio\\Kill.mp3","Audio\\Die.mp3","Audio\\GetCoin.mp3"
	};
	private Player player1=null;
	private Player player2=null;
	public BGMPlayer(){
		 
	}
	public void StartBGM(int i){
			if(bGMThread!=null) bGMThread.stop();
			bGMThread=new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
					if(fileInputStream1!=null) fileInputStream1.close();
					fileInputStream1 =new FileInputStream(fileAddress1[i]);
					BufferedInputStream buffer = new BufferedInputStream(fileInputStream1);
					player1=new Player(buffer);
					player1.play();
				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			});
			bGMThread.start();
			
	}
	public void PlayEffect(int i){
		if(effectThread!=null) effectThread.stop();
		effectThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
				if(fileInputStream2!=null) fileInputStream2.close();
				fileInputStream2 =new FileInputStream(fileAddress2[i]);
				BufferedInputStream buffer = new BufferedInputStream(fileInputStream2);
				player2=new Player(buffer);
				player2.play();
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		effectThread.start();
	}
}
