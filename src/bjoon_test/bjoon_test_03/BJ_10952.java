package bjoon_test_03;

import java.util.Scanner;

public class BJ_10952 {
	public static void main(String[] args) {
		// 10952 = 0, 0���� �Է� ������ ����
		
		Scanner sc = new Scanner(System.in);
				
		 while(true) {					// ������ true(��)�� �� �� ���� �ݺ�
					 
			int a = sc.nextInt();		// a, b�� �Է�
			int b = sc.nextInt();
					
			if(a == 0 && b == 0) {		// a, b = 0 �̸� ��� ����
				sc.close();
				break;
			}
			System.out.println(a+b);	// println���� ���� �ʼ�
		 }
	}
}
