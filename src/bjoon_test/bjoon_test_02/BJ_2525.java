package bjoon_test_02;

import java.util.Scanner;

public class BJ_2525 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();	// 현재 시
		int b = sc.nextInt();	// 현재 분
		
		int c = sc.nextInt();	// 요리 필요 시간
		
		int d = 60 * a + b;		// 현재 시간 분단위로 바꾸기
		d += c;					// 요리 시간 합치기
		
		int e = (d/60) % 24;
		int f = d % 60;
		
		System.out.println(e + " " + f);
		
		sc.close();
	}
}
