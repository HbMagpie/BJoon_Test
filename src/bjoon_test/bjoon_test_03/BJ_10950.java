package bjoon_test_03;

import java.util.Scanner;

public class BJ_10950 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int arr[] = new int[a];		// �迭 �ʱ�ȭ
		
		for(int i=0; i<a; i++) {	// a����ŭ �׽�Ʈ ���̽� �߰�
			int b = sc.nextInt();
			int c = sc.nextInt();
			arr[i] = b+c;			// �� �迭�� b+c�� ����
		}
		sc.close();
		
		for(int k : arr) {
			System.out.println(k);	// �迭�� ������ �� ���, k�� ����
		}
	}
}
