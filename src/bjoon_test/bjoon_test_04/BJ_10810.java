package bjoon_test_04;

import java.util.Scanner;

public class BJ_10810 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int arr[] = new int[N];					// N��ŭ ����ؾ� �ϹǷ� �� ���ڿ� ���߱� ���� int[N]
		
		for(int a = 0 ; a < M; a++) {			// M�� ���	
			int i = sc.nextInt();	
			int j = sc.nextInt();
			int k = sc.nextInt();
			for(int b = i-1; b < j; b++) {		// arr[i] ~ arr[j]����
				arr[b] = k;						// k������ ���� 
			}
		}
		sc.close();	
		
		for(int b = 0; b < arr.length; b++) {	// N�� ��ŭ �迭 ���
			System.out.print(arr[b] + " ");
		}
	}
}