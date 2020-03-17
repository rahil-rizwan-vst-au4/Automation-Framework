package Utilities;

public class ReturnDemo {

	public static void main(String[] args) {
		String test = "";
//		int number = 4;
//		if(number % 2 == 0)
//			test = "even";
//		else 
//			test = "odd";
//		
//		number = 6;
//		if(number % 2 == 0)
//			test = true;
//		else 
//			test = false;
//		
//		number = 5;
//		if(number % 2 == 0)
//			test = true;
//		else 
//			test = false;
		test = checkDividedBy2(4);
		test = checkDividedBy2(6);
		test = checkDividedBy2(5);
		
		
		System.out.println(test);
	}

	private static String checkDividedBy2(int number) {
		if(number % 2 == 0)
			return "even";
		else 
			return "odd";
	}

}
