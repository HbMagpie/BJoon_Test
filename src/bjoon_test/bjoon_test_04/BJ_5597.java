package bjoon_test_04;

import java.util.Scanner;

public class BJ_5597 {
	public static void main(String[] args) {
		/* 
		 1~30까지 숫자 중 중복 없이 28개 입력, 빠진 숫자 2개 찾기(단, 작은 수 먼저 추출되어야 함)*/
		
		Scanner sc = new Scanner(System.in);
		boolean arr[] = new boolean[31];	// boolean으로 true, false 구분
		
		for(int i = 0; i < 28; i++) {		// 28개 입력한 숫자는 true값이 됨
			arr[sc.nextInt()] = true;
		}
		for(int i = 1; i <= 30; i++) {		
			if(!arr[i]) {					// arr[i]값이 true가 아니면(입력한 숫자가 아니면)
				System.out.println(i);		// false값 출력(1~30 순서대로 출력되기 때문에 자동으로 작은 수 먼저 추출)
			}
		}
	}
}
