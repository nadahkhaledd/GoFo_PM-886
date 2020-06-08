package Project;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Scanner;


/**
 * @author nadah
 */
public class owner extends User {
	public Vector<playground> pg = new Vector();
	public Vector<String> request = new Vector();
	private int OwnerID ;
	
	public owner() { }
	
   	public owner(String fname , String lname, String username, String id, String phone, String password, String email) {
		super(fname,lname, username, id , phone, password, email);
		// TODO Auto-generated constructor stub
	}
   	public void Refresh_request() {   // it is called every time before showing the requests list to the admin
   		request.clear();
   		String x;
   		for( int i =0 ; i < pg.size(); i++) {
   			if( !(pg.get(i).isApproval()) ){
   				x = "A new playground is waiting for approval with Playground ID = " + pg.get(i).getID() + " , and Owner ID = " + this.getOwnerID() ;
   				request.add(x);
   			}
   		}
   	}
   	public Vector<String> getRequest()
   	{
   		return request;
   	}
   	public Vector<playground> getvector()
   	{
   		return pg;
   	}
   	public int getOwnerID() {
   		return OwnerID ;
   	}
   	public void setOwnerID( int x ) {
   		OwnerID = x ;
   	}
 	
    Scanner input = new Scanner (System.in);
    playground plg = new playground();
    String n , l , d;
    double p;
    int id = 0;
    
   /**
    * method to add a new playground to the system
    */
    public void addPlayground()
	{
    	input.nextLine();
			System.out.println("Fill the following informatino");
			System.out.println("Enter playground name :");
			n = input.nextLine();
			System.out.println("Enter playground location: ");
			l = input.nextLine();
			System.out.println("Enter playground description: ");
			d = input.nextLine();
			System.out.println("Enter booking price for that playground: ");
			p = input.nextDouble();
			id++;
			
			playground plg = new playground(n,l,id,d,p,this.getF_Name() ,true);
			pg.add(plg);
			String re = "A new playground is waiting for approval with Playground ID = " + plg.getID() + " , and Owner ID = " + this.getOwnerID() ;
			request.add(re);
	}
    
    /**
     * method to delete a playground that exists in the system
     * @param id
     */
    public void deletePlayground(int id)
   	{
   		for(int i=0; i<pg.size(); i++)
   		{
   			if(pg.get(i).getID()==id)
   			{
   				pg.remove(i);
   			}
   		}
   	}
   	
    /**
     * method to update the info of a specific playground
     * @param Id
     */
   	public void updatePlayground(int Id)
   	{
   		for(int i=0; i<pg.size(); i++)
   		{
   			if(pg.get(i).getID()==Id)
   			{
   				int ans ;
   				System.out.println("What would you like to change about this playground? ");
   				System.out.println("1- Name");
   				System.out.println("2- Location");
   				System.out.println("3- Description");
   				System.out.println("4- Price per hour");
   				ans = input.nextInt();
   				input.nextLine();
   				switch(ans)
   				{
   				case (1):

   					System.out.println("Enter the new name : ");
   					n = input.nextLine();
   					plg.setName(n);
   					break;
   				case (2):
 
   					System.out.println("Enter the new Location : " );
   					l = input.nextLine();
   					plg.setLocation(l);   					
   					break;
   	
   				case (3):
   				
   					System.out.println("Enter the new Description : ");
   					d = input.nextLine();
   					plg.setDescription(d);  					
   					break;
   				
   				case (4):
   				
   					System.out.println("Enter the new price : ");
   					p = input.nextDouble();
   					plg.setPrice(p);
   					break;  				
   			}
   		}
   	}
}
   	
   	/**
   	 * method to show the data of the owner and his owned playgrounds 
   	 */
   	public void profile()
   	{
   		System.out.println("Owner ID : " + this.getOwnerID());
   		System.out.println("Name: " + getF_Name());
   		System.out.println("National ID: " + getID());
   		System.out.println("Phone Number: " + getPhone());
   		System.out.println("Email: " + getEmail());
   		System.out.println("Playgrounds Info:-");
   		System.out.println("You have " + pg.size() + " playgrounds.");
   		for(int i=0 ; i<pg.size(); i++)
   		{
   			if(pg.get(i).isApproval()==true && pg.get(i).isAvailable()==true)
   			{
   			System.out.println("\nPlayground No." + (i+1) +" : ");
   			pg.get(i).data();
   			}
   		}
   	}
   	
   	/**
   	 * method to view if any of the owner's playgrounds has been booked by any player 
   	 */
   	public void viewBookings()
   	{
   		for(int i =0 ; i<pg.size() ;i++) {
   			if(pg.get(i).isApproval() == true && pg.get(i).isAvailable() == false && pg.get(i).getBookingNum() != 0) {
   				for(int k =0 ; k < Main.players.size() ; k ++) {		//iterating each player
   					for(int j =0 ; j<Main.players.get(k).bookings.size() ; j++) {	//iterating each player current bookings
   						if(Main.players.get(k).bookings.get(j).getBooking_num() == pg.get(i).getBookingNum()) {
   							System.out.println("----------------------------");
   							Main.players.get(k).bookings.get(j).view();
   							System.out.println("Booked By : " );
   							Main.players.get(k).getInfo() ;
   							System.out.println();
   							j = Main.players.get(k).bookings.size() ;
   							break;
   						}
   						
   					}
   				}
   			}
   			else 
			{
				System.out.println("no one has booked any playground yet");
				break;
			}
   			
   		}
   	}
   	
   	
   	
   	
}
   	

