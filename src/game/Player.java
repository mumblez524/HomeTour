package game;

import java.util.ArrayList;
import java.util.List;

import fixtures.Item;
import fixtures.Room;

/*
 * Represents the player moving through these rooms
 */

public class Player {
	//Keeps track of the current Room inhabited by the Player
	private Room currentRoom;
	//Holds the Players Items collected throughout their journey
	private List<Item> backpack = new ArrayList<>();

	/*
	 * Current Room Functions
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	
	/*
	 * Inventory Management Functions
	 */
	public List<Item> getBackpack(){
		return backpack;
	}
	public void stuffBackpack(Item goodies) {
		backpack.add(goodies);
	}
	public void removeItemFromBackpack(Item begone) {
		backpack.remove(begone);
	}
	
}