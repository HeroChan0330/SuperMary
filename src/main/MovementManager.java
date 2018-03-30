package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.EventListener;
import java.util.LinkedList;

import org.omg.CORBA.portable.Delegate;

public class MovementManager {//这个类主要是用来控制怪物的移动

	LinkedList<BadGuy> badGuys=new LinkedList<>();
	Runnable killEvent=null;

	
	public MovementManager(){

	}
	public void Add(BadGuy badguy){
		badGuys.add(badguy);
	}
	public void AutoUpdate(){
		for(BadGuy badGuy:badGuys){
			if(!badGuy.alive)return;
			if(badGuy.MoveLimited!=null){
				if(badGuy.area.x<=badGuy.MoveLimited.x||badGuy.area.x+badGuy.area.width>=badGuy.MoveLimited.x+badGuy.MoveLimited.width)badGuy.velX*=-1;
				if(badGuy.area.y<=badGuy.MoveLimited.y||badGuy.area.y+badGuy.area.height>=badGuy.MoveLimited.y+badGuy.MoveLimited.height)badGuy.velY*=-1;
			}
			badGuy.area.x+=badGuy.velX;
			//badGuy.area.y+=badGuy.velY;
		}
	}
	public void Draw(Graphics g){
		g.setColor(Color.red);
		for(BadGuy badGuy:badGuys){
			if(badGuy.alive){
			//g.fillRect(badGuy.area.x, badGuy.area.y, badGuy.area.width, badGuy.area.height);
			badGuy.Draw(g);
			}
		}
		
	}
	public void BadGuysInit(){
		BadGuy badGuy=new BadGuy(new Rectangle(100, ConstTable.baseLineY-31, 30, 31));
		badGuy.MoveLimited=new Rectangle(20, 0, 180, 0);
		Add(badGuy);
	}
	public void HitBadGuy(BadGuy badGuy,Mary mary){
		badGuy.Hit(mary);//可以继承badguy写其他类型怪物，对Hit进行重写
		if(!badGuy.alive){
			badGuys.remove(badGuy);	
			if(killEvent!=null)killEvent.run();
		}
	}
	public void CheckImpact(Mary mary){
		//System.out.println(badGuys.size());
		for(BadGuy badGuy:badGuys){
			if(badGuy.TouchCheck(mary.area)){
				//System.out.println(mary.ySpeed);
				if(mary.ySpeed<-1) {//不知道哪里的BUG，这里如果是0有时会反杀？
					HitBadGuy(badGuy,mary);
					mary.ySpeed*=-1;
					break;
				}
				else mary.Hit();
			}
		}
	}
}
