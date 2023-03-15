package bjoon_test_03;

import java.util.Scanner;

public class BJ_10950 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int arr[] = new int[a];		// 배열 초기화
		
		for(int i=0; i<a; i++) {	// a값만큼 테스트 케이스 추가
			int b = sc.nextInt();
			int c = sc.nextInt();
			arr[i] = b+c;			// 각 배열에 b+c값 저장
		}
		sc.close();
		
		for(int k : arr) {
			System.out.println(k);	// 배열에 저장한 값 출력, k는 뭘까
		}
	}
}
