package bjoon_test_03;

import java.util.Scanner;

public class BJ_2439 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		
		for(int i = 1; i <= a; i++) {		// i = 1~a까지 반복
			for(int j = 1; j <= a-i; j++) {	// j = 1~i까지 반복(오른쪽 정렬은 음수(-)로, 입력값-i)
				System.out.print(" ");		// 공백 출력
			}
		for(int k = 1; k <= i; k++) {
			System.out.print("*");			// "*" 출력
		}
		System.out.print('\n');				// 반복할 때 마다 개행
		}
		sc.close();
	}
}
