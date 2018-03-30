package main;

import java.awt.Rectangle;

public class Goods extends GameObject{//½ðÇ®  ¼´score

	enum GoodsType{
		None,Money,MushRoom,Sunflower
	}
	public GoodsType goodsType;
	public Goods(Rectangle rectangle) {
		super(rectangle);
		// TODO Auto-generated constructor stub
	}
	

}
