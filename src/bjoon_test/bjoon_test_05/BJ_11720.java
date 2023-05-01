package bjoon_test_05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BJ_11720 {
	public static void main(String[] args) throws IOException {
		// BufferedReader ���(������ ���� ���� �� ȿ����)
		/* getBytes() = �����ڵ� ���ڿ��� ����Ʈ�ڵ�� ���ڵ� ���ִ� �޼ҵ�
		   getBytes( )�� ���ڷ� ĳ���ͼ��� �ѱ��� ������ ����Ʈ charset���� �ڵ�
		   ���ڿ��� byte[] �迭�� �ٲٴ� �޼ҵ�		
		*/
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		bf.readLine();
		
		int sum = 0;
		
		for(byte value : bf.readLine().getBytes()) {
			sum += (value - '0');
		}
		System.out.println(sum);
	} 
}