import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;


public class RoomTest {

	// Test that getAdj returns the same String as the instance variable adj
	@Test
	public void testGetAdj() {
		String testAdj = "test";
		Room testRoom = new Room(testAdj, null, null, null, null);
		assertEquals(testAdj, testRoom.getAdj());
	}

	// Test that getFurn returns the same String as the instance variable furn
	@Test
	public void testGetFurn() {
		String testFurn = "test";
		Room testRoom = new Room(null, testFurn, null, null, null);
		assertEquals(testFurn, testRoom.getFurn());
	}

	// Test that getItem returns the same String as the instance variable item
	@Test
	public void testGetItem() {
		String testItem = "test";
		Room testRoom = new Room(null, null, testItem, null, null);
		assertEquals(testItem, testRoom.getItem());
	}

	// Test that getPrev returns the same Room as the instance variable prev
	@Test
	public void testGetPrev() {
		Room testPrev = new Room(null, null, null, null, null);
		Room testRoom = new Room(null, null, null, testPrev, null);
		assertEquals(testPrev, testRoom.getPrev());
	}

	// Test that getNext returns the same Room as the instance variable next
	@Test
	public void testGetNext() {
		Room testNext = new Room(null, null, null, null, null);
		Room testRoom = new Room(null, null, null, null, testNext);
		assertEquals(testNext, testRoom.getNext());
	}

	// Test that setNextRoom sets the instance variable next to the given Room
	@Test
	public void testSetNextRoom() {
		Room testNext = new Room(null, null, null, null, null);
		Room testRoom = new Room(null, null, null, null, null);
		testRoom.setNextRoom(testNext);
		assertEquals(testNext, testRoom.getNext());
	}

	// Test that hasPrevRoom returns true if its prev instance variable is not null.
	// Test that it returns false otherwise.
	@Test
	public void testHasPrevRoom() {
		Room mockRoom = Mockito.mock(Room.class);
		Room testRoom = new Room(null, null, null, mockRoom, null);
		assertTrue(testRoom.hasPrevRoom());
		testRoom = new Room(null, null, null, null, null);
		assertFalse(testRoom.hasPrevRoom());
	}

	// Test that hasNextRoom returns true if its next instance variable is not null.
	// Test that it returns false otherwise.
	@Test
	public void testHasNextRoom() {
		Room mockRoom = Mockito.mock(Room.class);
		Room testRoom = new Room(null, null, null, null, mockRoom);
		assertTrue(testRoom.hasNextRoom());
		testRoom = new Room(null, null, null, null, null);
		assertFalse(testRoom.hasNextRoom());
	}

	// Test that the instance variable item is null after calling removeItem.
	// Also test that removeItem works on a Room with an item instance variable that is already null.
	@Test
	public void testRemoveItem() {
		String testItem = "test";
		Room testRoom = new Room(null, null, testItem, null, null);
		testRoom.removeItem();
		assertNull(testRoom.getItem());

		testRoom.removeItem(); //repeat - this should work fine and have the same result
		assertNull(testRoom.getItem());
	}

}
