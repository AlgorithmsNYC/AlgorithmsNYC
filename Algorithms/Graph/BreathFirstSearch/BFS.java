package nyc.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	
	private int[][] maze = 
		   {{1,1,1,1,1,1,1},
			{1,0,1,0,0,0,1},
			{1,0,1,0,0,0,1},
			{1,0,0,0,1,0,1},
		 	{1,1,1,1,1,1,1}};
	private int[][] distancefromstart;
	private int[][] directions = {{0,1},{-1,0},{1,0},{0,-1}};
	//private int goalXPos = 5,goalYPos = 3;
	private Cell goal = new Cell(3,5);
	private Queue<Cell> visited = new LinkedList<Cell>();
	private Queue<Cell> frontier = new LinkedList<Cell>();
	public BFS(){
		
		init();
	}

	private void init() {
		
		 distancefromstart = new int[maze.length][maze[0].length];
		 for(int i=0;i<distancefromstart.length;i++){
			 for(int j=0;j<distancefromstart[i].length;j++)
				 distancefromstart[i][j]=0;
		 }
	}
	

	public int shortedDistance(int xCoordinate,int yCoordinate){
		//maze[xCoordinate][yCoordinate]
		Cell curPos = new Cell(xCoordinate,yCoordinate);
		//visited.add(curPos);
		frontier.add(curPos);
		while(!isGoal(curPos)){
			Cell toBeProcessed = frontier.poll();
			for(int k=0;k<directions.length;k++){
				int nextX = directions[k][0]+curPos.getxPos();
				int nextY = directions[k][1]+curPos.getyPos();
				//System.out.println("Next coordinates " + nextX + " : " + nextY);
				//System.out.println(maze[nextX][nextY]);
				if(maze[nextX][nextY]==0 && !inVisited(nextX,nextY)){
					frontier.add(new Cell(nextX,nextY));
					distancefromstart[nextX][nextY] = distancefromstart[curPos.getxPos()][curPos.getyPos()]+1;
				}
				
			}
			visited.add(toBeProcessed);
			curPos = toBeProcessed;
		}
		return distancefromstart[curPos.getxPos()][curPos.getyPos()];
	}

	private boolean inVisited(int nextX, int nextY) {
		Cell cell2Check = new Cell(nextX,nextY);
		return visited.contains(cell2Check);
	}

	private boolean isGoal(Cell curPos) {
		return curPos.equals(goal);
	}
	
	public static void main(String[] args){
		BFS bfs = new BFS();
		System.out.println(bfs.shortedDistance(1, 1));
	}
}
class Cell {
	private int xPos,yPos;
	public Cell(int xPos,int yPos){
		this.setxPos(xPos);
		this.setyPos(yPos);
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Cell))
			return false;
		Cell oCell = (Cell)o;
		return this.xPos == oCell.xPos && this.yPos == oCell.yPos;
	}
	
	@Override
	public int hashCode(){
		return (xPos*yPos)%19;
	}
}
