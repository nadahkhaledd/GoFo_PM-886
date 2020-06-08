package Project;



/**
 * 
 * @author Marwan
 *
 */
public class Booking {
	
	public playground booked_playground = new playground();
	public int booking_number;
	public Player player ; 
	private boolean valid ;
	float timeInterval;
	public void validity(boolean v) {
		valid = v;		
		if(v == true) {
			booked_playground.setAvailability(false);	
			booked_playground.setBookingNum(booking_number);
		}else {
			booked_playground.setAvailability(true);
			booked_playground.setBookingNum(0);
		}
	}
	
	/**
	 * view all the info related to a booking that has been made 
	 */
	public void view() {
		System.out.println();
		System.out.println("Playground Name : " + booked_playground.getName());
		System.out.println("Playground Location : " + booked_playground.getLocation());
		System.out.println("time interval : " + this.timeInterval + " hour(s)");
		System.out.println("Booking number : " + this.booking_number);
		
	}
	public int getBooking_num() {
		return booking_number ;
	}

}