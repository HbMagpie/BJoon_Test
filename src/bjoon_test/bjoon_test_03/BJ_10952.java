package bjoon_test_03;

import java.util.Scanner;

public class BJ_10952 {
	public static void main(String[] args) {
		// 10952 = 0, 0값을 입력 받으면 종료
		
		Scanner sc = new Scanner(System.in);
				
		 while(true) {					// 조건이 true(참)이 될 때 까지 반복
					 
			int a = sc.nextInt();		// a, b값 입력
			int b = sc.nextInt();
					
			if(a == 0 && b == 0) {		// a, b = 0 이면 모두 끝냄
				sc.close();
				break;
			}
			System.out.println(a+b);	// println으로 개행 필수
		 }
	}
}
