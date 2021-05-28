package fixtures;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/*
 * Represents a room in the house. 
 * 
 * Extends Fixture so will inherit the descriptive properties
 */

public class Room extends Fixture {
	/*
	 * Creates a TreeMap to hold exits for this Room.
	 * 
	 * String: 	Holds the direction of Room adjacent to this Room
	 * Room: 	Holds a Room object adjacent to this Room
	 */
	private Map<String, Room> exits = new TreeMap<>();
	/*
	 * Holds all Items the player may interact with in a Room
	 */
	private List<Item> items = new ArrayList<>();
	
	/*
	 ******************
	 * Constructor
	 ******************
	 */
	public Room(String name, String shortDesc, String longDesc) {
		super(name, shortDesc, longDesc);
	}
	
	/*
	 ***********************
	 * Setters and Getters
	 ***********************
	 */
	
	/*
	 * ArrayList of Items
	 */
	//Add an Item to the current ArrayList
	public void addItem(Item newItem) {
		items.add(newItem);
	}
	//Return the ArrayList consisting of all Items in the Room
	public List<Item> getItemList(){
		return items;
	}
	//Delete an Item from the current ArrayList
	public void deleteItem(Item begone) {
		items.remove(begone);
	}
	
	/*
	 * TreeMap of exits and their directions
	 */
	//Add an exit adjacent to the Room
	public void setExits(String direction, Room room) {
		exits.put(direction, room);
	}
	//Return the TreeMap with all exits and directions 
	public Map<String, Room> getExits() {
		return exits;
	}
	//Return a specified exit adjacent to the Room
	public Room getExit(String direction) {
		return exits.get(direction);
	}
	
}
