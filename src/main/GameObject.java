package main;

import java.awt.Rectangle;

public class GameObject {//��Object����
	public boolean block=true;
	public Rectangle area;
	boolean alive=true;
	Rectangle MoveLimited=null;//�Ƿ��л��Χ���� ����о�ѭ���˶�
	public GameObject(Rectangle rectangle){
		area=rectangle;
	}
	
	/*
		
 * @brief �ж����������ľ����Ƿ��ص�
 * @param rc1 ��һ�������λ��
 * @param rc2 �ڶ��������λ��
 * @return ���������Ƿ��ص��������ص���Ҳ��Ϊ���ص���

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
