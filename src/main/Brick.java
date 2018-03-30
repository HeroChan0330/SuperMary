package main;

import java.awt.Rectangle;
import java.awt.geom.Area;

import main.Goods.GoodsType;

public class Brick extends GameObject{//֧�ŵ�שͷ���������棬���е�שͷ
	
	public Brick(Rectangle rectangle) {
		super(rectangle);
		// TODO Auto-generated constructor stub
	}
	public int surprise=0;//�Ƿ��н��
	public int type=0;//ש�������
	public Goods Impact(){//��ײ��Ȼ�����������ש��������Ǯ
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
	public int HorizontalBlock(Rectangle maryArea){//����-1��ʾû�к�����ײ ���෵��У�����λ��
		if(TouchCheck(maryArea)){
			if(maryArea.x<area.x+area.width&&maryArea.x+maryArea.width>area.x+area.width)return area.x+area.width;
			else if(maryArea.x+maryArea.width>area.x&&maryArea.x<area.x)return area.x-maryArea.width;
		}
		return -1;
	}
	
}
