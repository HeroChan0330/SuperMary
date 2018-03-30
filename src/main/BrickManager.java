package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.LinkedList;

public class BrickManager {//用来统一管理砖头的类
	
	public class ImpactRes{
		public int type=0;//碰撞类型0为不撞  1为从下向上撞 、2为从上向下撞 
		public int attach=0;//附加数据
		public int brickIndex;
		public ImpactRes(int _type,int _attach){
			type=_type;
			attach=_attach;
		}
	}
	
	
	
	
	//LinkedList<Brick> bricks=new LinkedList<>();
	public Brick bricks[]=new Brick[50];//我猜砖头应该比50少吧
	public int count=0;
	GoodsManager manager=null;//主窗口的金钱管理系统
	public BrickManager(GoodsManager moneyManager){
		manager=moneyManager;
	}
	public void Add(Brick brick){//安装高度从高到低排列
		int i=0;
		int top=brick.area.y;
		if(count==0){bricks[0]=brick;count=1;return;}
		while(i<count){
			if(brick.area.y>=bricks[i].area.y)break;
			i++;
		}
		count++;
		for(int t=count-1;t>i;t--){
			bricks[t]=bricks[t-1];
		}
		bricks[i]=brick;
		
	}
	public void Remove(int index){
		for(int i=index;i<count-1;i++){
			bricks[i]=bricks[i+1];
		}
		bricks[--count]=null;
	}
	public ImpactRes CheckImpact(Mary mary){//没碰撞返回-1 碰撞返回一个合适的y坐标
		ImpactRes res=new ImpactRes(0, 0);
		
		Rectangle area=mary.area;
		int left=area.x,right=area.x+area.width;
		int topY=area.y;
		int bottomY=area.y+area.height-mary.ySpeed;
		for(int i=0;i<count;i++){
			if(right>bricks[i].area.x&&left<bricks[i].area.x+bricks[i].area.width){
				res.brickIndex=i;
				if(mary.ySpeed>0&&topY<bricks[i].area.y+bricks[i].area.height&&topY>bricks[i].area.y){
				//从下往上顶
					res.type=2;
					res.attach=bricks[i].area.y+bricks[i].area.height;
					Goods money=bricks[i].Impact();
					if(money!=null)manager.Add(money);
					return res;
				}
				else if(topY<=bricks[i].area.y&&bottomY>bricks[i].area.y&&bottomY<bricks[i].area.y+bricks[i].area.height){
					//从上向下撞
					res.type=1;
					res.attach=bricks[i].area.y-area.height;
					return res;
				}
			}
		}
		return res;
	}
	
	public void BlockInit(){
	/*	Brick ground1=new Brick(new Rectangle(0, ConstTable.baseLineY, 300, 200));
		Brick ground2=new Brick(new Rectangle(450, ConstTable.baseLineY, 3814, 200));
		
		Brick b1=new Brick(new Rectangle(250, 300, 200, 20));
		b1.surprise=true;
		Add(ground1);
		Add(ground2);
		Add(b1);
		*/

	}
	public void Draw(Graphics g){
		
		for(int i=0;i<count;i++){
			//if(bricks[i].type!=0)
			switch(bricks[i].type){
		/*	case 1:
				g.setColor(Color.orange);
				g.fillRect(bricks[i].area.x, bricks[i].area.y, bricks[i].area.width, bricks[i].area.height);
			break;*/
			case 2:
				//g.setColor(Color.GREEN);
				//g.fillRect(bricks[i].area.x, bricks[i].area.y, bricks[i].area.width, bricks[i].area.height);
				g.drawImage(Resourse.BrickImg[0], 
						bricks[i].area.x, bricks[i].area.y, bricks[i].area.x+bricks[i].area.width, bricks[i].area.y+bricks[i].area.height,
						0, 0, Resourse.BrickImgWidth, Resourse.BrickImgWidth, null);
				break;
			case 3:
				//g.setColor(Color.BLUE);
				//g.fillRect(bricks[i].area.x, bricks[i].area.y, bricks[i].area.width, bricks[i].area.height);
				g.drawImage(Resourse.BrickImg[1], 
						bricks[i].area.x, bricks[i].area.y, bricks[i].area.x+bricks[i].area.width, bricks[i].area.y+bricks[i].area.height,
						0, 0, Resourse.BrickImgWidth, Resourse.BrickImgWidth, null);
				break;
			case 4:
				g.drawImage(Resourse.BrickImg[2], 
						bricks[i].area.x, bricks[i].area.y, bricks[i].area.x+bricks[i].area.width, bricks[i].area.y+bricks[i].area.height,
						0, 0, Resourse.BrickImgWidth, Resourse.BrickImgWidth, null);
				break;
				
			}
			
		}
	}
}

