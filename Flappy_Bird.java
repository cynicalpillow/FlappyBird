import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Flappy_Bird{
	//pretty simple flappy bird game
	static JFrame f;
	static DrawPanel p;
	static Player pa;
	static int width = 700;
	static int height = 700;
	static int score = 0;
	static int highScore = 0;

	public static void init(){
		score = 0;
		f = new JFrame();
		p = new DrawPanel();
		p.addKeyListener(p);
		pa = new Player(100, 350, 15, 30);
		Pipe.pipes.clear();
		Pipe.generate(350);
		Pipe.generate(700);
		f.setSize(width, height);
		p.setFocusable(true);
		f.add(p);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static class DrawPanel extends JPanel implements KeyListener{
		public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            for(int i = 0; i < Pipe.pipes.size(); i++){
            	g.fillRect(Pipe.pipes.get(i).getX(), Pipe.pipes.get(i).getY(), Pipe.pipes.get(i).width, Pipe.pipes.get(i).getHeight());
            }
            g.fillRect(pa.getX(), pa.getY(), pa.getWidth(), pa.getWidth());
            g.setColor(Color.blue);
            Font f = new Font ("Monospaced", Font.BOLD, 30);
            g.setFont(f);
            g.drawString(String.valueOf(score), 10, 30);
            g.setColor(Color.red);
            f = new Font ("Monospaced", Font.BOLD, 30);
            g.setFont(f);
            g.drawString(String.valueOf(highScore), width-45, 30);
        }
      	public void keyPressed(KeyEvent e){
      		if(e.getKeyCode() == KeyEvent.VK_SPACE){
      			pa.setYVel(15);
      		} else if(e.getKeyCode() == KeyEvent.VK_R){
      			if(pa.getStatus()){
      				pa = new Player(100, 350, 15, 30);
					Pipe.pipes.clear();
					Pipe.generate(350);
					Pipe.generate(700);
					score = 0;
      			}
      		} else if(e.getKeyCode() == KeyEvent.VK_X){
      			System.exit(0);
      		}
      	}
	    public void keyReleased(KeyEvent e){}
	    public void keyTyped(KeyEvent e){}
	}
	public static void update(){
		pa.update();
		Pipe.update();
		f.getToolkit().sync();
		p.repaint();
		pa.collide();
		updateScore();
		highScore = Math.max(highScore, score);
		try{
			Thread.sleep(15);
		}catch(Exception e){}
	}
	public static void updateScore(){
		boolean inc = false;
		for(Pipe p : Pipe.pipes){
			if(p.getX() + p.width <= 100 && !p.getCounted()){
				inc = true;
				p.setCounted(true);
			}
		}
		if(inc)score++;
	}
	public static void main(String args[]){
		init();
		while(true){
			while(!pa.getStatus()){
				update();
			}
			try{
				Thread.sleep(15);
			}catch(Exception e){}
		}
	}
}