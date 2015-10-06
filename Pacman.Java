import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


public class Pacman implements KeyListener,ActionListener{
	//pacman 15x15
	//wall
	//ghost
	//8 r0ws 16 col
	ImageIcon eblock=new ImageIcon("black.jpg");
	ImageIcon blocks[]={new ImageIcon("black.jpg"),
						new ImageIcon("man.gif"),
						new ImageIcon("wall.jpg"),
						new ImageIcon("enemy.jpg"),
						new ImageIcon("ball.jpg"),
						new ImageIcon("red.jpg")



};
ImageIcon directions[]={new ImageIcon("pacman-u.jpg"),
						new ImageIcon("pacman-d.jpg"),
						new ImageIcon("pacman-r.jpg"),
						new ImageIcon("pacman-l.jpg")
};
ImageIcon live = new ImageIcon("live.jpg");
final int LEFT=3;
final int RIGHT=2;
final int UP=0;
final int DOWN=1;

	int direction=LEFT;
	int lives=3;
	JFrame window;
	JLabel block;
	int score=0;
	final int PACMAN=1;
	final int EMPTY=0;
	final int WALL=2;
	final int GHOST=3;
	final int PELLET=4;
	final int POWERUP=5;

	int grid[][]={
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
				{2,5,4,4,4,4,4,4,1,4,4,4,4,4,4,5,2},
				{2,4,2,2,2,2,2,4,2,4,2,2,2,2,2,4,2},
				{2,4,4,4,4,4,4,4,2,4,4,4,4,4,4,4,2},
				{2,4,2,4,2,2,2,2,2,2,2,2,2,4,2,4,2},
				{2,4,4,4,4,4,2,4,4,4,2,4,4,4,4,4,2},
				{2,4,2,2,2,4,4,4,4,4,4,4,2,2,2,4,2},		
				{2,4,4,4,2,4,4,2,3,2,4,4,2,4,4,4,2},
				{2,4,2,4,2,4,4,2,3,2,4,4,2,4,2,4,2},
				{2,4,2,4,2,4,4,2,3,2,4,4,2,4,2,4,2},
				{2,4,2,4,4,4,5,2,3,2,5,4,4,4,2,4,2},
				{2,4,2,4,2,2,2,2,2,2,2,2,2,4,2,4,2},
				{2,4,4,4,4,4,4,4,2,4,4,4,4,4,4,4,2},
				{2,4,2,2,2,2,2,4,2,4,2,2,2,2,2,4,2},
				{2,5,4,4,4,4,4,4,2,4,4,4,4,4,4,5,2},
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}


		
	};
	JLabel pGrid[][];
	int pinky_m[]={UP,LEFT,LEFT,LEFT,UP};
	int blinky_m[]={UP,UP,RIGHT,RIGHT,RIGHT,UP,LEFT};
	int clyde_m[]={UP,UP,UP,RIGHT,RIGHT,DOWN};
	int inky_m[]={UP,UP,UP,UP,LEFT,LEFT,LEFT,UP,LEFT};
	Ghost pinky=new Ghost(7,8,UP,grid,pinky_m);
	Ghost blinky=new Ghost(8,8,UP,grid,blinky_m);
	Ghost clyde=new Ghost(9,8,UP,grid,clyde_m);
	Ghost inky=new Ghost(10,8,UP,grid,inky_m);
	Timer time;

	public void actionPerformed(ActionEvent e){
		int pacman_r=getPacmanRow();
		int pacman_c=getPacmanCol();
		pinky.chase(getPacmanRow(),getPacmanCol(),grid);
		blinky.chase(getPacmanRow(),getPacmanCol(),grid);
		clyde.chase(getPacmanRow(),getPacmanCol(),grid);
		inky.chase(getPacmanRow(),getPacmanCol(),grid);
		if(pinky.move()){
			grid[pacman_r][pacman_c]=EMPTY;
			pacman_r=8;
			pacman_c=1;
		}
		
		pinky.move();
		blinky.move();
		clyde.move();
		inky.move();
		paintGrid();
		
	}

	public void keyTyped(KeyEvent e){
		//System.out.println(e.getKeyCode());
		//int value=e.getKeyCode();

	}
	public void keyPressed(KeyEvent e){
			int value=e.getKeyCode();
		System.out.println(e.getKeyCode());
		if(value==38){
			direction=UP;
			int pacman_r=getPacmanRow();
			int pacman_c=getPacmanCol();
			if(pacman_r==0) pacman_r=15;
			if(grid[pacman_r-1][pacman_c]==EMPTY || grid[pacman_r-1][pacman_c]==PELLET || grid[pacman_r-1][pacman_c]==POWERUP) {
			//scoring
				if(grid[pacman_r-1][pacman_c]==PELLET){

				SoundEffect.CHOMP.play();
					score++;}
				else if(grid[pacman_r-1][pacman_c]==POWERUP)score+=10;
			grid[pacman_r-1][pacman_c]=PACMAN;
			if(pacman_r==16) pacman_r=getPacmanRow();
			grid[pacman_r][pacman_c]=EMPTY;
		}
		else if(grid[pacman_r-1][pacman_c]==GHOST){
			lives--;
			if (lives==3)live1.setIcon(new ImageIcon("dead.jpg"));
			if (lives==2)live3.setIcon(new ImageIcon("dead.jpg"));
			if (lives==1)live2.setIcon(new ImageIcon("dead.jpg"));
			grid[pacman_r][pacman_c]=EMPTY;
			if(lives==0) System.exit(0);
			grid[8][1]=PACMAN;
		}
			
}

		
		else if(value==40){
			direction=DOWN;
			int pacman_r=getPacmanRow();
			int pacman_c=getPacmanCol();
			if(grid[pacman_r+1][pacman_c]==EMPTY || grid[pacman_r+1][pacman_c]==PELLET || grid[pacman_r+1][pacman_c]==POWERUP){
			if(grid[pacman_r+1][pacman_c]==PELLET){

				SoundEffect.CHOMP.play();
					score++;}
				else if(grid[pacman_r+1][pacman_c]==POWERUP)score+=10;
			grid[pacman_r+1][pacman_c]=PACMAN;
			if(pacman_r==15) pacman_r=getPacmanRow();
			grid[pacman_r][pacman_c]=EMPTY;
		}
		else if(grid[pacman_r+1][pacman_c]==GHOST){
			lives--;
			if (lives==3)live1.setIcon(new ImageIcon("dead.jpg"));
			if (lives==2)live3.setIcon(new ImageIcon("dead.jpg"));
			if (lives==1)live2.setIcon(new ImageIcon("dead.jpg"));
			grid[pacman_r][pacman_c]=EMPTY;
			if(lives==0) System.exit(0);
			grid[8][1]=PACMAN;
		}
		}
		else if(value==37){
			direction=LEFT;
			int pacman_r=getPacmanRow();
			int pacman_c=getPacmanCol();
			if(grid[pacman_r][pacman_c-1]==EMPTY || grid[pacman_r][pacman_c-1]==PELLET || grid[pacman_r][pacman_c-1]==POWERUP){
			if(grid[pacman_r][pacman_c-1]==PELLET){

				SoundEffect.CHOMP.play();
					score++;}
				else if(grid[pacman_r][pacman_c-1]==POWERUP)score+=10;
			grid[pacman_r][pacman_c-1]=PACMAN;
			if(pacman_c==17) pacman_c=getPacmanCol();
			grid[pacman_r][pacman_c]=EMPTY;
		}
		else if(grid[pacman_r][pacman_c-1]==GHOST){
			lives--;
			if (lives==3)live1.setIcon(new ImageIcon("dead.jpg"));
			if (lives==2)live3.setIcon(new ImageIcon("dead.jpg"));
			if (lives==1)live2.setIcon(new ImageIcon("dead.jpg"));
			grid[pacman_r][pacman_c]=EMPTY;
			if(lives==0) System.exit(0);
			grid[8][1]=PACMAN;
		}
		}
		else if(value==39){
			direction=RIGHT;
			int pacman_r=getPacmanRow();
			int pacman_c=getPacmanCol();
			if(grid[pacman_r][pacman_c+1]==EMPTY || grid[pacman_r][pacman_c+1]==PELLET || grid[pacman_r][pacman_c+1]==POWERUP){
			if(grid[pacman_r][pacman_c+1]==PELLET){

				SoundEffect.CHOMP.play();
					score++;}
				else if(grid[pacman_r][pacman_c+1]==POWERUP)score+=10;
			grid[pacman_r][pacman_c+1]=PACMAN;
			if(pacman_c==16) pacman_c=getPacmanCol();
			grid[pacman_r][pacman_c]=EMPTY;
		}
		else if(grid[pacman_r][pacman_c+1]==GHOST){
			lives--;
			if (lives==3)live1.setIcon(new ImageIcon("dead.jpg"));
			if (lives==2)live3.setIcon(new ImageIcon("dead.jpg"));
			if (lives==1)live2.setIcon(new ImageIcon("dead.jpg"));
			
			grid[pacman_r][pacman_c]=EMPTY;
			if(lives==0) System.exit(0);
			grid[8][1]=PACMAN;
		}
	}
		System.out.println("score" + score);
		paintGrid();

	}
	public int getPacmanRow(){
		for(int i=0; i<16; i++){
		for(int j=0; j<17; j++){
			if(grid[i][j]==1)
				return i;
	}
}
return 0;
}
public int getPacmanCol(){
		for(int i=0;i<16;i++){
		for(int j=0;j<17;j++){
			if(grid[i][j]==1)
				return j;
	}
} 
return 0;
}
	public void keyReleased(KeyEvent e){
		
	}
	public void paintGrid(){
		score_txt.setText(""+score);
		if(score==183){
			JOptionPane.showMessageDialog(null,"WIN");
			System.exit(0);
		}
		for(int i=0;i<16;i++){
		for(int j=0;j<17;j++){
			if(grid[i][j]==PACMAN){		
			 if(direction==LEFT)pGrid[i][j].setIcon(directions[LEFT]);
				else if(direction==RIGHT)pGrid[i][j].setIcon(directions[RIGHT]);
				else if(direction==UP)pGrid[i][j].setIcon(directions[UP]);
				else if(direction==DOWN)pGrid[i][j].setIcon(directions[DOWN]);


			}
			else{
				pGrid[i][j].setIcon(blocks[grid[i][j]]);

			}
			
			window.repaint();
		}}
	}
	JPanel body;
	JPanel top;
	JLabel score_lbl= new JLabel("score");
	JLabel score_txt= new JLabel("000");
	JLabel live1= new JLabel();
	JLabel live2= new JLabel();
	JLabel live3= new JLabel();
	AudioInputStream audioIn;
	URL url;


