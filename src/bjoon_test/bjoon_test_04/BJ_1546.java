package bjoon_test_04;

import java.util.Scanner;

public class BJ_1546 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();			// ���� ����(N) �Է�
		int arr[] = new int[N];			// �迭 �ʱ�ȭ
		int M = 0;						// �ִ�(M) �ʱ�ȭ
		float sum = 0, a = 0;			// a = ���� �� ����(�Ǽ���)
						
		for(int i = 0; i < N; i++) {	// �������ŭ ���� �Է�
			arr[i] = sc.nextInt();					
				if(M < arr[i]) {		// �ִ� ���ϱ�
					M = arr[i];				
				}		
			}
			for(int i = 0; i < N; i++) {	// ��� ���� ����(����/�ִ�*100)
			 a = ((float)arr[i]/M*100);
			 sum += a;
			}
			System.out.print(sum/N);	// �� ��� �� ���
			sc.close();
		}
	}