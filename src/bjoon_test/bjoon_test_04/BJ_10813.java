package bjoon_test_04;

import java.util.Scanner;

public class BJ_10813 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int arr[] = new int[N];					// N만큼 출력해야 하므로 그 숫자에 맞추기 위해 int[N]
		
		for(int a = 0; a < N; a++) {			// 기본 1~5값 입력
			arr[a] = a+1;			
		}
		for(int b = 0 ; b < M; b++) {			// M줄 출력	
			int i = sc.nextInt();		
			int j = sc.nextInt();	
			int temp = arr[i-1];
				arr[i-1] = arr[j-1];			// i, j값 서로 변경(temp 사용)
				arr[j-1] = temp;
		}
		sc.close();	
		
		for(int c = 0; c < arr.length; c++) {	// 배열의 길이만큼 결괏값 출력
			System.out.print(arr[c] + " ");
		}
	}
}