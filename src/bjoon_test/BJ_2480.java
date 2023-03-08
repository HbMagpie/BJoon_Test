package bjoon_test_02;

import java.util.Scanner;

public class BJ_2480 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();	// dice1
		int b = sc.nextInt();	// dice2
		int c = sc.nextInt();	// dice3
		
		int d = Math.max(a, b);
		int e = Math.max(d, c);
		
		if(a == b && b == c) {
			System.out.print(10000+a*1000);
		}
		else if(a == b || a == c) {
			System.out.print(1000+a*100);
		}
		else if(b == c) {
			System.out.print(1000+b*100);
		}
		else {		
			System.out.print(e*100);	// 자바에서 함수사용시 Math.붙여야 함
		}
		sc.close();
		
	}
}
