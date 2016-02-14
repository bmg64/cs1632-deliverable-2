import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;


public class CoffeeMakerQuestTest {

	// Test that dispInventory returns the appropriate strings for various cases of item ownership.
	// If all item rooms have a null item, it should display that the player has all 3 items.
	// If none of the item rooms have a null item, it should display three "don't have" messages.
	// One test for inbewteen values included.
	@Test
	public void testDispInventory() {
		
		Room testCreamRoom = Mockito.mock(Room.class);
		Room testCoffeeRoom = Mockito.mock(Room.class);
		Room testSugarRoom = Mockito.mock(Room.class);
		
		Mockito.when(testCreamRoom.getItem()).thenReturn(null);
		Mockito.when(testCoffeeRoom.getItem()).thenReturn(null);
		Mockito.when(testSugarRoom.getItem()).thenReturn(null);
		String res = CoffeeMakerQuest.dispInventory(testCreamRoom, testCoffeeRoom, testSugarRoom);
		assertEquals(res, "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n");
		
		Mockito.when(testCreamRoom.getItem()).thenReturn("");
		Mockito.when(testCoffeeRoom.getItem()).thenReturn("");
		Mockito.when(testSugarRoom.getItem()).thenReturn("");
		res = CoffeeMakerQuest.dispInventory(testCreamRoom, testCoffeeRoom, testSugarRoom);
		assertEquals(res, "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
		
		Mockito.when(testCreamRoom.getItem()).thenReturn("");
		Mockito.when(testCoffeeRoom.getItem()).thenReturn(null);
		Mockito.when(testSugarRoom.getItem()).thenReturn(null);
		res = CoffeeMakerQuest.dispInventory(testCreamRoom, testCoffeeRoom, testSugarRoom);
		assertEquals(res, "You have a cup of delicious coffee.\nYOU HAVE NO CREAM!\nYou have some tasty sugar.\n");
	}
	
	// Test that dispRoom returns the appropriate strings for various cases.
	// Only print the "A door leads" lines if the room has a door to the north/south.
	@Test
	public void testDispRoom() {
		Room testRoom = Mockito.mock(Room.class);
		Mockito.when(testRoom.getAdj()).thenReturn("test");
		Mockito.when(testRoom.getFurn()).thenReturn("test");
		Mockito.when(testRoom.hasNextRoom()).thenReturn(false);
		Mockito.when(testRoom.hasPrevRoom()).thenReturn(false);
		assertEquals(CoffeeMakerQuest.dispRoom(testRoom), "\nYou see a test room.\nIt has a test.\n\n INSTRUCTIONS (N,S,L,I,H,D) >");
		Mockito.when(testRoom.hasNextRoom()).thenReturn(true);
		assertEquals(CoffeeMakerQuest.dispRoom(testRoom), "\nYou see a test room.\nIt has a test.\nA door leads north.\n\n INSTRUCTIONS (N,S,L,I,H,D) >");
		Mockito.when(testRoom.hasPrevRoom()).thenReturn(true);
		assertEquals(CoffeeMakerQuest.dispRoom(testRoom), "\nYou see a test room.\nIt has a test.\nA door leads north.\nA door leads south.\n\n INSTRUCTIONS (N,S,L,I,H,D) >");
	}
	
	// Test that performAction performs the appropriate actions for inputs that use Room.
	// Includes a check for case-insensitivity and a check for invalid input.
	@Test
	public void testPerformAction() {
		
		Room testRoom1 = Mockito.mock(Room.class);
		Room testRoom2 = Mockito.mock(Room.class);
		Mockito.when(testRoom1.getNext()).thenReturn(testRoom2);
		Mockito.when(testRoom2.getPrev()).thenReturn(testRoom1);
		Mockito.when(testRoom1.hasNextRoom()).thenReturn(true);
		Mockito.when(testRoom2.hasPrevRoom()).thenReturn(true);
		Mockito.when(testRoom1.getItem()).thenReturn("test");
		
		String input = "";
		CoffeeMakerQuest.performAction(input, testRoom1);
		Mockito.verify(testRoom1, Mockito.times(0)).getNext();
		Mockito.verify(testRoom2, Mockito.times(0)).getPrev();
		Mockito.verify(testRoom1, Mockito.times(0)).getItem();
		Mockito.verify(testRoom1, Mockito.times(0)).removeItem();
		
		
		input = "n";
		assertEquals(CoffeeMakerQuest.performAction(input, testRoom1), testRoom2);
		Mockito.verify(testRoom1, Mockito.times(1)).hasNextRoom();
		Mockito.verify(testRoom1, Mockito.times(1)).getNext();
	
		input = "N";
		assertEquals(CoffeeMakerQuest.performAction(input, testRoom1),testRoom2);
		Mockito.verify(testRoom1, Mockito.times(2)).hasNextRoom();
		Mockito.verify(testRoom1, Mockito.times(2)).getNext();
		
		input = "s";
		CoffeeMakerQuest.performAction(input, testRoom2);
		Mockito.verify(testRoom2, Mockito.times(1)).hasPrevRoom();
		Mockito.verify(testRoom2, Mockito.times(1)).getPrev();
		
		input = "l";
		CoffeeMakerQuest.performAction(input, testRoom1);
		Mockito.verify(testRoom1, Mockito.times(4)).getItem();
		Mockito.verify(testRoom1, Mockito.times(1)).removeItem();
	}

}
