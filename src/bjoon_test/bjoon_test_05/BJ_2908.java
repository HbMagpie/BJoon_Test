package bjoon_test_05;

import java.util.Scanner;

public class BJ_2908 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// ���ڿ��� ���ڷ� �ٲٱ�
		String a = sc.next();
		String b = sc.next();
		int arr1[] = new int[a.length()];
		int arr2[] = new int[b.length()];
				
		// ���ڿ��� �ִ� ���ڷε� ���ڸ� ������ int�� ������ ��ȯ
		for(int i = 0; i < a.length(); i++) {
			arr1[i] = a.charAt(i) - '0';
			arr2[i] = b.charAt(i) - '0';
		}
				
		// arr1[0]���� arr1[2]�� ��ȯ
		int temp = arr1[0];
		arr1[0] = arr1[2];
		arr1[2] = temp;
				
		int temp2 = arr2[0];
		arr2[0] = arr2[2];
		arr2[2] = temp2;
				
		// �ٽ� int���� String������ ��ȯ
		String A = arr1[0]+ "" + arr1[1] + "" + arr1[2];
		String B = arr2[0]+ "" + arr2[1] + "" + arr2[2];
				
		// �ٽ� String���� int������ �ٲ㼭 ���� ���
		if(Integer.parseInt(A) > Integer.parseInt(B)) {
			System.out.print(A);
		}
		else {
			System.out.print(B);
		}
		sc.close();
	}
}