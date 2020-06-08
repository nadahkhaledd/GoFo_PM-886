package Project;


/**
 * 
 * @author nadah
 *
 */
public class playground {
	private String name;
	private String location;
	private int ID;
	private String description;
	private double price;
	private String Owner;
	public boolean approval = false ;
	private boolean availability = true ;
	private int booking_number ;
	public playground()
	{
	}
	public playground(String name, String location, int id, String description, double price,String owner,
			boolean availability) {
		super();
		this.name = name;
		this.location = location;
		this.ID = id;
		this.description = description;
		this.price = price;
		this.Owner = owner;
		this.approval = approval;
		this.availability = availability;
	}
	public void setBookingNum(int x) {
		this.booking_number = x;
	}
	public int getBookingNum() {
		return booking_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getID()
	{
		return ID;
	}
	public void setID(int id)
	{
		this.ID = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOwner()
	{
		return Owner;
	}
	public void setOwner(String owner)
	{
		this.Owner = owner;
	}
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}
	public boolean isAvailable() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	/**
	 * all info for the playground 
	 */
	public void data()
	{
		System.out.println("Name: " + name);
   		System.out.println("Location: " + location);
   		System.out.println("Description: " + description);
   		System.out.println("Price: " + price);
   		System.out.println("Owner: " + Owner);
	}
	
	/**
	 * print playground info 
	 */
	public void dataForOwner()
	{
		System.out.println("Name: " + name);
   		System.out.println("Location: " + location);
   		System.out.println("Description: " + description);
   		System.out.println("Price: " + price);
   		System.out.println("Owner: " + Owner);
   		System.out.println("ID : " + this.getID());
   		if(this.isApproval()) {
   			System.out.println(" Approved");
   		}else {
   			System.out.println(" NOT Approved");
   		}
   		
	}
}
