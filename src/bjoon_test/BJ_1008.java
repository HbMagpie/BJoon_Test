package bjoon_test;

import java.util.Scanner;

public class BJ_1008 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		double c = (a*1.0)/b;
		
		System.out.println(c);
		sc.close();
	}
}