import java.awt.Color;
import java.awt.event.KeyEvent;



public class AtackDown extends Players {

	public AtackDown(Color c, Team.sides s,int num) {
		super(c, s);
		this.num = num;
	}

	@Override
	public void goToInitialPosition(int w, int h) {
		if (T_S.equals(Team.sides.LEFT_SIDE)) {
			setCenter(w / 3+w/3, h/4+h/2);
			this.alpha = 0;
		} else {
			setCenter(w / 3, h/4+h/2);
			this.alpha = -Math.PI;
		}

	}
	public void WhatToDoWithBall(Ball b, KeyEvent e) {
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_UP):
			double dx =	getPDirX();
			double dy = getPDirY();
		
			cx = cx+(RADIUS*2*dx);
			cy = cy+(RADIUS*2*dy);
			b.setX(getX());
			b.setY(getY());
			break;

		case (KeyEvent.VK_SPACE):
			b.setAlpha(alpha);
			((Ball) b).Shot();
			break;

		case (KeyEvent.VK_DOWN):
			 dx =	getPDirX();
			dy = getPDirY();

			cx = cx-(RADIUS*2*dx);
			cy = cy-(RADIUS*2*dy);
			b.setX(getX());
			b.setY(getY());
			break;
			
		case (KeyEvent.VK_LEFT):
			setAlpha(getAlpha() - Math.PI / 10);
				break;
				
		case (KeyEvent.VK_RIGHT):
			setAlpha(getAlpha() + Math.PI / 10);
				break;
		}
		
	}
	public void WhatToDoWithoutBall(Ball b, SoccerField sf) {

		if (T_S.equals(Team.sides.LEFT_SIDE)) {
			if(sf.getAtackDownLeft().contains(b.getX(),b.getY())){
				
			alpha = Math.atan2(b.getY()-cy, b.getX()-cx);
				
				double dx,dy;
				dy = Math.sin(alpha);
				dx = Math.cos(alpha);
				cx = ( cx+SPEED*RADIUS*dx);
				cy = ( cy+SPEED*RADIUS*dy);
				
			}
		}
			else if (sf.getAtackDownRight().contains(b.getX(),b.getY())){
			alpha = Math.atan2(b.getY()-cy, b.getX()-cx);
				
				double dx,dy;
				dy = Math.sin(alpha);
				dx = Math.cos(alpha);
				cx = ( cx+SPEED*RADIUS*dx);
				cy = ( cy+SPEED*RADIUS*dy);
			}
			
		
		}
}
