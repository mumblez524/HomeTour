package game;

import java.util.Map;
import java.util.Scanner;

import fixtures.Item;
import fixtures.Room;

public class Main {
	
	// To keep track of whether the User wants to end the program
	public static boolean running = true;
	// To take input from the user
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		// Initialize a RoomManager and initialize it to create the framework of the Home
		RoomManager manager = new RoomManager();
		manager.init();
		
		// Initialize a Player to traverse the Home
		Player player = new Player();
		player.setCurrentRoom(manager.getStartingRoom());
		
		System.out.println(help());
		
		do {
			printRoom(player);
			String[] command = collectInput();
			parse(command, player);
		} while (running);
		
		
		/*
		 ****************************************************************************
		 * If the user has been stealing from the residents they will be punished
		 ****************************************************************************
		 */
		if	(player.getBackpack().isEmpty()) {
			System.out.println("\nThank you for touring the house! Have a good one!\n"
					+ "Here have a cookie!");
		}
		else {
			System.out.println("\nThanks for visiting my home! I noticed you got some sticky lil' fingers "
					+ "so the cops are on the way.\n"
					+ "You hear the sound of sirens growing louder.\n"
					+ "Bye! Have a nice day!");
		}
	}
	
	
	/*
	 **********************
	 * Display Functions
	 **********************
	 */
	//Prints Room information
	private static void printRoom(Player player) {
		System.out.println("________________________________________________________________________________________________________________________________\n" 
				+ player.getCurrentRoom().getName() + "\n\n"
				+ player.getCurrentRoom().getLongDesc());

		if(player.getCurrentRoom().getItemList().size() != 0) {
			printItems(player);
		}
		printExits(player);
		
		System.out.println("________________________________________________________________________________________________________________________________");

	}
	
	private static void printItems(Player player) {
		System.out.println("\nRoom Items:");
		for(int i = 0; i < player.getCurrentRoom().getItemList().size(); i++) {
			Item item = player.getCurrentRoom().getItemList().get(i);
			System.out.println(item.getShortDesc());
		}
	}
	private static void printExits(Player player) {
		System.out.println("\nRoom Exits:");
		for(Map.Entry<String, Room> e : player.getCurrentRoom().getExits().entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue().getShortDesc());
		}
	}	
	
	// Displays the current Player inventory
	private static void displayInventory(Player player){
		System.out.println("\n________________________________________________________________________________________________________________________________\nInventory:");
		for(int i = 0; i < player.getBackpack().size(); i++) {
			System.out.println(player.getBackpack().get(i).getName());
		}
		System.out.println("________________________________________________________________________________________________________________________________");
	}
	
	// Displays the longDesc of an Item
	private static void getInteraction(Player player){
		System.out.println("\n" 
				+ player.getCurrentRoom().getItemList().get(0).getName() + ":\n" 
				+ player.getCurrentRoom().getItemList().get(0).getLongDesc());
	}
	
	// Prints the Commands a user may use as well as shortly describing what they do
	private static String help() {
		return "________________________________________________________________________________________________________________________________\n"
				+ "Controls:\n"
				+ "go: \tto travel to a different room type \"go\" followed by the intended direction\n"
				+ "\tdirections: \"north\", \"east\", \"south\", \"west\"\n"
				+ "\tex: go north\n"
				+ "take: \tpick up the item in the room and add it to your players inventory\n"
				+ "inventory: displays the items in your inventory\n"
				+ "interact:  interact with the item in the room\n"
				+ "\tex: description spork\n"
				+ "help: \tif you have forgotten the controls type \"help\"\n"
				+ "exit: \tto exit the house type \"exit\". We don't mind. It's not like "
				+ "we're lonely here or anything...\n"
				+ "________________________________________________________________________________________________________________________________";
	}

	
	
	/*
	 ***************************
	 *  Scanner Input Function
	 ***************************
	 */
	private static String[] collectInput() {
		System.out.print("\n> ");
		String input = scan.nextLine();
		String[] phrase = input.split(" ");
		return phrase;
	}
	
	// Go through prompts again
	private static void again(Player player) {
		if(player.getCurrentRoom().getItemList().size() > 0) {
			printItems(player);
		}
		printExits(player);
		String[] newCommand = collectInput();
		parse(newCommand, player);
	}

	
	
	/*
	 **************************
	 * Parse the Commands 
	 **************************
	 */
	private static void parse(String[] command, Player player) {
		
		if(validCommand(command)) {
			switch(command[0]) {
			case "go": 
				goToRoom(command, player);
				break;
			case "take":
				take(player, player.getCurrentRoom().getItemList().get(0));
				again(player);
				break;
			case "inventory":
				displayInventory(player);
				again(player);
				break;
			case "interact":
				getInteraction(player);
				again(player);
				break;
			case "help":
				System.out.println("\n" + help());
				again(player);
				break;
			case "exit":
				running = false;
				break;
			}
			
		}
		else {
			System.out.println("\nINVALID COMMAND! Input \"help\" for valid commands\n");
		}
	}
	
	// Used to check if the users command is a valid input
	private static boolean validCommand(String[] command) {

		switch(command[0]) {
		case "go": 
			return true;
		case "take":
			return true;
		case "inventory":
			return true;
		case "interact":
			return true;
		case "help":
			return true;
		case "exit":
			return true;
		}
		
		return false;
	}
	
	
	
	/*
	 *****************************
	 * User Command Functions
	 ***************************** 
	 */
	
	// To change Current Room of the Player
	private static void goToRoom(String[] command, Player player) {
		player.setCurrentRoom(player.getCurrentRoom().getExit(command[1]));
	}
	
	// To take an item out of a Room and into the Players inventory
	private static void take(Player player, Item item) {
		player.stuffBackpack(item);
		player.getCurrentRoom().deleteItem(item);
	}
}