public Pacman(){
	SoundEffect.init();
	SoundEffect.volume= SoundEffect.Volume.LOW;
	score_lbl.setForeground(Color.white);
	score_txt.setForeground(Color.white);

	top = new JPanel();
	top.setBackground(Color.black);
	top.setLayout(new GridLayout(1,5));
	top.add(score_lbl);
	top.add(score_txt);
	live1.setIcon(live);
	live2.setIcon(live);
	live3.setIcon(live);
	top.add(live1);
	top.add(live2);
	top.add(live3);

	body = new JPanel();

	//ImageIcon eblock=new ImageIcon("black.jpg");
	window=new JFrame("Pacman");
	window.addKeyListener(this);
	window.setLayout(new BorderLayout());
	body.setLayout( new GridLayout(16,17));
	//window.setLayout(new GridLayout(16,17));
	//block=new JLabel(eblock);
	pGrid=new JLabel[16][17];
	for(int i=0;i<16;i++){

		for(int j=0;j<17;j++){
			pGrid[i][j]=new JLabel(blocks[grid[i][j]]);
			body.add(pGrid[i][j]);
	}
	
	}
	// add the block to window
	//window.add(block);
	//window.setSize(300,300);

	window.add(body, BorderLayout.CENTER);
	window.add(top,BorderLayout.NORTH);
	window.pack();
	window.setVisible(true);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	time=new Timer(1000,this);
	time.setRepeats(true);	
	time.start();


}
public static void main(String args[]){
	new Pacman();
}
}
