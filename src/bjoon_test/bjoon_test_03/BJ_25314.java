package bjoon_test_03;

import java.util.Scanner;

public class BJ_25314 {
	public static void main(String[] args) {
		/* long int = 4, long long int = 8(int ���� long �ϳ��� 4�� ����) */
		Scanner sc = new Scanner(System.in);
		
		
		int a = sc.nextInt() / 4;
		
		for(int i = 0; i < a; i++) {
			System.out.print("long ");
		}
		System.out.print("int");
		sc.close();
	}
}