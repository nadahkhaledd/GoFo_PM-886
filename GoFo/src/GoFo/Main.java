package Project;

import java.util.Scanner;
import java.util.Vector;

/**
 * 
 * @author nadah , marwan , omar , mohamed 
 *
 */
public class Main {
	static int booking_num  = 1;		// It is incremented by one after each booking process (unique) it is the link between the playground and its booking process
										// If its value in the playground was (0) means it is not booked thus if not available and booking number is (0) , it is suspended
	static int ownerID = 1;
	static Player player = new Player();
	static owner Owner = new owner();
	static Administrator admin = new Administrator ();
	static String LoggedIn_UserType ;  // Type of logged in user that indicates his vector
	static int LoggedIn_UserLocation;   // Index of the logged in user in his vector 
	static Scanner scan = new Scanner(System.in);
	public static Vector<Player> players = new Vector<Player> () ;
	public static Vector <owner> owners = new Vector<owner>();
	public static Vector <playground> availablePlaygrounds = new Vector <playground>();  // contains all the available playgrounds at the moment
	
	/**
	 * all admin methods 
	 */
	public static void Admin_menu() {
		int answer ;
		System.out.println();
		System.out.println("                System Administrator        ");
		System.out.println();
		do {
			for(int i =0 ; i< owners.size() ; i++) {
				owners.get(i).Refresh_request();
			}
			admin.requests();
			System.out.println("1- Approve Playground");
			System.out.println("2- Suspend Playground");
			System.out.println("3- Delete Playground");
			System.out.println("4- Activate Playground");
			System.out.println("5- Show available Playgrounds");
			System.out.println("6- Logout");
			System.out.println();
			answer = scan.nextInt();
			while(answer <1 || answer > 6) {
				System.out.println("Enter only a valid choice !");
				answer = scan.nextInt();
			}
			switch(answer) {
				case(1):
					admin.approve_playground();
				break;
				case(2):
					admin.suspend_playground();
				break;
				case(3):
					admin.delete_playground();
				break;
				case(4):
					admin.activate_playground();
				break;
				case(5):
					admin.showPlaygorunds();
				break;
			}
		}while(answer != 6);
	}
	
	/**
	 * shows and access to all owner's methods 
	 * @param location
	 */
	public static void owner_menu(int location) {
		int answer ;
		System.out.println();
		System.out.println();
		System.out.println(" -------------------- Hello " + owners.get(location).getF_Name() + " -------------------- ");
		do {
			System.out.println();
			System.out.println("Which operation do you want to perform ?");
			System.out.println("1- Add Playground");
			System.out.println("2- Update a playground");
			System.out.println("3- Delete a playground");
			System.out.println("4- View bookings");
			System.out.println("5- Logout");
			answer = scan.nextInt();
			while(answer < 1 || answer > 5) {
				System.out.println("Enter a valid choice 1");
				answer = scan.nextInt();
			}
				switch(answer) {
					case(1):
						scan.hasNextLine();
						owners.get(location).addPlayground();
					break;
					case(2):
						Vector update = new Vector();
						for(int i =0 ; i<owners.get(location).pg.size(); i++) {
							if( owners.get(location).pg.get(i).isAvailable()) { // to get only the available
								System.out.println("------------------------------------");
								System.out.println();
								owners.get(location).pg.get(i).dataForOwner();
								update .add(owners.get(location).pg.get(i).getID());
								System.out.println();
							}
						}
						if(update.size() != 0 ) {
							System.out.println(" You can Update only the playgrounds that is not active");
							System.out.println();
							System.out.println(" Enter the ID of the playgorund that you want to update ");
							int id = scan.nextInt();
							while( ! (update.contains(id)) ) {
								System.out.println("The ID you entered is not found !!");
								System.out.println("If it exists then the playground might be active or Suspended");
								System.out.println("Enter the right ID");
								id = scan.nextInt();
							}
							owners.get(location).updatePlayground(id);
						}else {
							System.out.println("You have not registered any playground yet or don't have available playgrounds to update");
						}
					break;
					case (3):
						Vector delete = new Vector();
						for(int i =0 ; i<owners.get(location).pg.size(); i++) {
							if( owners.get(location).pg.get(i).isAvailable()) { // to get only the available
								System.out.println("------------------------------------");
								System.out.println();
								owners.get(location).pg.get(i).dataForOwner();
								delete .add(owners.get(location).pg.get(i).getID());
								System.out.println();
							}
						}
						if(delete.size() != 0 ) {
							System.out.println(" You can delete only the playgrounds that is not active");
							System.out.println(" Enter the ID of the playgorund that you want to delete ");
							int id = scan.nextInt();
							while( ! (delete.contains(id)) ) {
								System.out.println("The ID you entered is not found !!");
								System.out.println("If it exists then the playground might be active or Suspended");
								System.out.println("Enter the right ID");
								id = scan.nextInt();
							}
							owners.get(location).deletePlayground(id);
						}else {
							System.out.println("You have not registered any playground yet or don't have available playgrounds to delete");
						}
						
						
					break;
					case(4):
						owners.get(location).viewBookings();
					break;
				}
		}while(answer != 5);
		
	}
	
