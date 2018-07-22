
import javax.swing.JPanel;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;

public class DrawPanel extends JPanel {

	
	private SoccerField sf;
	private Team t1, t2;
	private static boolean hasStarted = false;
	private Ball b;
	private Timer t;



	public DrawPanel() {
		
	
		sf = new SoccerField();
		b = new Ball();
		hasStarted = false;
		t = new Timer();
		t1 = new Team(Color.BLUE, Team.sides.LEFT_SIDE,false);//if you want play with Blue Team,
																//change the to true
		t2 = new Team(Color.green, Team.sides.RIGHT_SIDE,false);//if you want AI with green Team,
																//change to false
		t.schedule(new PlayerTimer(), 1000, 20);
		t.schedule(new TimerBall(), 1000, 20);

		addKeyListener(new MyKeyboardAdapter());
		setFocusable(true);
		requestFocusInWindow();

	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		int h = getHeight(), w = getWidth();

		if (!hasStarted) {
			sf.setSize(w, h);
			b.goToInitialPosition(w, h);
			t1.goToInitialSide(w, h);
			t2.goToInitialSide(w, h);
			hasStarted = true;
		
		}

		sf.drawMe(g);
		t1.DrawMe(g);
		t2.DrawMe(g);
		b.drawMe(g);
	}

	private class TimerBall extends TimerTask {
		public void run() {

			b.checkAngle(sf);
			if (b.getMove()) {
				b.move();
			}
			t1.GotBall(b);// for Control the ball with player when player took
			 t2.GotBall(b);//the ball
			 
			 if(sf.getRightGateBorders().contains(b.getX(),b.getY())&&b.isTouch()){
				 t1.setResult(t1.getResult()+1);
				 t1.goToInitialSide(800, 476);
				 t2.goToInitialSide(800, 476);
				 b.goToInitialPosition(800, 476);
				 b.setBallSpeed();
				 b.setTouch(false);
			 }
			 if(sf.getLeftGateBorders().contains(b.getX(),b.getY())&&b.isTouch()){
				 t2.setResult(t2.getResult()+1);
				 t1.goToInitialSide(800, 476);
				 t2.goToInitialSide(800, 476);
				 b.goToInitialPosition(800, 476);
				 b.setBallSpeed();
				 b.setTouch(false);
			 }

			repaint();
		}
	}

	private class PlayerTimer extends TimerTask {
		public void run() {
				 { // not Got the ball
					 	
						t1.knowWhatToDoWithoutControl(b, sf);
					
				} // end left team
				
				{
					
					t2.knowWhatToDoWithoutControl(b, sf);
				}
			repaint();
		}

	}


		class MyKeyboardAdapter extends KeyAdapter {
			
			public void keyPressed(KeyEvent e) {
				if(t1.TeamGotBall&&t1.TeamGotControl)	{// constractor
					t1.knowWhatToDoWithBall(b,e);
				}
				if(t2.TeamGotBall&&t2.TeamGotControl){// constractor
					t2.knowWhatToDoWithBall(b,e);
				}
				
			}// end key preesd
		}// end keyAdapter

	
}


