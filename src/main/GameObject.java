package main;

import java.awt.Rectangle;

public class GameObject {//”Object“类
	public boolean block=true;
	public Rectangle area;
	boolean alive=true;
	Rectangle MoveLimited=null;//是否有活动范围限制 如果有就循环运动
	public GameObject(Rectangle rectangle){
		area=rectangle;
	}
	
	/*
		
 * @brief 判断两个轴对齐的矩形是否重叠
 * @param rc1 第一个矩阵的位置
 * @param rc2 第二个矩阵的位置
 * @return 两个矩阵是否重叠（边沿重叠，也认为是重叠）

		bool isOverlap(const Rect &rc1, const Rect &rc2)
		{
		    if (rc1.x + rc1.width  > rc2.x &&
		        rc2.x + rc2.width  > rc1.x &&
		        rc1.y + rc1.height > rc2.y &&
		        rc2.y + rc2.height > rc1.y
		       )
		        return true;
		    else
		        return false;
		}
	 */
	public boolean TouchCheck(Rectangle rect){
		if (rect.x + rect.width  > area.x &&
				area.x + area.width  > rect.x &&
				rect.y + rect.height > area.y &&
		        area.y + area.height > rect.y
		       )
		        return true;
		    else
		        return false;
	}
}
