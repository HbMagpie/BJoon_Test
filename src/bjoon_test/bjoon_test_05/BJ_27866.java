package bjoon_test_05;

import java.util.Scanner;

public class BJ_27866 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String a = sc.next();
		int b = sc.nextInt();
		
		char c = a.charAt(b-1);
		System.out.print(c);
		
		sc.close();
	}
}