	/**
	 * shows and access to all player's methods 
	 * @param location
	 */
	public static void player_menu(int location) {
		int  answer ;
		System.out.println();
		System.out.println();
		System.out.println(" -------------------- Hello "+ players.get(location).getF_Name() +" -------------------- " );
		do {
			System.out.println();
			System.out.println("Which operation do you want to perform?");
			System.out.println("1- Book a playground");
			System.out.println("2- Show available Playgrounds");
			System.out.println("3- Filter playgrounds");
			System.out.println("4- Create a team");
			System.out.println("5- View team");
			System.out.println("6- Cancel booking");
			System.out.println("7- Logout");
			answer = scan.nextInt();
			while(answer < 1 || answer >7) {
				System.out.println("Enter a valid choice !");
				answer = scan.nextInt();
			}
				switch(answer) {
					case(1):
						availablePlaygrounds.clear();
						for(int i =0 ; i < owners.size(); i++) {
							int size = owners.get(i).pg.size();
							for (int j = 0 ; j<size ; j++) {
								if(owners.get(i).pg.get(j).isAvailable() && owners.get(i).pg.get(j).isApproval()) {
									availablePlaygrounds.add(owners.get(i).pg.get(j));
								}
							}
						}
						if(availablePlaygrounds.size()!= 0) {
							System.out.println(" These are the available playgrounds at the moment");
							for(int i =0 ; i<availablePlaygrounds.size(); i++) {
								System.out.println(" --------------------- Playground number : " + (i+1) + " ---------------------");
								availablePlaygrounds.get(i).data();
								System.out.println();
							}
						}else {
							System.out.println();
							System.out.println("There is no available playground right now ");
						}
						if(availablePlaygrounds.size() != 0) {
							int choice ;
							System.out.println("Enter the number of the playground you want to book :");
							choice = scan.nextInt();
							while(choice < 1 || choice > availablePlaygrounds.size()) {
								System.out.print("choose only from the valid choices !!");
								choice = scan.nextInt();
							}
							choice--;
							System.out.print("How many hours ? :");
							int hoursNum = scan.nextInt();
							players.get(location).Book(availablePlaygrounds.get(choice), booking_num , hoursNum);
							booking_num++;
						}
						
					break;
					case(2):
						availablePlaygrounds.clear();
						for(int i =0 ; i < owners.size(); i++) {
							int size = owners.get(i).pg.size();
							for (int j = 0 ; j<size ; j++) {
								if(owners.get(i).pg.get(j).isAvailable()  && owners.get(i).pg.get(j).isApproval()) {
									availablePlaygrounds.add(owners.get(i).pg.get(j));
								}
							}
						}
						if(availablePlaygrounds.size()!= 0) {
							System.out.println(" These are the available playgrounds at the moment");
							for(int i =0 ; i<availablePlaygrounds.size(); i++) {
								System.out.println(" --------------------- Playground number : " + (i+1) + " ---------------------");
								availablePlaygrounds.get(i).data();
								System.out.println();
							}
						}else {
							System.out.println();
							System.out.println("There is no available playground right now ");
						}
					break;
					case(3):
						availablePlaygrounds.clear();
						for(int i =0 ; i < owners.size(); i++) {
							int size = owners.get(i).pg.size();
							for (int j = 0 ; j<size ; j++) {
								if(owners.get(i).pg.get(j).isAvailable()  && owners.get(i).pg.get(j).isApproval()) {
									availablePlaygrounds.add(owners.get(i).pg.get(j));
								}
							}
						}
						if(availablePlaygrounds.size()!=0) {
							Vector<String> filter = new Vector <String>();
							System.out.println("These are the locations that has available playgrounds");
							for(int i =0 ; i< availablePlaygrounds.size() ; i++) {
								if( ! filter.contains(availablePlaygrounds.get(i).getLocation())) {
									filter.add(availablePlaygrounds.get(i).getLocation()) ;
									System.out.println((i+1) + "- " + availablePlaygrounds.get(i).getLocation());
								}
							}
							System.out.println("Choose a location by entering its number");
							int locationChoice = scan.nextInt();
							while(locationChoice < 1 || locationChoice > filter.size()) {
								System.out.println("Enter a valid choice");
								locationChoice = scan.nextInt();
							}
							locationChoice --;
							for(int i =0 ; i< availablePlaygrounds.size(); i++) {
								if(availablePlaygrounds.get(i).getLocation().equals(filter.get(locationChoice))) {
									System.out.println("-----------------------------------------");
									availablePlaygrounds.get(i).data();
									System.out.println();
								}
							}
						}
					break;
					case(4):
						if(players.size() < 7) {
							System.out.println("There is no enough players to add in one team");
						}else {
							
							Vector <Player> availablePlayers = new Vector <Player> ();
							Vector <Player> chosenTeam = new Vector <Player>();
							int number ;
							for(int i =0 ; i<players.size() ; i++) {
								if( ! (i == location) ) {
									availablePlayers.add(players.get(i));
								}
							}
							System.out.println("The team must contains 7 players including you ");
							System.out.println("Choose 6 from these players to add to your team (By entering their number)");
							for( int i =0 ; i< availablePlayers.size(); i++) {
								System.out.println(" -------------------- Player number " + (i+1) +" --------------------");
								availablePlayers.get(i).getInfo() ;
							}
							for(int i =0 ; i<6 ; i++) {
								number = scan.nextInt();
								while(number < 1 || number > availablePlayers.size()) {
									System.out.println("choose a vaild choice");
									number = scan.nextInt();
								}
								number --;
								chosenTeam.add(availablePlayers.get(number)) ;
							}
							System.out.println("Enter a name for you team : ");
							String name = scan.next();
							players.get(location).Create_team(chosenTeam , name);
						}
					break;
					case(5):
						players.get(location).View_team();
					 break;
					case(6):
						System.out.println("These are your current Bookings : ");
						System.out.println();
						players.get(location).View_bookings();
						System.out.println("Enter the number of the current process that you want to Cancel : ");
						int Bookchoice = scan.nextInt();
						while( Bookchoice < 1 || Bookchoice > players.get(location).bookings.size()) {
							System.out.println("Enter a valid choice !!");
							Bookchoice = scan.nextInt();
						}
						Bookchoice--;
						players.get(location).Cancel_booking(Bookchoice);
					break;
						
				}
			}while( answer != 7);
		
	}
	
