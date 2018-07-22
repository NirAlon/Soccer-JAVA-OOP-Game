import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public abstract class Players implements sheard_function {

	public final int RADIUS=10;
	public final double SPEED = 0.1; 
	protected double cx,cy;
	protected double alpha;
	protected double playerSpeed;
	protected Color teamColor;
	protected Team.sides T_S;
	protected int num;
	protected boolean PlayerGotBall=false;
	
	public Players(Color c, Team.sides s) {
		
		teamColor=c;
		T_S = s;
		PlayerGotBall=false;
	}

	public void DrawMe(Graphics g) {
		g.setColor(teamColor);
		g.fillOval((int)cx-RADIUS, (int)cy-RADIUS, 2*RADIUS, 2*RADIUS);
		g.setColor(Color.BLACK);
		g.drawOval((int)cx-RADIUS, (int)cy-RADIUS, 2*RADIUS, 2*RADIUS);
		g.drawLine((int)cx, (int)cy, (int)(cx+RADIUS*Math.cos(alpha)),
												(int)(cy+RADIUS*Math.sin(alpha)));
		g.drawString(""+num,(int) cx-RADIUS, (int)cy-RADIUS);
		
		
		
	}

	protected void setPlayerGotBall(boolean b) {
		PlayerGotBall=b;
		
	}

	protected void setAlpha(double alpha){
		this.alpha=alpha;
	}
	protected double getAlpha() {
		
		return alpha;
	}

	public void setCenter(double left, double top) {
		cx = left;
		cy = top;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return cx;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return cy;
	}

	public Rectangle playerRec(){
		return new Rectangle((int)cx-3,(int)cy-3,(int)RADIUS*2+3,(int)RADIUS*2+3);
	}

	
	public void WhatToDoWithBall(Ball b, KeyEvent e) {
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_UP):
			setAlpha(getAlpha() - Math.PI / 10);
			break;

		case (KeyEvent.VK_SPACE):
			b.setAlpha(alpha);
			((Ball) b).Shot();
			break;

		case (KeyEvent.VK_DOWN):
			setAlpha(getAlpha() + Math.PI / 10);
			break;
			
		
		}
		
	}

	
	
	protected double getPDirY() {
		// TODO Auto-generated method stub
		return Math.sin(alpha);
	}

	protected double getPDirX() {
		// TODO Auto-generated method stub
		return Math.cos(alpha);
	}
}
