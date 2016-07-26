package problem19;

//Calculating # of Sundays that fall on the first of any month;

//1900 Jan 1 was a Monday
/*
 * January		1 -> 31
 * February		2 -> n
 * n=28;
 * if(year%4==0)
 * if(year%100!=0 || year%400==0)
 * 	n= 29
 * March		3 -> 31
 * April		4 -> 30
 * May			5 -> 31
 * June			6 -> 30
 * July			7 -> 31
 * August		8 -> 31
 * September	9 -> 30        
 * October		10-> 31
 * November		11-> 30
 * December		12-> 31
 * 
 * 
 */

public class problem19 {

	public static void main(String[] arg) {
		int days = 0;
		int sundays = 0;
		for (int i = 1900; i < 2000; i++)
			for (int j = 0; j < 12; j++) {
				if ((days + 1) % 7 == 0)
					sundays++;
				days += daysM(i, j);
			}
		System.out.println(sundays);

	}

	public static int daysM(int year, int month) {
		if (month == 11 || month == 9 || month == 6 || month == 4)
			return 30;
		if (month == 2) {
			if (year % 4 == 0)
				if (year % 100 != 0 || year % 400 == 0)
					return 29;
			return 28;
		}

		return 31;
	}

}
