package bjoon_test_03;

import java.util.Scanner;

public class BJ_10951 {
	public static void main(String[] args) {
		// EOF(End Of File) = �Է¿��� ���̻� ���� �� �ִ� �����Ͱ� ���� �� �����
		
		Scanner sc = new Scanner(System.in);
				
		 while(sc.hasNextInt()) {
			 /* �Է°��� �����ϰ�� true�� ��ȯ�ϸ� ������ �ƴҰ�� �ٷ� ���ܸ� ������
			    ���̻��� �Է��� ���� �ʰ� hasNextInt()���� false�� ��ȯ�ϸ鼭 �ݺ����� �����
			  */
					 
			 int a = sc.nextInt();
			 int b = sc.nextInt();
			 System.out.println(a+b);
		 }
		 sc.close();
	}
}
