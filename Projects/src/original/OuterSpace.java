package original;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	private Alien alienOne;
	private Alien alienTwo;

	
	private ArrayList<Alien> aliens;
	private ArrayList<Ammo> shots;
	

	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];
		ship = new Ship(400,400,3);
		aliens = new ArrayList<Alien>();
		alienOne = new Alien(20,20,1);
		alienTwo = new Alien(100,20,1);
		aliens.add(alienOne);
		aliens.add(alienTwo);
		shots = new ArrayList<Ammo>();
		
		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);

		if(keys[0] == true && ship.getX()>-25)
		{
			ship.move("LEFT");
		}
		if(keys[1] == true && ship.getX()<750)
		{
			ship.move("RIGHT");
		}
		
		if(keys[2] == true && ship.getY()>0)
		{
			ship.move("UP");
		}
		
		if(keys[3] == true && ship.getY()<490)
		{
			ship.move("DOWN");
		}
		//shoot
		if(keys[4] == true)
		{
			Ammo shot = new Ammo(ship.getX()+35,ship.getY(),4);
			shots.add(shot);
			keys[4]=false;
		}
		if(ship.getX()>=750)
			ship.setX(0);
		if(ship.getX()<=-20)
			ship.setX(740);

		ship.draw(graphToBack);
		for(Alien i : aliens)
		{
			i.draw(graphToBack);
			if(i.getX()<721 && i.getX()>0)
				i.move("RIGHT");
			else
			{
				
					i.setY(i.getY()+80);
					i.setSpeed(-i.getSpeed());
					i.move("RIGHT");
				
			}
			for(Ammo s : shots)
			{
				//check for collision
				if(s.getX()+10>i.getX()&&s.getX()<i.getX()+80&&s.getY()<i.getY()+80&&s.getY()>i.getY())
				{
					aliens.remove(i);
					shots.remove(s);
				}
			}
		}
		for(Ammo s : shots)
		{
			//move up and remove if out of bounds
			s.draw(graphToBack);
			s.move("UP");
			if(s.getY()+10<0)
				shots.remove(s);
		}
		
		twoDGraph.drawImage(back, null, 0, 0);
	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{

	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(5);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}

