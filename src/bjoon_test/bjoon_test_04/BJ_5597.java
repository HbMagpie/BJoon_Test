package bjoon_test_04;

import java.util.Scanner;

public class BJ_5597 {
	public static void main(String[] args) {
		/* 
		 1~30���� ���� �� �ߺ� ���� 28�� �Է�, ���� ���� 2�� ã��(��, ���� �� ���� ����Ǿ�� ��)*/
		
		Scanner sc = new Scanner(System.in);
		boolean arr[] = new boolean[31];	// boolean���� true, false ����
		
		for(int i = 0; i < 28; i++) {		// 28�� �Է��� ���ڴ� true���� ��
			arr[sc.nextInt()] = true;
		}
		for(int i = 1; i <= 30; i++) {		
			if(!arr[i]) {					// arr[i]���� true�� �ƴϸ�(�Է��� ���ڰ� �ƴϸ�)
				System.out.println(i);		// false�� ���(1~30 ������� ��µǱ� ������ �ڵ����� ���� �� ���� ����)
			}
		}
	}
}
