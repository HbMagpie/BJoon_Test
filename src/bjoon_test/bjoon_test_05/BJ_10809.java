package bjoon_test_05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BJ_10809 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
								
		int[] arr = new int[26];				// �迭 �ʱ�ȭ
				
		for(int i = 0; i < arr.length; i++) {	// ��� �迭���� -1 �⺻���� ��
			arr[i] = -1;
		}
				
		String S = br.readLine();				// �ܾ�(S) �Է�
				
		for(int i = 0; i < S.length(); i++) {
			char ch = S.charAt(i);				// charAt() : String���� ����� ���ڿ� �߿��� �� ���ڸ� �����ؼ� charŸ������ ��ȯ
					
		if(arr[ch-'a'] == -1) {					/* ���� ���ڰ� ���ԵǾ��ִ� ��� ó�� ��Ÿ�� ��ġ�� ��Ÿ���� ��
														 (-1 �� ��쿡�� �迭�� ���� ���� �����ϰ� -1 �� �ƴ� ��� �迭�� ���� ���� �������� ����)*/
			arr[ch-'a'] = i;					// ch �� ������ ��ġ�� �ռ� ���� arr �迭�� ������ ��ȯ('a'=97)
			}
		}	
				
		for(int val : arr) {					// ���� �迭 ���
			System.out.print(val + " ");
		}
	} 
}