package game;

import fixtures.Room;
import fixtures.Item;

/*
 * Responsible for "loading" our rooms into memory.
 * 
 * When game.Main is executed, it will invoke the init() method in this class that will 
 * instantiate all other Room objects, link them together as exits, and designate a startingRoom
 */

public class RoomManager {
	
	//The room the Player will start at.
	private Room startingRoom;

	/***************
	 * BUILD HOME
	 ***************
	 *
	 * Initializes all Rooms, their exits, and items found within the Home
	 */
	public void init() {
		
		/*
		 * All rooms throughout the Home
		 */
		Room foyer = new Room("The Foyer", "a small foyer",
				"The small entryway of a neo-colonial house.\n"
				+ "To the north is a small room, where you can see a piano.\n"
				+ "A dining room is open to the south, where a large table can be seen.\n"
				+ "The hardwood floor leads west into doorway, next to a staircase that leads down to a basement floor.\n");
		Room playRoom = new Room("The Playroom", "A large playroom",
				"The playroom is littered with toys all over and a huge TV with consoles connected all"
				+ "over.\n"
				+ "You see child no older than twelve years of age playing GTA5 screaming "
				+ "obscenities over the mic.\n"
				+ "You feel like a quick escape is the best option here.\n"
				+ "To the south is the foyer.\n");		
		Room diningRoom = new Room("The Dining Room", "A large dining room",
				"The room is tidy and the tiled walls shine like mirrors, clear enough to see " + "yourself in.\n"
				+ "You realize you have food stuck in your teeth from that salad you had...\n"
				+ "\"Was that there the whole time????\" you think to yourself as you rub it away "
				+ "thinking nobody else saw.\n\n"
				+ "\tThey saw it...\n");
		Room basement = new Room("The Basement", "A pitch black basement",
				"It is pitch black in here. You take a curious step in without any light and " 
				+ "promptly tumble down the stairs.\n");
		
		/*
		 * All items found throughout the Home
		 */
		Item coat = new Item("coat", "an expensive coat", "\nYou should try it on. I'm sure the owner "
				+ "won't mind.\n"
				+ "You start posing like superman and the back rips.\n"
				+ "\"Oops...\"\n"
				+ "You put it back, careful to hide the tears from anyone just passing by.\n");
		Item furby = new Item("furby", "An odd owl toy", "\nLooks like this puppy has been through the "
				+ "ringer. Don't think the kid will notice if it goes missing... <.<' >.>'\n"
				+ "*you eagerly stuff this treasure in your backpack thinking nobody noticed*\n");
		Item spork = new Item("spork", "a widdle sporky", "\nGood for any kind of food slippery or slimey.\n");
		Item darkEscence = new Item("blob", "a dark blob", "\nYou get this weird scream in your head as "
				+ "you stare into it. I'm sure that doesn't mean anything serious.\n"
				+ "Nothing out of the ordinary here. Nope. Nope. Nope.\n");
		/*
		 * Stick the Home together
		 * 
		 * 	1. Set all exits for each room.
		 * 	2. Add all items to their specific rooms.
		 */
		foyer.setExits("north", playRoom);
		foyer.setExits("south", diningRoom);
		foyer.setExits("west", basement);		
		
		foyer.addItem(coat);
		
		playRoom.setExits("south", foyer);
		
		playRoom.addItem(furby);
		
		diningRoom.setExits("north", foyer);
		
		diningRoom.addItem(spork);
		
		basement.setExits("east", foyer);
		
		basement.addItem(darkEscence);
		
		//	3. Create a starting point for the Player
		setStartingRoom(foyer);
	}

	public Room getStartingRoom() {
		return startingRoom;
	}

	public void setStartingRoom(Room startingRoom) {
		this.startingRoom = startingRoom;
	}
	
}

