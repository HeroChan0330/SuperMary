package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.invoke.ConstantCallSite;

import main.BrickManager.ImpactRes;

public class Mary extends Creature{//����
	boolean hanging=false;//����״̬
	int ySpeed=0;//��ֱ�����ٶ�
	
	int direction=0;//0Ϊ�� 1Ϊ��
	public Runnable hitEvent=null;
	public BrickManager brickManager=null;

	int life=3;//������
	double SizeMultiple=1;
	
	public Mary(Rectangle rectangle,BrickManager brickManager) {
		super(rectangle);
		this.brickManager=brickManager;
		// TODO Auto-generated constructor stub
	}
	public void LeftMove(){
		area.x-=ConstTable.horizontalSpeed*SizeMultiple;
		if(area.x<0)area.x=0;
		direction=1;
		for(int i=0;i<brickManager.count;i++){
			int res=brickManager.bricks[i].HorizontalBlock(area);
			if(res!=-1){
				area.x=res;
				break;
			}
		}
	}
	public void RightMove(){
		area.x+=ConstTable.horizontalSpeed*SizeMultiple;
		if(area.x+area.width>ConstTable.SceneWidth)area.x=ConstTable.SceneWidth-area.width;
		direction=0;
		for(int i=0;i<brickManager.count;i++){
			int res=brickManager.bricks[i].HorizontalBlock(area);
			if(res!=-1){
				area.x=res;
				break;
			}
		}
		
	}
	public void Skip(){
		if(!hanging){
			ySpeed=(int)(ConstTable.SkipSpeed*Math.sqrt(SizeMultiple));
			hanging=true;
		}	
	}
	
	//@Override
	public void StateUpdate(BrickManager brickManager){//����״̬����Ҫ����Ծ��
		BrickManager.ImpactRes checkImpactRes=brickManager.CheckImpact(this);
		if(hanging){
			if(checkImpactRes.type!=0){
				if(checkImpactRes.type==1){
				ySpeed=0;
				hanging=false;
				area.y=checkImpactRes.attach;
				}
				else if(checkImpactRes.type==2){//���϶�
					//hanging=false;
					if(SizeMultiple>1&&brickManager.bricks[checkImpactRes.brickIndex].type==4){
					//����ש��
						brickManager.Remove(checkImpactRes.brickIndex);
					}
					else{
						ySpeed*=-1;
						area.y=checkImpactRes.attach;
					}
				}
			}
			else{
				area.y-=ySpeed;
				ySpeed-=ConstTable.gravity;
				if(ySpeed<-ConstTable.MaxSpeedY)ySpeed=-ConstTable.MaxSpeedY;
			}
		}
		else{
			if(checkImpactRes.type==0){//û���κ�����Ӵ�������״̬
			ySpeed=0;
			hanging=true;
			}
		}
		//System.out.println(checkImpactRes);
		
		//�߿������������
		if(area.y>=ConstTable.SCREENHEIGHT){
			Hit();
		}
	}
	void Hit(){
		life--;
		if(life<=0){
		alive=false;
		System.out.println("Mary Die~~");
		if(hitEvent!=null) hitEvent.run();
		}
		else{
		System.out.println("Mary Hit~~");
		if(hitEvent!=null) hitEvent.run();
		Restart();
		}

	}
	void Restart(){
		area=new Rectangle(ConstTable.MaryStartPosX, ConstTable.MaryStartPosY, ConstTable.MaryWidth, ConstTable.MaryHeight);
		SizeMultiple=1;
	}
	//void Die(){}
	void ChangeSize(double multiple){
		area.y-=(int)((multiple-1.0)*(double)ConstTable.MaryHeight);
		area.width=(int)(ConstTable.MaryWidth*multiple);
		area.height=(int)(ConstTable.MaryHeight*multiple);
		SizeMultiple=multiple;
	}
	public void Draw(Graphics g){
		/*g.drawImage(Resourse.maryImg[direction],
				area.x, area.y,null);*/
		g.drawImage(Resourse.maryImg[direction],
				area.x, area.y, area.x+area.width, area.y+area.height, 
				0, 0, ConstTable.MaryWidth, ConstTable.MaryHeight, null);
	}

	
}
