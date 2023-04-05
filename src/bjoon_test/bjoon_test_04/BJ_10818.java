package bjoon_test_04;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_10818 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int arr[] = new int[a];
		
		for(int i = 0; i < a; i++) {
			arr[i] = sc.nextInt();
		}
		sc.close();
		
		Arrays.sort(arr);	// 배열 오름차순 정렬(최솟값, 최댓값 구하기 쉬워짐) 
		System.out.print(arr[0] + " " + arr[a-1]);
								
		}
	}