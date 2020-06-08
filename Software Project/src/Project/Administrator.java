package Project;
import java.util.Scanner;
import java.util.Vector;

/**
 * 
 * @author Mohamed 
 *
 */
public class Administrator 
{
	public Vector<String> message=new Vector() ;
	public Vector<playground>suspended= new Vector();
	Scanner input=new Scanner(System.in);
	
	/**
	 * activate a suspended playground 
	 */
	public void activate_playground()
    {
		if(suspended.size() != 0 ) {
			System.out.println();
			System.out.println("             Suspended playgrounds list");
			System.out.println();
	       for(int i =0 ; i<suspended.size() ; i++) {
	    	  System.out.println("---------------------- Playground number " + (i+1) + "-----------------------" );
	    	   suspended.get(i).dataForOwner();
	       }
	       System.out.print(" Enter the number of playground you want to activate : ");
	       int active = input.nextInt();
	       while(active < 1 || active > suspended.size()) {
	    	   System.out.println("Enter a vaild choice !");
	    	   System.out.print("Enter a correct playground number : ");
	    	   active = input.nextInt();
	       }
	       active --;
	       suspended.get(active).setAvailability(true);
	       suspended.remove(active);
		}else {
			System.out.println(" There is no Suspended playgrounds !");
		}
    }
	
	/**
	 * delete a playground permanently from the system 
	 */
	public void delete_playground() 
	{
		if( Main.owners.size() != 0) {				
			System.out.println(" You can only delete Unbooked playgrounds");
			System.out.println("Enter owner Id");
			int idsearch=input.nextInt();
			while(idsearch < 1 || idsearch > Main.owners.size()) {
				System.out.println("This ID is not found !");
				System.out.print(" Please enter a valid owner ID : ");
				idsearch = input.nextInt();
			}
			for(int i=0;i < Main.owners.size();i++)
			{
				if(Main.owners.get(i).getOwnerID() == idsearch) 
				{
					if(Main.owners.get(i).pg.size() != 0 ) {
						System.out.println("Enter playground ID: ");
						int pgID=input.nextInt();
						while( pgID < 1 || pgID > Main.owners.get(i).pg.size()) {
							System.out.println(" This ID is not found !");
							System.out.print("Please enter a valid playground ID : ");
							pgID = input.nextInt();
						}
						for(int j=0; j < Main.owners.get(i).getvector().size(); j++)
						{
							if(Main.owners.get(i).getvector().get(j).getID() == pgID && Main.owners.get(i).getvector().get(j).getBookingNum() == 0  )
							{
								Main.owners.get(i).deletePlayground(pgID);
								System.out.println(" The playground is deleted");
							}else {
								System.out.println("The chosen playground is currently active !");
								System.out.println("You can delete only Unactive playgrounds");
								
							}
						}
					}else {
						System.out.println(" This owner doesn't have any playgrounds yet ");
					}
				}
			}
		}else {
			System.out.println(" There is no Playgorund owners yet ");
		}
	
	}
	
	/**
	 * deactivate a playground for an amount of time 
	 */
	public void suspend_playground()
	{
		if(Main.owners.size() != 0 ) {
			System.out.println("enter owner Id");
			int idsearch=input.nextInt();
			while(idsearch < 1 || idsearch > Main.owners.size()) {
				System.out.println("This ID is not found !");
				System.out.print(" Please enter a valid owner ID : ");
				idsearch = input.nextInt();
			}
			for(int i=0;i < Main.owners.size();i++)
			{
				if(Main.owners.get(i).getOwnerID() == idsearch) 
				{
					if(Main.owners.get(i).pg.size() != 0 ) {
						System.out.println("Enter playground ID: ");
						int pgID=input.nextInt();
						while( pgID < 1 || pgID > Main.owners.get(i).pg.size()) {
							System.out.println(" This ID is not found !");
							System.out.print("Please enter a valid playground ID : ");
							pgID = input.nextInt();
						}
						for(int j=0;j < Main.owners.get(i).getvector().size();j++)
						{
							if(Main.owners.get(i).getvector().get(j).getID() == pgID && Main.owners.get(i).getvector().get(j).getBookingNum() == 0)
						   {
							   Main.owners.get(i).getvector().get(j).setAvailability(false);
							   suspended.add(Main.owners.get(i).getvector().get(j));
							   System.out.println(" The playground is suspended");
						   }
						}
					}else {
						System.out.println(" This owner doesn't have any playground yet");
					}
				}
			}
		}else {
			System.out.println(" There is no Playground owners yet");
		}

	}
	
	/**
	 * shows all the playgrounds that are pending for approval 
	 */
	public void requests()
	{
		message.clear();
		for(int i=0; i<Main.owners.size(); i++)
		{
			for(int j=0; j<Main.owners.get(i).request.size(); j++)
			{
				if( Main.owners.get(i).getRequest().get(j) != null)
				{
					message.add( Main.owners.get(i).request.get(j));
				}
			}
		}
		
		if(message.size() ==0) {
			System.out.println("You have no requests ");
			System.out.println();
		}else {
			System.out.println("You have " + message.size() + " request(s)\n");
		}
		for(int i=0; i<message.size(); i++)
		{
			System.out.println(message.get(i) + "\n");
		}
	}
	
	/**
	 * approve a new added playground to be displayed in the system 
	 */
	public void approve_playground()
	{
		boolean right  = false ;    //For handling wrong input mistakes
		System.out.println("Enter the ID of the owner of the playground");
		int ownerID = input.nextInt();
		System.out.println("Enter ID of the playground to approve : ");
		int ans = input.nextInt();
		for(int i=0; i < Main.owners.size(); i++)
		{
			if(Main.owners.get(i).getOwnerID() == ownerID) {  // Filter the owners
				for(int j=0; j < Main.owners.get(i).getvector().size();j++)
				{
					if( Main.owners.get(i).getvector().get(j).getID() == ans)  // filter the playground
				   {
					   Main.owners.get(i).getvector().get(j).setApproval(true);
					   right = true ; 
				   }
				   
				}
			}
		}
		if( right == true) {
			System.out.println("Approved");
		}else {
			System.out.println("You entered wrong information !!");
		}
		
	}
	
	public void showPlaygorunds() {
		Vector <Integer> avPlaygroundOwner = new Vector <Integer> () ;
		for(int i = 0 ; i<Main.owners.size() ; i++) {
			for( int j = 0 ; j < Main.owners.get(i).pg.size() ; j++) {
				if(Main.owners.get(i).pg.get(j).isAvailable()) {
					avPlaygroundOwner.add(i);
					break;
				}
			}
		}
		if(avPlaygroundOwner.size() == 0) {
			System.out.println("There is no available playground");
		}else {
			for(int i =0 ; i< avPlaygroundOwner.size() ; i++) {
				System.out.println();
				System.out.println(" -----  Owner ID  " +  Main.owners.get(  avPlaygroundOwner.get(i)).getOwnerID() +" -----" );
				for(int j =0  ; j < Main.owners.get(  avPlaygroundOwner.get(i)).pg.size() ; j++ ) {
					if( Main.owners.get(  avPlaygroundOwner.get(i)).pg.get(j) .isAvailable() ) {
						Main.owners.get(  avPlaygroundOwner.get(i)).pg.get(j).dataForOwner();
					}
				}
				System.out.println();
			}
		}
	}
	
	
}