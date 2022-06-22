public class Month 
{
	public enum monthenmu
	{
	    // declare constants of enum type
		JAN("Jan", "January"), FEB("Feb", "Feburary"), 
		MAR("Mar", "March"), APR("Apr", "April"), 
		MAY("May", "May"), JUNE("June", "June"),
		JULY("July", "July"), AUG("Aug", "August"),
		SEPT("Sept", "September"), OCT("Oct", "Octobor"),
		NOV("Nov", "November"), DEC("Dec", "December");
		
	    // Instance fields
		private final String abbreviation;
		private final String fullName;
		
		 //Constructor
		private monthenmu(String abbr, String fullName) 
		{
			this.abbreviation = abbr;
			this.fullName = fullName;
		}
		
		//Getters 
		public String getAbbr()
		{
			return abbreviation;
		}
		public String getFullName()
		{
			return fullName;
		}
		
	}
}