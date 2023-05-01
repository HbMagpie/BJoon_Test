package bjoon_test_05;

import java.util.Scanner;

public class BJ_2675 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int i = 0; i < T; i++) {
		int a = sc.nextInt();
		String b = sc.next();
		
		for(int j = 0; j < b.length(); j++) {
			for(int k = 0; k < a; k++) {
				System.out.print(b.charAt(j));
			}
		}
		System.out.println();
		
		}
		sc.close();
	}
}