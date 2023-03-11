package bjoon_test_01;

import java.util.Scanner;

public class BJ_11382 {
	public static void main(String[] args) {
		/* 1 ≤ A, B, C ≤ 10^12 → long 타입 사용해야함*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print(sc.nextLong()+sc.nextLong()+sc.nextLong());
		sc.close();
	}
}
