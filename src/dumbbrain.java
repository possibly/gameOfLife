import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class dumbbrain {
	
	public static void main(String[] args) {
		//tester t = new tester();
		Grid grid = new Grid();
		int tick = 0;
		
		grid.setGrid();
		grid.displayGrid();
		System.out.println("Tick: "+tick);
		
		while(grid.play() == false){
			tick++;
			System.out.println("Tick: "+tick);
			if(tick > 300){
				System.out.println("I get the feeling that this will go on forever so im gonna stop...");
				break;
			}
		}
		System.out.println("Game over.");
	}
	
}
