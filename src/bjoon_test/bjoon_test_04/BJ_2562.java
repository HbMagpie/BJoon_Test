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
		
		for(int value : arr) {	// 배열 값에 순서대로 값을 매김
			count++;
			
				if(value > max) {	// 최댓값(max)보다 배열 값(value)이 더 크면 그 값이 최댓값이 됨
				max = value;
				index = count;	// 그 최댓값이 있는 배열 인덱스 값을 구함
			}		
		}
		System.out.print(max + "\n" + index);
	}
}