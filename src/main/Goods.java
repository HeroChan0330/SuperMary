package main;

import java.awt.Rectangle;

public class Goods extends GameObject{//��Ǯ  ��score

	enum GoodsType{
		None,Money,MushRoom,Sunflower
	}
	public GoodsType goodsType;
	public Goods(Rectangle rectangle) {
		super(rectangle);
		// TODO Auto-generated constructor stub
	}
	

}
