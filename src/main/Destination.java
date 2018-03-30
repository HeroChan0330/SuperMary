package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Destination extends GameObject{

	public Runnable WinEvent=null;
	public Destination(Rectangle rectangle) {
		super(rectangle);
		// TODO Auto-generated constructor stub
	}
	
	public void CheckWin(Rectangle rect)
	{
		if(TouchCheck(rect)){
			if(WinEvent!=null) WinEvent.run();
		}
		//return this.TouchCheck(rect);
	}
	public void Draw(Graphics g){
		g.drawImage(Resourse.destinationImg,area.x,area.y,area.width,area.height,null);
				
	}
}
