package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BadGuy extends Creature{
	public int type=0;//��������
	public int velX=ConstTable.horizontalSpeed_Monster;
	public int velY=0;//y���ٶ�Ӧ��û��
	public BadGuy(Rectangle rectangle) {
		super(rectangle); 
		// TODO Auto-generated constructor stub
	}
	public void Draw(Graphics g){
		g.drawImage(Resourse.badGuysImg[type], area.x, area.y, null);
	}
	public void Hit(Mary mary){
		alive=false;
	}
}
