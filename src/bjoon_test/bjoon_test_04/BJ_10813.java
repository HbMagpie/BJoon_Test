package bjoon_test_04;

import java.util.Scanner;

public class BJ_10813 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int arr[] = new int[N];					// N��ŭ ����ؾ� �ϹǷ� �� ���ڿ� ���߱� ���� int[N]
		
		for(int a = 0; a < N; a++) {			// �⺻ 1~5�� �Է�
			arr[a] = a+1;			
		}
		for(int b = 0 ; b < M; b++) {			// M�� ���	
			int i = sc.nextInt();		
			int j = sc.nextInt();	
			int temp = arr[i-1];
				arr[i-1] = arr[j-1];			// i, j�� ���� ����(temp ���)
				arr[j-1] = temp;
		}
		sc.close();	
		
		for(int c = 0; c < arr.length; c++) {	// �迭�� ���̸�ŭ �ᱣ�� ���
			System.out.print(arr[c] + " ");
		}
	}
}