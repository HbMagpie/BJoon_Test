package bjoon_test_05;

import java.util.Scanner;

public class BJ_9086 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		String a;
		int lastx;
		
		for(int i = 0; i < T; i++) {
			a = sc.next();
			char b = a.charAt(0);				// ù��° ��(0)
			lastx = a.length()-1;				// ������ �ε��� ��(a���ڿ� ����-1)
			char c = a.charAt(lastx);			// ������ ��(lastx)
			System.out.println(b + "" + c);		// ���� ���� ���
		}	
		sc.close();
		}
	}