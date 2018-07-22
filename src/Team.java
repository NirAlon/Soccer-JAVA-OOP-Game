import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Team {

	public enum sides {
		LEFT_SIDE, RIGHT_SIDE
	};

	protected Color team_Color;
	protected final int MAX_PLAYERS = 5;
	protected sides teamSide;
	private Players[] players;
	public boolean TeamGotBall;
	private Timer t;
	private int result;
	public boolean TeamGotControl;

	public Team(Color c, sides s,boolean b) {
		team_Color = c;
		teamSide = s;
		players = new Players[MAX_PLAYERS];
		players[0] = new GoalKeeper(c, teamSide, 0);
		players[1] = new DefTop(c, teamSide, 1);
		players[2] = new DefDown(c, teamSide, 2);
		players[3] = new AtackUp(c, teamSide, 3);
		players[4] = new AtackDown(c, teamSide, 4);
		
		TeamGotControl = b;
		t = new Timer();
		result = 0;

	}

	public void DrawMe(Graphics g) {
		for (int i = 0; i < players.length; i++) {
			players[i].DrawMe(g);
		}
		if (teamSide.equals(Team.sides.LEFT_SIDE)) {
			g.drawString(""+result, 50, 50);
		}
		else{
			g.drawString(""+result, 600, 50);
		}
	}

	public void goToInitialSide(int w, int h) {
		for (int i = 0; i < players.length; i++) {
			players[i].goToInitialPosition(w, h);
		}
	}

	public void GotBall(Ball b) {
		for (int i = 0; i < players.length; i++) {
			if (players[i].T_S.equals(Team.sides.LEFT_SIDE)) {
				if (( players[i]).playerRec().contains(b.getX(), b.getY())) {
					b.setX(players[i].getX());
					b.setY(players[i].getY());
					b.setNotMove(false);
					TeamGotBall = true;
					players[i].setPlayerGotBall(true);
				}
			}
		}
		for (int i = 0; i < players.length; i++) {
			if (players[i].T_S.equals(Team.sides.RIGHT_SIDE)) {
				if (( players[i]).playerRec().contains(b.getX(), b.getY())) {
					b.setX(players[i].getX());
					b.setY(players[i].getY());
					b.setNotMove(false);
					TeamGotBall = true;
					players[i].setPlayerGotBall(true);
				}
			}
		}
			if (b.getMove()) {
				TeamGotBall = false;
				for (int i = 0; i < players.length; i++) {
					players[i].setPlayerGotBall(false);
				}

			}
		

	}

	public void knowWhatToDoWithoutControl(Ball b, SoccerField sf) {

		for (int i = 0; i < players.length; i++) {
			int a = (int) (Math.random()*4);
			int c = (int) (Math.random()*4);
			switch (i) {
			case (0):

				((GoalKeeper) players[0]).WhatToDoWithoutBall(b, sf);
			if((players[0]).PlayerGotBall&&!TeamGotControl){
				double al=Math.atan2(players[a].getY()-players[0].getY(),players[a].getX()-players[0].getX());
				//if(players[0].T_S.equals(Team.sides.RIGHT_SIDE)){//for control only one player
				b.setAlpha(al);	
			
				b.Shot();
				//}
				
			}
				break;

			case (1):
				 ((DefTop) players[1]).WhatToDoWithoutBall(b,sf);
			if((players[1]).PlayerGotBall&&!TeamGotControl){
				double al=Math.atan2(players[c].getY()-players[1].getY(),players[c].getX()-players[1].getX());
				//if(players[0].T_S.equals(Team.sides.RIGHT_SIDE)){
				b.setAlpha(al);		
				b.Shot();
				//}
				
			}

				break;
				
			case (2):
				 ((DefDown) players[2]).WhatToDoWithoutBall(b,sf);
			if((players[2]).PlayerGotBall&&!TeamGotControl){
				double al=Math.atan2(players[a].getY()-players[2].getY(),players[a].getX()-players[2].getX());
				//if(players[0].T_S.equals(Team.sides.RIGHT_SIDE)){
				b.setAlpha(al);		
				b.Shot();
				//}
				
			}

				break;
			case (3):
				 ((AtackUp) players[3]).WhatToDoWithoutBall(b,sf);
			if((players[3]).PlayerGotBall&&!TeamGotControl){
				double al=Math.atan2(players[c].getY()-players[3].getY(),players[c].getX()-players[3].getX());
				//if(players[0].T_S.equals(Team.sides.RIGHT_SIDE)){
				b.setAlpha(al);		
				b.Shot();
				//}
				
			}

				break;
			case (4):
				 ((AtackDown) players[4]).WhatToDoWithoutBall(b,sf);
			if((players[4]).PlayerGotBall){
				double al;
				if(players[4].T_S.equals(Team.sides.LEFT_SIDE)){
				al=Math.atan2(sf.getLeftGateBorders().y+40-players[4].getY(),sf.getRightGateBorders().x-players[4].getX());
				}
				else{
				al=Math.atan2(sf.getLeftGateBorders().y-players[4].getY(),sf.getLeftGateBorders().x-players[4].getX());
				}
				b.setAlpha(al);		
				b.Shot();
				
			}

				break;
			}

		}

	}

	public void knowWhatToDoWithBall(Ball b, KeyEvent e) {
		
		for (int i = 0; i < players.length; i++) 
		switch (i) {
		case (0):
			if (players[0].PlayerGotBall)
				((GoalKeeper) players[0]).WhatToDoWithBall(b, e);

			break;
		case (1):
			if (players[1].PlayerGotBall)
				((DefTop) players[1]).WhatToDoWithBall(b, e);

			break;
		case (2):
			if (players[2].PlayerGotBall)
				((DefDown) players[2]).WhatToDoWithBall(b, e);

			break;
		case (3):
			if (players[3].PlayerGotBall)
				((AtackUp) players[3]).WhatToDoWithBall(b, e);

			break;
		case (4):
			if (players[4].PlayerGotBall)
				((AtackDown) players[4]).WhatToDoWithBall(b, e);

			break;
		}
	}
	public void setResult(int num){
		result = num;
	}
	public int getResult(){
		return result;
	}
	

}
