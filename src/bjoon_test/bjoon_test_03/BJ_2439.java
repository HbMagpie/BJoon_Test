package bjoon_test_03;

import java.util.Scanner;

public class BJ_2439 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		
		for(int i = 1; i <= a; i++) {		// i = 1~a���� �ݺ�
			for(int j = 1; j <= a-i; j++) {	// j = 1~i���� �ݺ�(������ ������ ����(-)��, �Է°�-i)
				System.out.print(" ");		// ���� ���
			}
		for(int k = 1; k <= i; k++) {
			System.out.print("*");			// "*" ���
		}
		System.out.print('\n');				// �ݺ��� �� ���� ����
		}
		sc.close();
	}
}
