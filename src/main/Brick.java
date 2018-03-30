package main;

import java.awt.Rectangle;
import java.awt.geom.Area;

import main.Goods.GoodsType;

public class Brick extends GameObject{//支撑的砖头，包括地面，空中的砖头
	
	public Brick(Rectangle rectangle) {
		super(rectangle);
		// TODO Auto-generated constructor stub
	}
	public int surprise=0;//是否有金币
	public int type=0;//砖块的类型
	public Goods Impact(){//碰撞，然后如果是神秘砖，弹出金钱
		if(surprise>0){
			return ShowGoods();
		}
		return null;
	}
	public Goods ShowGoods(){
		Rectangle showArea=new Rectangle(area.x+(area.width-ConstTable.moneyWidth)/2, area.y-ConstTable.moneyWidth, ConstTable.moneyWidth, ConstTable.moneyWidth);
		Goods goods=new Goods(showArea);
		goods.goodsType=GoodsType.values()[surprise];
		surprise=0;
		return goods;
	}
	public int HorizontalBlock(Rectangle maryArea){//返回-1表示没有横向碰撞 其余返回校正后的位置
		if(TouchCheck(maryArea)){
			if(maryArea.x<area.x+area.width&&maryArea.x+maryArea.width>area.x+area.width)return area.x+area.width;
			else if(maryArea.x+maryArea.width>area.x&&maryArea.x<area.x)return area.x-maryArea.width;
		}
		return -1;
	}
	
}
