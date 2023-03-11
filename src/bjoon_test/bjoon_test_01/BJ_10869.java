package bjoon_test_01;

import java.util.Scanner;

public class BJ_10869 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		// 출력 결과 정수형으로 나와야 함.
		System.out.println(a+b);
		System.out.println(a-b);
		System.out.println(a*b);
		System.out.println(a/b);	
		System.out.println(a%b);
		sc.close();
	}
}