import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AtackUp extends Players {

	
	public AtackUp(Color c, Team.sides s, int num) {
		super(c, s);
		this.num = num;
	}
	@Override
	public void goToInitialPosition(int w, int h) {
		if (T_S.equals(Team.sides.LEFT_SIDE)) {
			setCenter(w / 3+w/3, h / 4);
			this.alpha = 0;
		} else {
			setCenter(w / 3, h / 4);
			this.alpha = -Math.PI;
		}

	}
	

	public void WhatToDoWithBall(Ball b, KeyEvent e) {
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_UP):
			double dx =	getPDirX();
			double dy = getPDirY();
		
			cx = cx+(RADIUS*1.5*dx);
			cy = cy+(RADIUS*1.5*dy);
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

			cx = cx-(RADIUS*1.5*dx);
			cy = cy-(RADIUS*1.5*dy);
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
			if(sf.getAtackUpLeft().contains(b.getX(),b.getY())&&!PlayerGotBall){
				
			alpha = Math.atan2(b.getY()-cy, b.getX()-cx);
				
				double dx,dy;
				dy = Math.sin(alpha);
				dx = Math.cos(alpha);
				cx = ( cx+SPEED*RADIUS*dx);
				cy = ( cy+SPEED*RADIUS*dy);
				
			}
		}
			else if (sf.getAtackUpRight().contains(b.getX(),b.getY())&&!PlayerGotBall){
			alpha = Math.atan2(b.getY()-cy, b.getX()-cx);
				
				double dx,dy;
				dy = Math.sin(alpha);
				dx = Math.cos(alpha);
				cx = ( cx+SPEED*RADIUS*dx);
				cy = ( cy+SPEED*RADIUS*dy);
			}
			
		
		}
	
//	@Override
//	public void DrawMe(Graphics g) {
//		super.DrawMe(g);
//
//			Graphics2D g2d = (Graphics2D) g;
//			BufferedImage bi = null;
//			try{
//				bi = ImageIO.read(new File("messi.jpg"));
//			}
//			catch(IOException ex){
//				System.out.println("File messi.jpg was not found\n");
//			}
//			TexturePaint tp = new TexturePaint(bi,
//											new Rectangle((int)cx+13,(int)cy+15,25,25));
//			g2d.setPaint(tp);
//			
////			g.setColor(Color.GRAY);
//			g2d.fillOval((int)cx-RADIUS,(int) cy-RADIUS, 2*RADIUS, 2*RADIUS);
//			g2d.setColor(Color.BLACK);
//			g2d.drawOval((int)cx-RADIUS, (int)cy-RADIUS, 2*RADIUS, 2*RADIUS);
//			g2d.dispose();
//		
//	}

}
