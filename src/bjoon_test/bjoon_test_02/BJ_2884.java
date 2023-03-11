package bjoon_test_02;

import java.util.Scanner;

public class BJ_2884 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();	
		int c;
		
		if(a == 0) {
			a = 24;
			c = (a*60) + b -45;
		}
		else {
			c = (a*60) + b -45;	
		}
		System.out.print((c/60)%24 + " " + (c%60));	// %24∏¶ «ÿ¡‡æﬂ «‘.
		sc.close();
	}
}
