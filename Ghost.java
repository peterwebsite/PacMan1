public class Ghost{
	int ghost_r;
	int ghost_c;
	int pacman_r;
	int pacman_c;

	int direction;
	int lastSpot;
	int grid[][];
	final int LEFT=3;
	final int RIGHT=2;
	final int UP=0;
	final int DOWN=1;
	final int PACMAN=1;
	final int EMPTY=0;
	final int WALL=2;
	final int GHOST=3;
	final int PELLET=4;
	final int POWERUP=5;
	int moves[];
	int moves_len;
	public void flip(){
		if(direction==UP)direction=DOWN;
		else if(direction==DOWN)direction=UP;
		else if(direction==LEFT)direction=RIGHT;
		else if(direction==RIGHT)direction=LEFT;

	}

public Ghost(int r, int c, int dir, int g[][],int m[]){
		ghost_r=r;
		ghost_c=c;
		grid=g;
		direction=dir;
		lastSpot=EMPTY;
		moves=m;
		moves_len=0;
	}
	public void chase(int p_r, int p_c, int g[][]){
		pacman_r=p_r;
		pacman_c=p_c;
		grid=g;
		if(ghost_r==pacman_r){
			if(ghost_c<=pacman_c){
				for(int i=ghost_c+1;i<pacman_c;i++){
					if(grid[ghost_r][i]==WALL)return;
					else{
						direction=RIGHT;
					}
				}
			}else if(pacman_c<=ghost_c){
				for(int i=ghost_c-1;i<pacman_c;i++){
					if(grid[ghost_r][i]==WALL)return;
					else{
						direction=LEFT;
					}
				}				
			}
		}
		if (ghost_c==pacman_c){
			if(ghost_r<=pacman_r){
				for(int i=ghost_r+1;i<pacman_r;i++){
					if(grid[i][ghost_c]==WALL)return;
					else{
						direction=DOWN;
					}
				}
			}else if(pacman_c<=ghost_c){
				for(int i=ghost_r-1;i<pacman_r;i++){
					if(grid[ghost_r][i]==WALL)return;
					else{
						direction=UP;
					}
				}				
			}
		}
	}
//public boolean killedPacMan(int pr,int pc){
//	if(grid[ghost_r][ghost_c]==)

public boolean move(){
	if(moves_len!=moves.length){
		direction=moves[moves_len];
		moves_len++;
	}
	if(direction==UP){
		System.out.println("up");
		if(grid[ghost_r-1][ghost_c]==EMPTY || 
			grid[ghost_r-1][ghost_c]==PELLET ||
			grid[ghost_r-1][ghost_c]==POWERUP){
					System.out.println("up1");

		if(lastSpot==EMPTY){
			lastSpot=grid[ghost_r-1][ghost_c];
			grid[ghost_r-1][ghost_c]=GHOST;
			grid[ghost_r][ghost_c]=EMPTY;
					System.out.println("up2");

			ghost_r--;
		}else{
			
			grid[ghost_r-1][ghost_c]=GHOST;
			grid[ghost_r][ghost_c]=lastSpot;
				System.out.println("up3");

			ghost_r--;
			}		
		}else if(grid[ghost_r-1][ghost_c]==WALL){
		System.out.println("flip");

			flip();
		}
		else if(grid[ghost_r-1][ghost_c]==PACMAN){
			return true;
		}

		
		}else if(direction==DOWN){
			System.out.println("DOWN");
		if(grid[ghost_r+1][ghost_c]==EMPTY || 
			grid[ghost_r+1][ghost_c]==PELLET ||
			grid[ghost_r+1][ghost_c]==POWERUP){
		if(lastSpot==EMPTY){
			lastSpot=grid[ghost_r+1][ghost_c];
			grid[ghost_r+1][ghost_c]=GHOST;
			grid[ghost_r][ghost_c]=EMPTY;
			ghost_r++;
		}else{
			grid[ghost_r+1][ghost_c]=GHOST;
			grid[ghost_r][ghost_c]=lastSpot;
			ghost_r++;
			}	
			}else if(grid[ghost_r-1][ghost_c]==WALL){
			flip();
		}
		else if(grid[ghost_r-1][ghost_c]==PACMAN){
			return true;
		}	
	
}else if(direction==LEFT){
	System.out.println("LEFT");
		if(grid[ghost_r][ghost_c-1]==EMPTY || 
			grid[ghost_r][ghost_c-1]==PELLET ||
			grid[ghost_r][ghost_c-1]==POWERUP){
		if(lastSpot==EMPTY){
			lastSpot=grid[ghost_r][ghost_c-1];
			grid[ghost_r][ghost_c-1]=GHOST;
			grid[ghost_r][ghost_c]=EMPTY;
			ghost_c--;
		}else{
			grid[ghost_r][ghost_c-1]=GHOST;
			grid[ghost_r][ghost_c]=lastSpot;
			ghost_c--;
			}	
			}else if(grid[ghost_r-1][ghost_c]==WALL){
			flip();
		}
		else if(grid[ghost_r-1][ghost_c]==PACMAN){
			return true;
		}	



}else if(direction==RIGHT){
	System.out.println("RIGHT");
		if(grid[ghost_r][ghost_c+1]==EMPTY || 
			grid[ghost_r][ghost_c+1]==PELLET ||
			grid[ghost_r][ghost_c+1]==POWERUP){
		if(lastSpot==EMPTY){
			lastSpot=grid[ghost_r][ghost_c+1];
			grid[ghost_r][ghost_c+1]=GHOST;
			grid[ghost_r][ghost_c]=EMPTY;
			ghost_c++;
		}else{
			grid[ghost_r][ghost_c+1]=GHOST;
			grid[ghost_r][ghost_c]=lastSpot;
			ghost_c++;
			}		

}else if(grid[ghost_r-1][ghost_c]==WALL){
			flip();
		}
		else if(grid[ghost_r-1][ghost_c]==PACMAN){
			return true;
		}
}
return false;

}
}
