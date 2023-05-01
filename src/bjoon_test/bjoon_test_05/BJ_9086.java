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
			char b = a.charAt(0);				// 첫번째 값(0)
			lastx = a.length()-1;				// 마지막 인덱스 값(a문자열 길이-1)
			char c = a.charAt(lastx);			// 마지막 값(lastx)
			System.out.println(b + "" + c);		// 공백 없이 출력
		}	
		sc.close();
		}
	}