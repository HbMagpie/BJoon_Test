package bjoon_test_04;

import java.util.Scanner;

public class BJ_3052 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean arr[] = new boolean[42];	// boolean의 길이 = 42
		int count = 0;
		
		for(int i = 0; i < 10; i++) {
			arr[sc.nextInt() % 42] = true;	// 배열에 % 42한 값을 모두 true로 저장
		}
			for(boolean value : arr) {		
				if(value) {					
					count++;
				}
			}				
			System.out.print(count);
			sc.close();
	}
}