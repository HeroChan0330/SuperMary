package main;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.Node; 
import org.w3c.dom.NodeList; 
import org.xml.sax.SAXException;

import main.Goods.GoodsType; 

public class GameLoader {
	public int mission=0;
	public class DOMParser { 
		  DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
		  //Load and parse XML file into DOM 
		  public Document parse(String filePath) { 
		     Document document = null; 
		     try { 
		        //DOM parser instance 
		        DocumentBuilder builder = builderFactory.newDocumentBuilder(); 
		        //parse an XML file into a DOM tree 
		        document = (Document) builder.parse(new File(filePath)); 
		     } catch (ParserConfigurationException e) { 
		        e.printStackTrace();  
		     } catch (SAXException e) { 
		        e.printStackTrace(); 
		     } catch (IOException e) { 
		        e.printStackTrace(); 
		     } 
		     return document; 
		  }
	}
	
	public GameLoader(){
 
	}
	public void LoadMission(String fileName,GoodsManager goodsManager){
		ReadHis();
		
	       DOMParser parser = new DOMParser(); 
	        Document document = parser.parse(fileName); 
	        //get root element 
	         Element rootElement = document.getDocumentElement();
	       
	        //System.out.println(rootElement.getAttribute("Id"));
	        
	        System.out.println(mission);
	        
	        NodeList nodes = rootElement.getChildNodes();
	        
	        Element node=null;
	        for(int i=0;i<nodes.getLength();i++){
	        	if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
	        		Element temp= (Element)nodes.item(i);
	        		if(Integer.parseInt(temp.getAttribute("Id"))==mission){
	        			node=temp;
	        			break;
	        		}
	        	}
	        }
	        
	        ConstTable.baseLineY=Integer.parseInt(node.getAttribute("BaseLine")); 
	        
	        ConstTable.OffsetMax=Integer.parseInt(node.getAttribute("OffsetMax")); 
	        ConstTable.SceneWidth=Integer.parseInt(node.getAttribute("SceneWidth"));
	        
	        ConstTable.DestionationX=Integer.parseInt(node.getAttribute("DestionationX")); 
	        ConstTable.DestionationY=ConstTable.baseLineY-Integer.parseInt(node.getAttribute("DestionationY")); 
	        
	        try {
	        	
				Resourse.bgImage=ImageIO.read(new File(node.getAttribute("BGImg")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        NodeList list=node.getChildNodes();
	       // System.out.println(node);
	        	for(int i=1;i<list.getLength();i++){
	        		if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {   
		        		Element node2=(Element)list.item(i);
		        		int x=Integer.parseInt(node2.getAttribute("x")); 
		        		int y=ConstTable.baseLineY-Integer.parseInt(node2.getAttribute("y")); 
		        		int type=Integer.parseInt(node2.getAttribute("type")); 
		        		Goods goods=new Goods(new Rectangle(x, y, ConstTable.moneyWidth, ConstTable.moneyWidth));
		        		goods.goodsType=GoodsType.values()[type];
		        		goodsManager.Add(goods);
	        		}
	        	}
    	document=null;
	}
	public void BrickAnalyze(String fileName,BrickManager brickManager){
	       DOMParser parser = new DOMParser(); 
	        Document document = parser.parse(fileName); 
	        //get root element 
	        Element rootElement = document.getDocumentElement();
	       
	        //System.out.println(rootElement.getAttribute("Id"));
	        
	        
	        
	        //下面的节点就是各个砖块的信息 
	        NodeList nodes = null; 
	        NodeList temp1=rootElement.getElementsByTagName("Bricks");
	        for(int i=0;i<temp1.getLength();i++){
	        	Element temp2=(Element)temp1.item(i);
	        	//System.out.println(temp2);
	        	if(Integer.parseInt(temp2.getAttribute("Id"))==mission){
	        		nodes=temp2.getChildNodes();
	        	}
	        }
	        
	        for (int i=0; i < nodes.getLength(); i++) 
	        {
	        
	           Node node = (Node) nodes.item(i); 
	           if (node.getNodeType() == Node.ELEMENT_NODE) {   
	              Element child = (Element) node; 
	              int width = Integer.parseInt(child.getAttribute("width")); 
	              int height = Integer.parseInt(child.getAttribute("height")); 
	              int x = Integer.parseInt(child.getAttribute("x")); 
	              int y= ConstTable.baseLineY-Integer.parseInt(child.getAttribute("y")); 
	              int surprise=Integer.parseInt(child.getAttribute("surprise")); 
	              int type=Integer.parseInt(child.getAttribute("type"));
	              Brick brick=new Brick(new Rectangle(x, y, width, height));
	              brick.type=type;
	              brick.surprise=surprise;
	              brickManager.Add(brick);
	             //System.out.println(width);
	              //process child element 
	           }
	        }
	        document=null;
	}
	public void NPCAnalyze(String fileName,MovementManager movementManager){
	       DOMParser parser = new DOMParser(); 
	        Document document = parser.parse(fileName); 
	        //get root element 
	         Element rootElement = document.getDocumentElement();
	       
	        //System.out.println(rootElement.getAttribute("Id"));
	        
	        
	        
	        //下面的节点就是各个NPC的信息 
	        NodeList nodes = null; 
	        NodeList temp1=rootElement.getElementsByTagName("BadGuys");
	        for(int i=0;i<temp1.getLength();i++){
	        	Element temp2=(Element)temp1.item(i);
	        	//System.out.println(temp2);
	        	if(Integer.parseInt(temp2.getAttribute("Id"))==mission){
	        		nodes=temp2.getChildNodes();
	        	}
	        }
	        for (int i=0; i < nodes.getLength(); i++) 
	        { 
	        
	           Node node = (Node) nodes.item(i); 
	           if (node.getNodeType() == Node.ELEMENT_NODE) {   
	              Element child = (Element) node; 
	              int width = Integer.parseInt(child.getAttribute("width")); 
	              int height = Integer.parseInt(child.getAttribute("height")); 
	              int x = Integer.parseInt(child.getAttribute("x")); 
	              int y= ConstTable.baseLineY-Integer.parseInt(child.getAttribute("y")); 
	              int limRectX=Integer.parseInt(child.getAttribute("LimRectX")); 
	              int limRectWidth=Integer.parseInt(child.getAttribute("LimRectWidth")); 
	              int type=Integer.parseInt(child.getAttribute("type"));
	              BadGuy badGuy;
	              switch(type){
	              	case 1:
	              		badGuy=new BadGuy_Turtle(new Rectangle(x, y, width, height));
	              		break;
	              	case 2:
	              		badGuy=new BadGuy_Hedgehog(new Rectangle(x, y, width, height));
	              		break;
	              	default:
	              		badGuy=new BadGuy(new Rectangle(x, y, width, height));
	              		break;
	              }
            		badGuy.type=type;
	              badGuy.MoveLimited=new Rectangle(limRectX, 0, limRectWidth, 0);
	              movementManager.Add(badGuy);
	             //System.out.println(width);
	              //process child element 
	           }
	        }
	        document=null;
	}
	public int ReadHis(){
		try {
			FileReader fileReader=new FileReader(new File("GameHis.dat"));
			mission=fileReader.read();
			fileReader.close();
			return mission;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				FileWriter fileWriter = new FileWriter(new File("GameHis.dat"),false);
				fileWriter.write(1);
				mission=1;
				fileWriter.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public void WriteHis(int _mission){
		try {
			FileWriter fileWriter=new FileWriter(new File("GameHis.dat"),false);
			fileWriter.write(_mission);
			fileWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void NextMission(){
		mission++;
		WriteHis(mission);
	}
}
