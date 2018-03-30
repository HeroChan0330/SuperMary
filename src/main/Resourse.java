package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

public class Resourse {//管理图像资源
	public static Image bgImage;
	public static Image maryImg[]=new Image[2];
	public static Image badGuysImg[]=new Image[5];//我也不知道有几种怪物类型
	public static Image goodsImg[]=new Image[3];
	public static Image destinationImg;
//	public static Image NormalBrick;
//	public static Image SpecialBrick;
	public static Image BrickImg[]=new Image[3];
	public static int BrickImgWidth=100;

	
	public static void ResourseInit(){
		try {
			bgImage=ImageIO.read(new File("Image\\bg1.jpg"));
			maryImg[0]=ImageIO.read(new File("Image\\maryr.png"));
			maryImg[1]=ImageIO.read(new File("Image\\maryl.png"));
			
			goodsImg[0]=ImageIO.read(new File("Image\\coin.png"));
			goodsImg[1]=ImageIO.read(new File("Image\\mushroom.png"));
			goodsImg[2]=ImageIO.read(new File("Image\\coin.png"));
			
			badGuysImg[0]=ImageIO.read(new File("Image\\monster1.png"));
			badGuysImg[1]=ImageIO.read(new File("Image\\badguy_turtle.png"));
			badGuysImg[2]=ImageIO.read(new File("Image\\badguy-hedgehog.png"));
			
			BrickImg[0]=ImageIO.read(new File("Image\\Brick1.png"));
			BrickImg[1]=ImageIO.read(new File("Image\\Brick2.png"));
			BrickImg[2]=ImageIO.read(new File("Image\\Brick3.png"));
			
			destinationImg=ImageIO.read(new File("Image\\Destination.png"));
			
			BrickImgWidth=BrickImg[0].getWidth(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void LoadGame(){
		
	}
}