	/**
	 * create a new user profile 
	 */
	public static void Signup() {
		int answer ;
		boolean exist = false ;
		System.out.println("Create account as:");
		System.out.println("1- Player");
		System.out.println("2- Playground Owner");
		answer = scan.nextInt();
		while(answer != 1 && answer != 2) {
    		System.out.println("Enter a valid choice !");
    		answer=scan.nextInt();
    	}
		String first,last,user,pass,id,phone,email;
		System.out.println("Enter the required info:");
		System.out.print("First name: ");
		first = scan.next();
		System.out.print("Last name: ");
		last = scan.next();
		System.out.print("Username: ");
		user = scan.next();
		System.out.print("National ID: ");
		id = scan .next();
		System.out.print("Phone number: ");
		phone = scan.next();
		System.out.print("Email: ");
		email = scan.next();
		System.out.print("Password: ");
		pass = scan.next() ;
		for(int i = 0 ; i<players.size() ; i++) {
			if(players.get(i).getUsername().equals(user)) { // check for identical Username and password
				exist = true;
				break;
			}
		}
		for(int i = 0 ; i<owners.size() ; i++) {
			if(owners.get(i).getUsername().equals(user)) { // check for identical Username and password
				exist = true;
				break;
			}
		}
		if(exist == true) {
			System.out.println("This username is already Taken !");
		}else {
			System.out.println("Account is created successfuly");
			if(answer == 1) {
				player = new Player(first , last , user , id , phone , pass , email );
	    		players.add(player);
			}else {
				Owner = new owner(first , last , user , id , phone , pass , email );
				Owner.setOwnerID(ownerID);
				ownerID++;
	    		owners.add(Owner);
			}
		}
	}

	/**
	 * login to user's profile 
	 */
	public static void login() {
		LoggedIn_UserType = "" ;
		boolean logged = false;
		String user,pass;
		System.out.println("---------Login---------");
		System.out.print("Username: ");
		user = scan.next();
		System.out.print("Password: ");
		pass = scan.next();
		for(int i = 0 ; i<players.size() ; i++) {   // Search in players vector
			if(players.get(i).login_check(user, pass)) { 
				LoggedIn_UserLocation = i;
				LoggedIn_UserType = "player";
				logged = true ;
				break;
			}
		}
		if(logged == false){			// If not found search in owner vector
			for(int i =0 ; i<owners.size() ; i++) {
				if(owners.get(i).login_check(user, pass)) {
					LoggedIn_UserLocation = i;
					LoggedIn_UserType = "owner" ;
					logged = true ;
					break;
				}
			}
			if(logged == false) {		// Not found 
				System.out.println("Wrong username or password !");
				System.out.println();
			}
		}
		if(LoggedIn_UserType.equals("player")) {
			player_menu(LoggedIn_UserLocation);
		}
		if(LoggedIn_UserType.equals("owner")) {
			owner_menu(LoggedIn_UserLocation) ;
		}
		
	}

	public static void main(String[] args) {
		int answer ;
		System.out.println();
		System.out.println("                    welcome to the GoFo application                    " );
		System.out.println();
		do {
			System.out.println("---------- Home ----------");
			System.out.println("1- Login");
			System.out.println("2- Signup");
			System.out.println("3- Login as system administrator");
			System.out.println("4- Exit");
			answer = scan.nextInt();
			while(answer <1 || answer >4) {
				System.out.println("Enter a valid choice");
				answer = scan.nextInt();
			}
			if(answer == 1) {
				login();
			}
			if(answer == 2) {
				Signup ();
			}
			if(answer == 3) {
				Admin_menu();
			}
			if(answer == 4)
			{
				System.out.println("Finished");
				System.exit(0);
			}
		}
		while(answer != 4);
	}
}