import java.util.*;
public class TicTactoe {
	
	static ArrayList<Integer> playerpositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpupositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		char [] [] gameboard = {{' ','|',' ','|',' '},
								{'-','+','-','+','-'},
								{' ','|',' ','|',' '},
								{'-','+','-','+','-'},
								{' ','|',' ','|',' '}};
		printGameboard(gameboard);
		
		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your placement (1-9) : ");
			int pos = scan.nextInt();
			while(playerpositions.contains(pos) || cpupositions.contains(pos)) {
				System.out.println("position already taken , please enter correct position");
				pos = scan.nextInt();
			}
			
			placepiece(gameboard,pos,"player");
			String result = checkWin();
			if(result.length()>0) {
				printGameboard(gameboard);
				System.out.println(result);
				break;
			}
			
			
			Random rand = new Random();
			int cpupos = rand.nextInt(9)+1;
			while(playerpositions.contains(cpupos) || cpupositions.contains(cpupos)) {
				cpupos = rand.nextInt(9)+1;
			}
			placepiece(gameboard , cpupos , "cpu");
			printGameboard(gameboard);
			
			result = checkWin();
			if(result.length()>0) {
				printGameboard(gameboard);
				System.out.println(result);
				break;
			}
			
		}

	}
		
	public static void printGameboard(char[][] gameboard) {
		for(char[] row :gameboard) {
			for(char c :row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placepiece(char[][] gameboard ,int pos , String user) {
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerpositions.add(pos);
		}else if(user.equals("cpu")){
			symbol = 'O';
			cpupositions.add(pos);
		}
		switch(pos){
			case 1:
				gameboard[0][0] = symbol;
				break;
			case 2:
				gameboard[0][2] = symbol;
				break;
			case 3:
				gameboard[0][4] = symbol;
				break;
			case 4:
				gameboard[2][0] = symbol;
				break;
			case 5:
				gameboard[2][2] = symbol;
				break;
			case 6:
				gameboard[2][4] = symbol;
				break;
			case 7:
				gameboard[4][0] = symbol;
				break;
			case 8:
				gameboard[4][2] = symbol;
				break;
			case 9:
				gameboard[4][4] = symbol;
				break;	
			default:
				break;
		}
	}
	
	public static String checkWin() {
		
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List lefcol = Arrays.asList(1,4,7);
		List midcol = Arrays.asList(2,5,8);
		List rigcol = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(7,5,3);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(lefcol);
		winning.add(midcol);
		winning.add(rigcol);
		winning.add(cross1);
		winning.add(cross2);
		
		for(List l:winning) {
			if(playerpositions.containsAll(l)) {
				return "Congratulations, You win!";
			}else if(cpupositions.containsAll(l)) {
				return "you Lost , better luck next time";	
			}else if(playerpositions.size()+cpupositions.size() == 9) {
				return "it's a tie";
			}
		}
		
		return"";
	}
	

}
