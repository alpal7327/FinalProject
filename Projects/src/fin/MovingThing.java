package fin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public abstract class MovingThing implements Locatable
{
	private int xPos;
	private int yPos;

	public MovingThing()
	{
		this(10,10);
	}

	public MovingThing(int x, int y)
	{
		setPos(x,y);
	}

	public void setPos( int x, int y)
	{
		setX(x);
		setY(y);
	}


	public void setX(int x)
	{
		xPos = x;
	}


	public void setY(int y)
	{
		yPos = y;
	}

	public int getX()
	{
		return xPos;
	}


	public int getY()
	{
		return yPos;
	}

	public abstract void setSpeed( int s );
	public abstract int getSpeed();
	public abstract void draw(Graphics window);
	public abstract void draw(Graphics window, Image image);

	public void move(String direction)
	{
		if(direction.equals("LEFT"))
			setX(getX()-getSpeed());
		if(direction.equals("RIGHT"))
			setX(getX()+getSpeed());
		if(direction.equals("UP"))
			setY(getY()-getSpeed());
		if(direction.equals("DOWN"))
			setY(getY()+getSpeed());
      //add more code to complete the move method
	}

	public String toString()
	{
		return getX() + " " + getY() + " ";
	}
}