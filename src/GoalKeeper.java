import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class GoalKeeper extends Players {

	public GoalKeeper(Color c, Team.sides s, int num) {
		super(c, s);
		this.num = num;
	}

	@Override
	public void goToInitialPosition(int w, int h) {
		if (T_S.equals(Team.sides.LEFT_SIDE)) {
			setCenter(30, h / 2);
			this.alpha = 0;
		} else {
			setCenter(w - 30, h / 2);
			this.alpha = -Math.PI;
		}
	}

	public void WhatToDoWithoutBall(Ball b,SoccerField sf) {
		
		if(cy<sf.getLeftGateBorders().y){
			cy = sf.getLeftGateBorders().y;
		}
		else if(cy>sf.getLeftGateBorders().y+sf.getLeftGateBorders().height){
			cy=sf.getLeftGateBorders().y+sf.getLeftGateBorders().height;
		}
		
		
			if (sf.getLeftSide().contains(b.getX(),b.getY())&&T_S.equals(Team.sides.LEFT_SIDE)) {
				if(cy<sf.getLeftGateBorders().y){
					cy = sf.getLeftGateBorders().y;
				}
				
				if (cy < b.getY()) {
					cy = cy + (b.getY()-cy)/2;
				} else {
					cy = cy - (cy-b.getY())/2;
				}
			
		} // end left side
		else // right side
			if (sf.getRightSide().contains(b.getX(),b.getY())&&T_S.equals(Team.sides.RIGHT_SIDE)) {
				
				if (cy < b.getY()) {
					cy = cy + (b.getY()-cy)/2;
				} else {
					cy = cy - (cy-b.getY())/2;
				
			}
			
			
		}

	}
	
	public double getGKX(){
		return cx;
	}
	public double getGKY(){
		return cy;
	}

	public void WhatToDoWithBall(Ball b, KeyEvent e) {
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_UP):
			double dx =	getPDirX();
			double dy = getPDirY();
		
			//cx = cx+(RADIUS*SPEED*dx);
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

			//cx = cx-(RADIUS*SPEED*dx);
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


}
