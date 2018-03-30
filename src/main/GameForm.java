package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.Goods.GoodsType;

public class GameForm extends JFrame{
	//系统GUI类变量
	Graphics graphics;
	public Timer timer=new Timer();
	KeyManager keyManager=new KeyManager();
	int screenOffsetX=0;//画面的偏移	
	BGMPlayer bgmPlayer=new BGMPlayer();
	
	KeyListener keyListener=new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			keyManager.Release(e.getKeyCode());
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			keyManager.Press(e.getKeyCode());
			
		}
	};
	Image ImageBuffer=null;
	Graphics GraImage;
	public TimerTask gameLoop=new TimerTask() {
		@Override
		public void run() {
			GameLoop();
			SwingUtilities.invokeLater(new Runnable() 
			{
				public void run() {
					// TODO Auto-generated method stub
						//graphics.clearRect(0, 0, GameForm.this.WIDTH, GameForm.this.HEIGHT);
					GamePaint(GraImage);
					
			       //全局绘制
			        graphics.drawImage(ImageBuffer, screenOffsetX, 0, null);//将图形缓冲区绘制到屏幕上  
			        graphics.drawString(String.format("score=%d life=%d", goodsManager.score,mary.life), 5, 45);
			        
			        if(!mary.alive){
			        	graphics.setColor(Color.black);
			        	graphics.fillRect(0, 0, 640, 505);
			        	graphics.setColor(Color.white);
			        	graphics.drawString("GameOver", 250, 250);
			        }
			        else if(Won){
			        	graphics.setColor(Color.black);
			        	graphics.fillRect(0, 0, 640, 505);
			        	graphics.setColor(Color.white);
			        	graphics.drawString("You Win!", 250, 250);
			        }
				}
			});
			//System.out.println(mary.area.x);
			
		}
	};
	public void GamePaint(Graphics g){
		g.drawImage(Resourse.bgImage, 0, 0,null);
		brickManager.Draw(g);
		goodsManager.Draw(g);
		movementManager.Draw(g);
		mary.Draw(g);
		
		goodsManager.Draw(g);
		destination.Draw(g);
		//System.out.println(moneyManager.moneys.size());

	}
	public void GameLoop(){
		if(!mary.alive){
			//timer.cancel();
			//bgmPlayer.StartBGM(1);
		}
		if(keyManager.IfPress(KeyEvent.VK_LEFT)){
			mary.LeftMove();
		}
		else if(keyManager.IfPress(KeyEvent.VK_RIGHT)){
			mary.RightMove();
		}
		if(goodsManager.CheckMoney(mary.area)==GoodsType.Money){
			System.out.println("MoneyGet!");
		}
		
		if(keyManager.IfPress(KeyEvent.VK_UP)){
			mary.Skip();
		}
		
		mary.StateUpdate(brickManager);
		movementManager.AutoUpdate();
		movementManager.CheckImpact(mary);
		destination.CheckWin(mary.area);
		
		//画面控制
		int actualX=mary.area.x+screenOffsetX;
		if(actualX>ConstTable.RIGHTMOVELIM&&mary.direction==0){
			screenOffsetX=ConstTable.RIGHTMOVELIM-mary.area.x;
		}
		else if(actualX<ConstTable.LEFTMOVELIM&&mary.direction==1){
			screenOffsetX=ConstTable.LEFTMOVELIM-mary.area.x;
		}
		if(screenOffsetX>0)screenOffsetX=0;
		if(screenOffsetX<ConstTable.OffsetMax)screenOffsetX=ConstTable.OffsetMax;
		//screenOffsetX[]下标代表关卡
		
		//System.out.println(mary.area.x);
	}
	
	
	//游戏事件
	Runnable hitEvent=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			if(!mary.alive){
			bgmPlayer.StartBGM(1);
			bgmPlayer.PlayEffect(1);
			}
			screenOffsetX=0;//回到开始位置
		}
	};
	Runnable killEvent=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			bgmPlayer.PlayEffect(0);
		}
	};
	GetGoodsEvent getGoodsEvent=new GetGoodsEvent(){
		@Override
		public void Run(GoodsType goodsType){
			if(goodsType==GoodsType.MushRoom){
				mary.ChangeSize(1.5);
			}
			bgmPlayer.PlayEffect(2);
		}
	};
	Runnable winEvent=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Win!");
			Won=true;
			
			if(gameLoader.mission<ConstTable.FinalMission){
				gameLoader.NextMission();
				screenOffsetX=0;//回到开始位置
				Init();
			//Begin();
			}
		}
	};
	//游戏类变量
	Mary mary;
	GoodsManager goodsManager;
	BrickManager brickManager;
	MovementManager movementManager;
	Destination destination;
	GameLoader gameLoader;
	boolean Won=false;
	public GameForm() {
		Init();
	}
	void Init(){
		this.setBounds(0,0,ConstTable.SCREENWIDTH,ConstTable.SCREENHEIGHT+24);
		this.setResizable(false);
		this.setTitle("I am Mary!");
		this.addKeyListener(keyListener); 
		
		//资源初始化
		Resourse.ResourseInit();
		goodsManager=new GoodsManager();
		brickManager=new BrickManager(goodsManager);
		movementManager=new MovementManager();
		gameLoader=new GameLoader();
		Won=false;
		//brickManager.BlockInit();//暂时用的添加砖块
		//movementManager.BadGuysInit();//暂时用的添加badguy
		
		gameLoader.LoadMission("Data\\Missions.xml",goodsManager);
		gameLoader.BrickAnalyze("Data\\Bricks.xml", brickManager);
		gameLoader.NPCAnalyze("Data\\BadGuys.xml", movementManager);
		mary=new Mary(new Rectangle(10, ConstTable.baseLineY-39, ConstTable.MaryWidth, ConstTable.MaryHeight),brickManager);
		destination=new Destination(new Rectangle(ConstTable.DestionationX,ConstTable.DestionationY,Resourse.destinationImg.getWidth(null),Resourse.destinationImg.getHeight(null))); 
		//事件管理
		mary.hitEvent=hitEvent;
		movementManager.killEvent=killEvent;
		goodsManager.getGoodsEvent=getGoodsEvent;
		destination.WinEvent=winEvent;
	}
	public void Begin(){
		graphics=getGraphics();
		//graphics=drawingSurface.getGraphics();
		graphics.setColor(Color.black);
		graphics.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		//graphics.fillRect(0, 0, 100, 10);
		if(ImageBuffer==null){
		       ImageBuffer = createImage(Resourse.bgImage.getWidth(null), Resourse.bgImage.getHeight(null));//创建图形缓冲区  
		        GraImage = ImageBuffer.getGraphics();//获取图形缓冲区的图形上下文  
		}
		timer=new Timer();
		timer.schedule(gameLoop, 30,30);
		bgmPlayer.StartBGM(0);
	}
}
