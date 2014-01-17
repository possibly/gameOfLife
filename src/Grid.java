import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Grid {
	
	boolean gameOver;
	ArrayList<ArrayList<String>> grid = new ArrayList<ArrayList<String>>();
	
	public void setGrid(){
		System.out.println("Sample file location: /home/tyler/words.txt");
		System.out.print("Enter your file location: ");
		Scanner s = new Scanner(System.in);
		String fileLoc = s.next();	
		try {
			s = new Scanner(new File(fileLoc));
			
			while (s.hasNext()){
				String temp = s.next();
				ArrayList<String> innerGrid = new ArrayList<String>();
				//System.out.println("Temp length: "+temp.length());
		
				for(int i = 0; i < temp.length();i++){
					innerGrid.add(new String(temp.substring(i, i+1)));
				}
				grid.add(innerGrid);
			} 
		
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND, TRY AGAIN.");
			System.out.println("");
			setGrid();
		}
	}
	
	public void displayGrid(){
		gameOver = true;
		
		for(int y = 0; y < grid.size(); y++){
			for(int x = 0; x < grid.get(y).size(); x++){
				if(grid.get(y).get(x).equals("X"))
					gameOver = false;
				System.out.print(grid.get(y).get(x));
			}
			System.out.println("");
		}	
	}
	
	/*RULES:
	    *Any live cell with fewer than two live neighbours dies, as if caused by under-population.
	    *Any live cell with two or three live neighbours lives on to the next generation.
	    *Any live cell with more than three live neighbours dies, as if by overcrowding.
	    *Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
		*/
	public boolean play(){
		ArrayList<ArrayList<String>> newGrid = new ArrayList<ArrayList<String>>();
		ArrayList<String> newInnerList = new ArrayList<String>();
		
		for(int y = 0; y < grid.size(); y++){
			for(int x = 0; x < grid.get(y).size(); x++){
				String cell = grid.get(y).get(x);
				int aliveNeighbors = 0;
				int deadNeighbors = 0;
				
				if(cell.equals("O")){
					newInnerList.add("O");
				}
				
				for(String neighbor: getNeighbors(y,x)){
						if(neighbor.equals("."))
							deadNeighbors++;
						if(neighbor.equals("X"))
							aliveNeighbors++;
				}
				
				if(cell.equals("X") && aliveNeighbors < 2)
					newInnerList.add(".");
				else if(cell.equals("X") && aliveNeighbors == 2){
					newInnerList.add("X");
					//System.out.println(cell+" at: "+x+" : "+y+" stayed alive.");
				}
				else if(cell.equals("X") && aliveNeighbors == 3){
					newInnerList.add("X");
					//System.out.println(cell+" at: "+x+" : "+y+" stayed alive.");
				}
				else if(cell.equals("X") && aliveNeighbors > 3)
					newInnerList.add(".");
				else if(cell.equals(".") && aliveNeighbors == 3)
					newInnerList.add("X");
				else if(cell.equals(".") && aliveNeighbors != 3)
					newInnerList.add(".");

			}
			newGrid.add(newInnerList);
			newInnerList = new ArrayList<String>();
		}
		grid = newGrid;
		displayGrid();
		return gameOver;
	}
	
	public ArrayList<String> getNeighbors(int y, int x){
		ArrayList<String> neighbors = new ArrayList<String>();
		
		try{
			neighbors.add(grid.get(y).get(x-1)); //left
			neighbors.add(grid.get(y-1).get(x-1)); //topleft
			neighbors.add(grid.get(y-1).get(x)); //top
			neighbors.add(grid.get(y-1).get(x+1)); //topright
			neighbors.add(grid.get(y).get(x+1)); //right
			neighbors.add(grid.get(y+1).get(x+1)); //bottomright
			neighbors.add(grid.get(y+1).get(x)); //bottom
			neighbors.add(grid.get(y+1).get(x-1)); //bottomleft
		}catch (Exception e){
			
		}
		
		//System.out.println(grid.get(y).get(x)+" at "+x+" : "+y);
		//System.out.println(neighbors);
		return neighbors;
	}
}
