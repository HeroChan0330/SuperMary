package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BadGuy_Hedgehog extends BadGuy{//¥Ã‚¨

	public BadGuy_Hedgehog(Rectangle rectangle) {
		super(rectangle);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
		//super.Draw(g);
		g.drawImage(Resourse.badGuysImg[2],area.x, area.y, null);
	}
	@Override
	public void Hit(Mary mary) {
		// TODO Auto-generated method stub
		mary.Hit();
	}
}
