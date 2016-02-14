import java.util.Scanner;

public class CoffeeMakerQuest {
	
	public static boolean foundCream = false;
	public static boolean foundSugar = false;
	public static boolean foundCoffee = false;
	public static int errorCode = 0;
	public static boolean game = true;
	public static Room currentRoom;
	
	public static Room r1 = new Room("small", "quaint sofa", "creamy cream", null, null);
	public static Room r2 = new Room("funny", "sad record player", null, r1, null);
	public static Room r3 = new Room("refinanced", "tight pizza", "caffeinated coffee", r2, null);
	public static Room r4 = new Room("dumb", "flat energy drink", null, r3, null);
	public static Room r5 = new Room("bloodthirsty", "beautiful bag of money", null, r4, null);
	public static Room r6 = new Room("rough", "perfect air hockey table", "sweet sugar", r5, null);

	public static void main(String[] args) {
		
		r1.setNextRoom(r2);
		r2.setNextRoom(r3);
		r3.setNextRoom(r4);
		r4.setNextRoom(r5);
		r5.setNextRoom(r6);
		
		Scanner sc = new Scanner(System.in);
		String input;
		currentRoom = r1;
		
		System.out.println("Coffee Maker Quest 2.0");
		
		while(game){
			System.out.println(dispRoom(currentRoom));
			input = sc.next();
			currentRoom = performAction(input, currentRoom);
		}
		sc.close();
		System.out.println("Exiting with error code " + errorCode);
	}
	
	/**
	 * Returns a string that displays whether the player has found the coffee, cream, and sugar,
	 * based on whether the items have been removed yet from their respective rooms.
	 */
	public static String dispInventory(Room creamRoom, Room coffeeRoom, Room sugarRoom){
		
		String res = "";
		
		if(coffeeRoom.getItem() == null){
			res += "You have a cup of delicious coffee.";
		}
		else{
			res += "YOU HAVE NO COFFEE!";
		}
		res += "\n";
		if(creamRoom.getItem() == null){
			res += "You have some fresh cream.";
		}
		else{
			res += "YOU HAVE NO CREAM!";
		}
		res += "\n";
		if(sugarRoom.getItem() == null){
			res += "You have some tasty sugar.";
		}
		else{
			res += "YOU HAVE NO SUGAR!";
		}
		res += "\n";
		return res;
	}
	
	/**
	 * Returns a string of the appropriate information for the current room and the possible actions.
	 */
	public static String dispRoom(Room currentRoom){
		String res = "";
		res += "\nYou see a " + currentRoom.getAdj() + " room.\n";
		res += "It has a " + currentRoom.getFurn() + ".\n";
		if(currentRoom.hasNextRoom()){
			res += "A door leads north.\n";
		}
		if(currentRoom.hasPrevRoom()){
			res += "A door leads south.\n";
		}
		res += "\n INSTRUCTIONS (N,S,L,I,H,D) >";
		return res;
	}
	
	/**
	 * Takes the input from the player and performs the appropriate action given the current room.
	 * Returns the new current room.
	 */
	public static Room performAction(String input, Room curroom){
		
		input = input.toLowerCase();

		if(input.equals("n")){
			if(curroom.hasNextRoom()){
				curroom = curroom.getNext();
			}
			else{
				System.out.println("There is no door to the north.");
			}
		}
		else if(input.equals("s")){
			if(curroom.hasPrevRoom()){
				curroom = curroom.getPrev();
			}
			else{
				System.out.println("There is no door to the south.");
			}
		}
		else if(input.equals("l")){
			if(curroom.getItem() != null){
				System.out.println("You found some " + curroom.getItem() + "!");
				if(curroom.getItem().equals("caffeinated coffee")){
					foundCoffee = true;
				}
				else if(curroom.getItem().equals("creamy cream")){
					foundCream = true;
				}
				else{
					foundSugar = true;
				}
				curroom.removeItem();
			}
			else{
				System.out.println("You don't see anything out of the ordinary.");
			}
		}
		else if(input.equals("i")){
			System.out.println(dispInventory(r1, r3, r6));
		}
		else if(input.equals("h")){
			System.out.println("Commands:\n");
			System.out.println("\'N\' or \'n\': move to the room to the north");
			System.out.println("\'S\' or \'s\': move to the room to the south");
			System.out.println("\'L\' or \'l\': search for hidden items and pick up anything found");
			System.out.println("\'I\' or \'i\': check inventory");
			System.out.println("\'H\' or \'h\': display help");
			System.out.println("\'D\' or \'d\': drink - game is won if all 3 ingredients have been found, lost otherwise");
		}
		else if(input.equals("d")){
			System.out.println(dispInventory(r1, r3, r6));
			if(foundCoffee && foundCream && foundSugar){
				System.out.println("You drink the beverage and are ready to study!\nYou win!");
			}
			else if(!foundCoffee && foundCream && foundSugar){
				System.out.println("You drink the sweetened cream, but without caffeine, you cannot study.\nYou lose!");
				errorCode = 1;
			}
			else if(!foundCoffee && foundCream && !foundSugar){
				System.out.println("You drink the cream, but without caffeine, you cannot study.\nYou lose!");
				errorCode = 1;
			}
			else if(foundCoffee && !foundCream){
				System.out.println("Without cream, you get an ulcer and cannot study.\nYou lose!");
				errorCode = 1;
			}
			else if(!foundCoffee && !foundCream && foundSugar){
				System.out.println("You eat the sugar, but without caffeine, you cannot study.\nYou lose!");
				errorCode = 1;
			}	
			else if(foundCoffee && foundCream && !foundSugar){
				System.out.println("Without sugar, the coffee is too bitter. You cannot study.\nYou lose!");
				errorCode = 1;
			}	
			else if(!foundCoffee && !foundCream && !foundSugar){
				System.out.println("You drink the air, as you have no coffee, sugar, or cream.");
				System.out.println("The air is invigorating, but not invigorating enough. You cannot study.\nYou lose!");
				errorCode = 1;
			}
			game = false;
		}
		else{
			System.out.println("What?");
		}
		return curroom;
	}

}
