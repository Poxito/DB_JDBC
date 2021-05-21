package domain;

import java.util.ArrayList;

public class Queries {

	private ArrayList<String> AritzQueryText = new ArrayList<String>();
	private ArrayList<String> AritzQueries = new ArrayList<String>();
	private ArrayList<String> ZdravkoQueryText = new ArrayList<String>();
	private ArrayList<String> ZdravkoQueries = new ArrayList<String>();

	public Queries() {

		AritzQueryText.add(
				"Retrieve the people that eat the dish 'cheescake' or a dish which its difficulty is higher or equal than 1, grouped by the category of the dish.");
		AritzQueryText.add(
				"Retrieve the maximum, minimum and average of hours of those employees that work in any project of department 1.");
		AritzQueryText.add("People who frequents the same restaurant(s) as Kevin.");

		AritzQueries.add("select p.nameId, d.category "
				+ "from eats as e inner join person as p on e.nameId = p.nameId inner join dishes as d on e.dish = d.dish "
				+ "where d.dish = \"cheesecake\" or d.dish in (select di.dish " + "from dishes as di "
				+ "where di.category >= 1.0) " + "group by p.nameId, d.category");
		AritzQueries
				.add("select em.Fname, wo.Hours " + "from works_on as wo inner join employee as em on wo.Essn = em.Ssn "
						+ "where wo.Pno = (select pr.Pnumber " + "from project as pr " + "where pr.Dnum = 1) "
						+ "group by em.Fname, wo.Hours, em.Lname " + "order by em.Lname");
		AritzQueries.add("select distinct fre.nameId " + "from frequents as fre " + "where not exists (select *  "
				+ "from (select restaurname " + "from frequents " + "where nameId = \"Kevin\") as KR "
				+ "where KR.restaurname not in (select restaurname " + "from frequents as freque "
				+ "where freque.nameId = fre.nameId))");

		ZdravkoQueryText.add("Retrieve the name and the trips of the guides who speak Basque and have gone to Beirut.");
		ZdravkoQueryText
				.add("Retrieve the all the information of the customer(s) that have gone to Mars and not to Everest.");
		ZdravkoQueryText
				.add("Retrieve the first name and last name of the those employees that work in project 1 and 2.");

		ZdravkoQueries.add("select distinct tou.guidename, tr.TripTo "
				+ "from (tourguide as tou inner join languages as lang on tou.GuideId = lang.GuideId) "
				+ " inner join trip as tr on tou.GuideId = tr.GuideId "
				+ "where lang.Lang =\"Euskera\" and tou.GuideId in (select trip.GuideId " + "from trip "
				+ "where trip.TripTo = \"Beirut\")");
		ZdravkoQueries.add("select c.CustomerId, c.custname, c.custaddress, c.custphone " + "from customer as c "
				+ "where exists(select * from hotel_trip_customer as htc where htc.CustomerId = c.CustomerId and htc.TripTo = \"Mars\") "
				+ "and not exists(select * from hotel_trip_customer as htc_vive where htc_vive.CustomerId = c.CustomerId and htc_vive.TripTo = \"Everest\")");
		ZdravkoQueries.add("select em.Fname, em.Lname " + "from employee as em "
				+ "where exists(select * from works_on as wo1 where wo1.Essn = em.Ssn and wo1.Pno = 1) "
				+ "and exists(select * from works_on as wo2 where wo2.Essn = em.Ssn and wo2.Pno = 2)");
	}

	public ArrayList<String> getAritzQueryText() {
		return AritzQueryText;
	}

	public ArrayList<String> getAritzQueries() {
		return AritzQueries;
	}

	public ArrayList<String> getZdravkoQueryText() {
		return ZdravkoQueryText;
	}

	public ArrayList<String> getZdravkoQueries() {
		return ZdravkoQueries;
	}

}
