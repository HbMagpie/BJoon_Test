package bjoon_test_04;

import java.util.Scanner;

public class BJ_10810 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int arr[] = new int[N];					// N만큼 출력해야 하므로 그 숫자에 맞추기 위해 int[N]
		
		for(int a = 0 ; a < M; a++) {			// M줄 출력	
			int i = sc.nextInt();	
			int j = sc.nextInt();
			int k = sc.nextInt();
			for(int b = i-1; b < j; b++) {		// arr[i] ~ arr[j]까지
				arr[b] = k;						// k값으로 변경 
			}
		}
		sc.close();	
		
		for(int b = 0; b < arr.length; b++) {	// N값 만큼 배열 출력
			System.out.print(arr[b] + " ");
		}
	}
}