package Project;

import java.util.Scanner;
import java.util.Vector;

/**
 * 
 * @author Marwan
 *
 */
	public class Player extends User {
		private
		Scanner scan = new Scanner(System.in);
		Team team = new Team();
		public Vector<Booking> bookings = new Vector<Booking>();
		Booking newBooking = new Booking();
		public Player() {};
		public Player(String f ,String l , String user , String id , String mobile, String pass , String email) {
			super( f , l , user , id , mobile , pass , email) ;
		}
		
		/**
		 * create a new team of players
		 * @param v 
		 * @param x
		 */
		public void Create_team(Vector <Player> v ,String x) {
			team.Members = v;
			team.name = x;
		}
		
		/**
		 * view player's team members
		 */
		public void View_team() {
			if(team.Members.size() != 0) {
				System.out.println("-------------------- Team name : " + team.name + " --------------------");
				for(int i =0 ; i < 6 ; i++) {
					System.out.println("---------- PLayer number " + (i+1) + " ---------- ");
					team.Members.get(i).getInfo();
				}
			}else {
				System.out.println(" You dont have a team ! ");
			}
		}
		
		/**
		 * method to book an activated playground 
		 * @param Playground
		 * @param number
		 * @param numberOfHours
		 */
		public void Book(playground Playground , int number , float numberOfHours) {
			newBooking.booked_playground = Playground ;
			newBooking.booking_number = number ;
			newBooking.player = this ;
			newBooking.validity(true);
			newBooking.timeInterval = numberOfHours;
			bookings.add(newBooking);
		}
		
		/**
		 * cancel a booking for a playground 
		 * @param x
		 */
		public void Cancel_booking(int x) {
			bookings.get(x).validity(false);
			bookings.remove(x);
		}
		
		/**
		 * view all the playgrounds that a player has booked 
		 */
		public void View_bookings() {
			for(int i =0 ; i<bookings.size(); i++) {
				System.out.println("--------------------------------------- Number " + (i+1) + "--------------------------------------- ");
				bookings.get(i).view();
			}
		}

}
