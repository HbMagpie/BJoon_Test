package bjoon_test_05;

import java.util.Scanner;

public class BJ_2908 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 문자열을 숫자로 바꾸기
		String a = sc.next();
		String b = sc.next();
		int arr1[] = new int[a.length()];
		int arr2[] = new int[b.length()];
				
		// 문자열에 있는 문자로된 숫자를 각각의 int형 정수로 변환
		for(int i = 0; i < a.length(); i++) {
			arr1[i] = a.charAt(i) - '0';
			arr2[i] = b.charAt(i) - '0';
		}
				
		// arr1[0]값과 arr1[2]값 교환
		int temp = arr1[0];
		arr1[0] = arr1[2];
		arr1[2] = temp;
				
		int temp2 = arr2[0];
		arr2[0] = arr2[2];
		arr2[2] = temp2;
				
		// 다시 int형을 String형으로 변환
		String A = arr1[0]+ "" + arr1[1] + "" + arr1[2];
		String B = arr2[0]+ "" + arr2[1] + "" + arr2[2];
				
		// 다시 String형을 int형으로 바꿔서 최종 출력
		if(Integer.parseInt(A) > Integer.parseInt(B)) {
			System.out.print(A);
		}
		else {
			System.out.print(B);
		}
		sc.close();
	}
}