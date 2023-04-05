package bjoon_test_04;

import java.util.Scanner;

public class BJ_10811 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int arr[] = new int[N];					// N��ŭ ����ؾ� �ϹǷ� �� ���ڿ� ���߱� ���� int[N]
		
		for(int a = 0; a < N; a++) {			// �⺻ 1~5�� �Է�
			arr[a] = a+1;			
		}		
		int count = 0;
		while(count < M) {						// count�� M�� ������ ������ �ݺ�(�� �� ���߱�)
			int i = sc.nextInt()-1;				// �ε��� ���� �����ֱ� ���� -1�� ����
			int j = sc.nextInt()-1;	
			while(j > i) {						// j���� i������ ũ�� ��ȯ �ݺ�
				int temp = arr[i];				
				arr[i] = arr[j];
				arr[j] = temp;
				j--;							// j�� -1, i�� +1�� ���ָ鼭 �� ���� �������� �ݺ� ����
				i++;
				} 
			count++;							// �� �� ������ count +1
			}	
		for(int b = 0; b < N; b++) {			// ������� �ᱣ�� ���
			System.out.print(arr[b] + " ");
		}
		sc.close();	
		}
	}