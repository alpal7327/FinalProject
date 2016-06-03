package fin;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import javax.swing.JFrame;
import java.awt.Component;

public class StarDino extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public StarDino()
	{
		super("STARDINO");
		setSize(WIDTH,HEIGHT);

		OuterSpace theGame = new OuterSpace();
		((Component)theGame).setFocusable(true);

		getContentPane().add(theGame);

		setVisible(true);
	}

	public static void main( String args[] )
	{
		StarDino run = new StarDino();
	}
}