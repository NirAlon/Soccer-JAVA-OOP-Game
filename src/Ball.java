import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball implements sheard_function{
	public final int RADIUS = 7;
	private double bx,by;
	private double alpha;
	private double ball_speed;
	private boolean move;
	private boolean touch;
	
	public boolean isTouch() {
		return touch;
	}


	public void setTouch(boolean touch) {
		this.touch = touch;
	}


	public Ball(){
		ball_speed = 0.5;
		alpha = 0;
		move=true;
	}
	

	public void goToInitialPosition(int w, int h) {
		bx = w/2;
		by = h/2;
		
	}


	
	public void drawMe(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage bi = null;
		try{
			bi = ImageIO.read(new File("ball.jpg"));
		}
		catch(IOException ex){
			System.out.println("File ball.jpg was not found\n");
		}
		TexturePaint tp = new TexturePaint(bi,
										new Rectangle((int)bx+10,(int)by,50,50));
		g2d.setPaint(tp);
		
//		g.setColor(Color.GRAY);
		g2d.fillOval((int)bx-RADIUS,(int) by-RADIUS, 2*RADIUS, 2*RADIUS);
		g2d.setColor(Color.BLACK);
		g2d.drawOval((int)bx-RADIUS, (int)by-RADIUS, 2*RADIUS, 2*RADIUS);
		g2d.dispose();
	}
	public double getX(){
		return bx;
	}
	public double getY(){
		return by;
	}
	public void setCenter(double x, double y){
		bx = x;
		by = y;
	}
	// return the x-component of ball direction
	public double dirX(){
		return Math.cos(alpha);
	}
	
	
	// change angle when ball meets borders 
	public void checkAngle(SoccerField sf){
		double dx,dy;
		Rectangle r = sf.getBorders();
		
		dx = Math.cos(alpha);
		dy = Math.sin(alpha);
		
		if(dx>0){ // check right border
			if(bx+RADIUS+dx*ball_speed>r.getMaxX()){
				alpha = Math.PI-alpha;
				ball_speed-=0.1;
			setTouch(true);
			}
		}
		else{ // check left border
			if(bx-RADIUS+dx*ball_speed<r.getMinX()){
				alpha = Math.PI-alpha;
				ball_speed-=0.1;
				setTouch(true);
			}
	}
		if(dy>0){  // check bottom border
			if(by+RADIUS+dy*ball_speed>r.getMaxY()){
				alpha = -alpha;
				ball_speed-=0.1;
			}
		}
		else{ // check top border
			if(by-RADIUS+dy*ball_speed<r.getMinY()){
				alpha = -alpha;
				ball_speed-=0.1;
			}
			
		}
		
	}

	public void setAlpha(double angle) {
		alpha = angle;
		
	}


	public double dirY() {
		// TODO Auto-generated method stub
		 return Math.sin(alpha);
	}


	public void move() {
		bx = bx+(dirX()*RADIUS*ball_speed);
		by = by+(dirY()*RADIUS*ball_speed);
		
		
	}


	public void setX(double cx) {
		bx = cx;
		
	}


	public void setY(double cy) {
		by = cy;
		
	}


	public void setNotMove(boolean b) {
		move=b;
		
	}


	public boolean getMove() {
		// TODO Auto-generated method stub
		return move;
	}


	public void Shot() {
		bx = bx+(dirX()*RADIUS*ball_speed)*5;
		by = by+(dirY()*RADIUS*ball_speed)*5;
		ball_speed+=0.02;
		setNotMove(true);
		
		
	}


	public double getAlpha() {
		// TODO Auto-generated method stub
		return alpha;
	}
	public void setBallSpeed(){
		ball_speed = 0.5;
	}

}

