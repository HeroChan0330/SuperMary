package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.naming.LinkLoopException;

import main.Goods.GoodsType;

public class GoodsManager{//用来管理拾取
	public LinkedList<Goods> goodss=new LinkedList<>();
	public int score=0;
	public GetGoodsEvent getGoodsEvent;
	
	public GoodsManager(){
		
	}
	public void Add(Goods goods){
		goodss.add(goods);
	}
	public GoodsType CheckMoney(Rectangle maryArea){//检测是否有碰到物品
		
		for(Goods m:goodss){
			if(m.TouchCheck(maryArea)){
				if(m.goodsType==GoodsType.Money)score++;
				goodss.remove(m);
				if(getGoodsEvent!=null) getGoodsEvent.Run(m.goodsType);
				return m.goodsType;
			}
		}
		return GoodsType.None;
	}
	public void Draw(Graphics g){
		for(Goods m:goodss){
			//g.fillRect(money.area.x, money.area.y, money.area.width, money.area.height);
			g.drawImage(Resourse.goodsImg[m.goodsType.ordinal()-1], m.area.x, m.area.y,null);
		}

	}
}
