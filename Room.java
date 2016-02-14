/**
 * Class for modeling a room in the game.
 */
public class Room {
	
	private String adj; //the unique adjective for the room
	private String furn; //the unique furnishing for the room
	private String item; //the item in the room; null if no item in room
	private Room prev; //the room to the south
	private Room next; //the room to the north
	
	public Room(String a, String f, String i, Room p, Room n){
		adj = a;
		furn = f;
		item = i;
		prev = p;
		next = n;
	}
	
	/**
	 * Returns the unique adjective for the room.
	 */
	public String getAdj(){
		return adj;
	}
	
	/**
	 * Returns the unique furnishing for the room.
	 */
	public String getFurn(){
		return furn;
	}
	
	/**
	 * Returns the hidden item in the room; returns null if no item.
	 */
	public String getItem(){
		return item;
	}
	
	/**
	 * Returns the Room to the south; returns null if there is no door to the south.
	 */
	public Room getPrev(){
		return prev;
	}
	
	/**
	 * Returns the Room to the north; returns null if there is no room to the north.
	 */
	public Room getNext(){
		return next;
	}
	
	/**
	 * Sets the next room (the door to the north), to avoid "uninitialized" warnings.
	 */
	public void setNextRoom(Room r){
		next = r;
	}
	
	/**
	 * Returns true if there exists a door to the south, false otherwise.
	 */
	public boolean hasPrevRoom(){
		return prev != null;
	}
	
	/**
	 * Returns true if there exists a door to the north, false otherwise.
	 */
	public boolean hasNextRoom(){
		return next != null;
	}
	
	/**
	 * Removes the found item from the room.
	 */
	public void removeItem(){
		item = null;
	}
}
