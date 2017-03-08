import java.util.*;
public class Pipe {
	static ArrayList<Pipe> pipes = new ArrayList<>();
	private int x;
	private int y;
	private int height;
	private boolean counted = false; //Updating score
	static int width = 50;
	public Pipe(int x, int y, int height){
		this.x = x;
		this.y = y;
		this.height = height;
	}
	public static void update(){
		for(int i = 0; i < pipes.size(); i++){
			pipes.get(i).setX(pipes.get(i).getX()-2);
		}
		int removed = 0;
		ArrayList<Pipe> delete = new ArrayList<>();
		for(int i = 0; i < pipes.size(); i++){
			Pipe z = pipes.get(i);
			if(z.getX()+width <= 0){
				delete.add(z);
				removed+=1;
			}
		}
		if(removed == 2) generate(Flappy_Bird.width);
		for(Pipe p : delete) pipes.remove(p);
	}
	public static void generate(int x){
		int h = (int)(Math.random() * (Flappy_Bird.height-350)+50);
		pipes.add(new Pipe(x, 0, h));
		pipes.add(new Pipe(x, h+200, Flappy_Bird.height-h-200));
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setHeight(int h){
		height = h;
	}
	public void setCounted(boolean b){
		counted = b;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getHeight(){
		return height;
	}
	public boolean getCounted(){
		return counted;
	}
}