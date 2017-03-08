import java.util.*;
public class Player {
	static final int GRAVITY_CONSTANT = 1;
	private int yVel;
	private int x;
	private int y;
	private int width;
	private boolean lost = false;
	public Player(int x, int y, int yVel, int width){
		this.x = x;
		this.y = y;
		this.yVel = yVel;
		this.width = width;
	}
	public void update(){
		y-=yVel;
		y = Math.max(0, y);
		yVel -= GRAVITY_CONSTANT;
	}
	public void collide(){
		if(y+width >= Flappy_Bird.height)lost = true;
		for(Pipe p : Pipe.pipes){
			if(x+width >= p.getX() && x <= p.getX() && y >= p.getY() && y <= p.getY() + p.getHeight()) lost = true;
			if(((x + width >= p.getX() && x + width <= p.getX() + p.width)|| (x >= p.getX() && x <= p.getX() + p.width)) && y+width >= p.getY() && y + width <= p.getY() + p.getHeight()) lost = true;
			if(((x + width >= p.getX() && x + width <= p.getX() + p.width)|| (x >= p.getX() && x <= p.getX() + p.width)) && y <= p.getY() + p.getHeight() && y >= p.getY()) lost = true;
		}
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setYVel(int y){
		yVel = y;
	}
	public void setWidth(int w){
		width = w;
	}
	public void setStatus(boolean b){
		lost = b;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getYVel(){
		return yVel;
	}
	public int getWidth(){
		return width;
	}
	public boolean getStatus(){
		return lost;
	}
}