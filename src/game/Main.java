package game;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import fixtures.Item;
import fixtures.Room;

public class Main {
	
	/*
	 * A static boolean used to keep track of whether the User wants to end the program
	 */
	public static boolean running = true;
	
	public static void main(String[] args) {
		/*
		 * Initialize a RoomManager and initialize it to create the framework of the Home
		 */
		RoomManager manager = new RoomManager();
		manager.init();
		
		/*
		 * Initialize a Player to traverse the Home
		 */
		Player player = new Player();
		player.setCurrentRoom(manager.getStartingRoom());
		
		/*
		 *  Display a prompt showing the User valid commands
		 */
		System.out.println(help());
		
		while(running) {
			// Display prompt for the current room occupied by Player
			printRoom(player);
			// Collect input from the user
			String[] command = collectInput();
			// Parse/take apart commands from the user
			parse(command, player);
		}
		
		/*
		 * If the user has been stealing from the residents they will be punished
		 */
		if	(player.getBackpack().isEmpty()) {
			System.out.println("\nThank you for touring the house! Have a good one!\n"
					+ "Since you were so good, here. Have a cookie!");
		}
		else {
			System.out.println("\nThanks for visiting. The cops are on their way since you got some"
					+ "sticky lil' fingers.\n"
					+ "Have a nice day!");
		}
	}
	
	private static void printRoom(Player player) {
		/*
		 * Display information for the Current Room inhabited by the Player
		 */
		System.out.println(player.getCurrentRoom().getName() + "\n\n"
				+ player.getCurrentRoom().getLongDesc());
		
		/*
		 * Display all Items in the Current Room
		 * 
		 * If already in Backpack, they must already be removed from the List of Items
		 */
		System.out.println("Items:");
		for(int i = 0; i < player.getCurrentRoom().getItemList().size(); i++) {
			Item item = player.getCurrentRoom().getItemList().get(i);
			System.out.println(item.getShortDesc());
		}
		
		/*
		 * Display all exits to other Rooms connected to the Current Room
		 */
		System.out.println("\nExits:");
		for(Map.Entry<String, Room> e : player.getCurrentRoom().getExits().entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue().getShortDesc());
		}
	}

	private static String[] collectInput() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		String[] phrase = input.split(" ");
		return phrase;
	}

	private static void parse(String[] command, Player player) {
		switch(command[0]) {
		case "go": 
			goToRoom(command, player);
			break;
		/*case "take":
			player.inventory.addItem(player.getCurrentRoom().getRoomItem());
			player.getCurrentRoom().deleteItem();
			break;
		case "inventory":
			player.inventory.printInventory();
			break;
		*/
		case "description":
			for(int i = 0; i < player.getCurrentRoom().getItemList().size(); i++) {
				System.out.println(player.getCurrentRoom().getItemList().get(i).getLongDesc());
			}
			break;
		case "help":
			System.out.println("\n" + help());
			break;
		case "exit":
			running = false;
			break;
		}
	}
	
	private static void goToRoom(String[] command, Player player) {
		
		player.setCurrentRoom(player.getCurrentRoom().getExit(command[1]));

		//System.out.println("You run face first into a wall. I guess there is no exit there...\n");
		
		
	}
	
	
	private static String help() {
		return "Controls:\n"
				+ "go: \tto travel to a different room type \"go\" followed by the intended direction\n"
				+ "\tex: go north\n"
				/*
				+ "take: \tto pick up an item and add it to your players inventory\n"
				+ "inventory: displays the items in your inventory\n"
				*/
				+ "description: displays full description of item\n"
				+ "\tex: description spork\n"
				+ "help: \tif you have forgotten the controls type \"help\"\n"
				+ "exit: \tto exit the house type \"exit\". We don't mind. It's not like "
				+ "we're lonely here or anything...\n";
	}
}
