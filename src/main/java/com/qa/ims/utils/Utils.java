package com.qa.ims.utils;

import java.util.Scanner;

public class Utils {
	
	public static final String MYSQL_URL = "34.89.86.245:3306";
	public static final Scanner SCANNER = new Scanner(System.in);

	private Utils() {

	}

	public static String getInput() {
		return SCANNER.nextLine();
	}
	public static double getInput1() {
		return SCANNER.nextDouble();
	}
	public static int getInput2() {
		return SCANNER.nextInt();
	}
	public static Long getInput3() {
		return SCANNER.nextLong();
	}

}
