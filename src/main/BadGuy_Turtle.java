package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BadGuy_Turtle extends BadGuy{
	public boolean hitted=false;//Ӣ���﷨�ϲ�ͨ��ֻ�Ǳ�ʾ��û�б����й�..
	public BadGuy_Turtle(Rectangle rectangle) {
		super(rectangle);
		// TODO Auto-generated constructor stub
		
	}
	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
		//super.Draw(g);
		g.drawImage(Resourse.badGuysImg[1],area.x, area.y, null);
	}
	@Override
	public void Hit(Mary mary) {
		// TODO Auto-generated method stub
		if(!hitted){
			hitted=true;
			velX*=10;
		}
		else{
			alive=false;
		}
	}
}
