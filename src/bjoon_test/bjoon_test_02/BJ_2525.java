package bjoon_test_02;

import java.util.Scanner;

public class BJ_2525 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();	// ���� ��
		int b = sc.nextInt();	// ���� ��
		
		int c = sc.nextInt();	// �丮 �ʿ� �ð�
		
		int d = 60 * a + b;		// ���� �ð� �д����� �ٲٱ�
		d += c;					// �丮 �ð� ��ġ��
		
		int e = (d/60) % 24;
		int f = d % 60;
		
		System.out.println(e + " " + f);
		
		sc.close();
	}
}
