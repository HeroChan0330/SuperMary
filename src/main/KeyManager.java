package main;

import java.awt.List;
import java.util.LinkedList;

public class KeyManager {//¼üÅÌ¹ÜÀí
	public LinkedList<Integer> keyCodes=new LinkedList<>();
	public KeyManager(){

	}
	public void Press(int keyCode){
		if(!keyCodes.contains(keyCode))
		keyCodes.add(keyCode);
	}
	public void Release(int keyCode){
		if(keyCodes.contains(keyCode))
		keyCodes.remove((Object)keyCode);
	}
	public boolean IfPress(int keyCode){
		return keyCodes.contains(keyCode);
	}
	
}
