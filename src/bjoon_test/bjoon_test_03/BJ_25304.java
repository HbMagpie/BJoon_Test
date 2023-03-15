package bjoon_test_03;

import java.util.Scanner;

public class BJ_25304 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int a = sc.nextInt();
		int arr[] = new int[a];
		int sum = 0;
		
		for(int i=0; i<a; i++) {	// a값만큼 테스트 케이스 추가
			int b = sc.nextInt();
			int c = sc.nextInt();
			arr[i] = b*c;			
		}
		for(int k : arr) {
			sum += k;	
		}
		if(sum == x) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		sc.close();
	}
}
