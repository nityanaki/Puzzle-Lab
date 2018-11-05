// Unit04vST.java by Leon Schram 07-19-10
//
// This is the student starting file of the Unit04 lab assignment.
// The starting file is actually the Puzzle09.java stage, which is the
// finished puzzle game for a 3 X 3 matrix.


import java.awt.*;
import java.applet.*;
import java.util.Random;
import javax.swing.*;
import java.util.Scanner;



public class Unit04vST extends Applet
{
	
	//Rectangle r11,r12,r13,r21,r22,r23,r31,r32,r33;
	Rectangle[][] collection;
	boolean scramble[];
	String matrix[][];
	Random rnd;
	int blankR;
	int blankC;
	int playLevel;
	String[] lastletter = {"I","P","Y"};
	int size;
	
	
	public void init()
	{	
		/*r11 = new Rectangle(100,100,200,200);
		r12 = new Rectangle(300,100,200,200);
		r13 = new Rectangle(500,100,200,200);
		r21 = new Rectangle(100,300,200,200);
		r22 = new Rectangle(300,300,200,200);
		r23 = new Rectangle(500,300,200,200);
		r31 = new Rectangle(100,500,200,200);
		r32 = new Rectangle(300,500,200,200);
		r33 = new Rectangle(500,500,200,200);*/
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter play level:");
		System.out.println("3x3");
		System.out.println("4x4");
		System.out.println("5x5");
		size = keyboard.nextInt();
		
		
		collection = new Rectangle[size+1][size+1];
		
		int hundredrow=1;
		int hundredcol=1;
		for (int a=1;a<collection.length;a++)
		{
			for (int b=1;b<collection[a].length;b++)
			{
				collection[a][b]= new Rectangle(hundredrow*100,hundredcol*100,100,100);
				hundredcol+=1;
			}
			hundredcol=1;
			hundredrow+=1;
		}
		
		
		
		/*for (int r=0;r<3;r++)
		{
			g.drawLine((r+1)*100+100,100,(r+1)*100+100,500);
		}
		for (int c=0;c<3;c++)
		{
			g.drawLine(100,(c+1)*100+100,500,(c+1)*100+100);
		}*/
		
		
		matrix = new String[size+2][size+2];
		scramble = new boolean[size*size+1];
		for (int k = 1; k <=size*size; k++)
			scramble[k] = false;
		rnd = new Random();
		
		for (int r = 0; r <= size+1; r++)
			for (int c = 0; c <= size+1; c++)
				matrix[r][c] = "#";	
		
		String lasto = lastletter[size-3];
		
		for (int r = 1; r <= size; r++)
			for (int c = 1; c <= size; c++)
			{
				matrix[r][c] = getLetter();
				if (matrix[r][c].equals(lasto))
				{
					blankR = r;
					blankC = c;
				}
			}
	}
	
	
	public String getLetter()
	{
		String letter = "";
		boolean Done = false;
		while(!Done)
		{
			int rndNum = rnd.nextInt(size*size) + 1;
			if (scramble[rndNum] == false)
			{
				
				letter = String.valueOf((char) (rndNum+64));
				scramble[rndNum] = true;
				Done = true;
			}
		}
		return letter;		
	}
	
		
	public void paint(Graphics g)
	{
		drawGrid(g);
		/*drawLetter(g,matrix[1][1],100,100);
		drawLetter(g,matrix[1][2],300,100);
		drawLetter(g,matrix[1][3],500,100);
		drawLetter(g,matrix[2][1],100,300);		
		drawLetter(g,matrix[2][2],300,300);
		drawLetter(g,matrix[2][3],500,300);
		drawLetter(g,matrix[3][1],100,500);
		drawLetter(g,matrix[3][2],300,500);
		drawLetter(g,matrix[3][3],500,500);*/
		int hundredrow = 1;
		int hundredcol = 1;
		for (int r=1;r<matrix.length-1;r++)
		{
			for (int c=1;c<matrix[r].length-1;c++)
			{
				drawLetter(g,matrix[r][c],hundredrow*50+30,hundredcol*50-40);
				hundredcol+=2;
			}
			hundredcol=1;
			hundredrow+=2;
		}
	}
	
	
	public void drawGrid(Graphics g)
	{
		//g.drawRect(100,100,600,600);
		//new code is line below
		/*g.drawRect(100,100,800,800);
		g.drawLine(300,100,300,900);
		g.drawLine(500,100,500,900);
		g.drawLine(100,300,900,300);
		g.drawLine(100,500,900,500);
		//new code
		g.drawLine(700,100,700,900);
		g.drawLine(100,700,900,700);*/
		g.drawRect(100,100,size*100,size*100);
		for (int r=0;r<size-1;r++)
		{
			g.drawLine((r+1)*100+100,100,(r+1)*100+100,100+100*size);
		}
		for (int c=0;c<size-1;c++)
		{
			g.drawLine(100,(c+1)*100+100,100+100*size,(c+1)*100+100);
		}
	}
	
	
	public void drawLetter(Graphics g, String letter, int x, int y)
	{
		int offSetX = x + 30-5;
		int offSetY = y + 175;
		g.setFont(new Font("Arial",Font.BOLD,100));
		if (letter.equals(lastletter[size-3]))
		{
			g.setColor(Color.white);
			g.fillRect(x+22,y+92,98,98);
		}
		else
		{
			g.setColor(Color.black);
			g.drawString(letter,offSetX,offSetY);			
		}
	}
	
	
	public boolean mouseDown(Event e, int x, int y)
	{
		/*if(r11.inside(x,y) && okSquare(1,1))
			swap(1,1);
		else if(r12.inside(x,y) && okSquare(1,2))
			swap(1,2);
		else if(r13.inside(x,y) && okSquare(1,3))
			swap(1,3);
		else if(r21.inside(x,y) && okSquare(2,1))
			swap(2,1);
		else if(r22.inside(x,y) && okSquare(2,2))
			swap(2,2);
		else if(r23.inside(x,y) && okSquare(2,3))
			swap(2,3);
		else if(r31.inside(x,y) && okSquare(3,1))
			swap(3,1);
		else if(r32.inside(x,y) && okSquare(3,2))
			swap(3,2);
		else if(r33.inside(x,y) && okSquare(3,3))
			swap(3,3);*/
		for (int i=1;i<matrix.length-1;i++)
		{
			for (int j=1;j<matrix[i].length-1;j++)
			{
				if(collection[i][j].inside(x,y)&&okSquare(i,j))
				{	
					swap(i,j);
					return true;
				}
			}
		}				
		return true;
	}
	
	
	public boolean okSquare(int r, int c)
	{
		boolean temp = false;
		if (matrix[r-1][c].equals(lastletter[size-3]))
			temp = true;
		else if (matrix[r+1][c].equals(lastletter[size-3]))
			temp = true;
		else if (matrix[r][c-1].equals(lastletter[size-3]))
			temp = true;
		else if (matrix[r][c+1].equals(lastletter[size-3]))
			temp = true;
		return temp;	
	}
	
	
	public void swap(int r, int c)
	{
		matrix[blankR][blankC] = matrix[r][c];
		matrix[r][c] = lastletter[size-3];
		blankR = r;
		blankC = c;
		repaint();
	}
	
			
	public void update(Graphics g)
	{
		paint(g);
	}
	
		
}




	
	
