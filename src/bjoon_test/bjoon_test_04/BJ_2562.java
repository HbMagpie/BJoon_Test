package bjoon_test_04;

import java.util.*;

public class BJ_2562 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int arr[] = new int[10];
		
		for(int i = 0; i < 9; i++) {
			arr[i] = sc.nextInt();
		}
		
		sc.close();		
		
		int count = 0;
		int max = 0;
		int index = 0;
		
		for(int value : arr) {	// �迭 ���� ������� ���� �ű�
			count++;
			
				if(value > max) {	// �ִ�(max)���� �迭 ��(value)�� �� ũ�� �� ���� �ִ��� ��
				max = value;
				index = count;	// �� �ִ��� �ִ� �迭 �ε��� ���� ����
			}		
		}
		System.out.print(max + "\n" + index);
	}
}