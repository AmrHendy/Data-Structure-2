package main;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

import perfect.linear.LinearSolver;
import perfect.quadratic.QuadraticSolver;
import perfect.solver.ISolver;

public class Main {

	public static void main(String[] args) {
		while(true){
			DataReader dataReader = new DataReader();
			Scanner scanner = new Scanner(System.in);
			String path = scanner.nextLine();
			int[] keySet = dataReader.getData(path);
			System.out.println("Size = " + keySet.length);
			ISolver linearSolver = new LinearSolver();
			ISolver quadraticSolver = new QuadraticSolver();
			linearSolver.build(keySet);
			System.out.println("Success Linear");
			if(keySet.length<10000){
				quadraticSolver.build(keySet);
				System.out.println("Success Quadratic");
			}
			System.out.println("Enter key to Search");
			while(true){
				int keyItem = scanner.nextInt();
				if(keyItem>=0 && linearSolver.search(keyItem))System.out.println("Found it");
				else System.out.println("Not Existing in Table");
			}
		}
	}

	public static boolean checkSearch(int[] keySet, ISolver solver) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int key : keySet) {
			set.add(key);
		}

		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			int key = random.nextInt(100000);
			key = -72424832;
			if (set.contains(key) != solver.search(key)) {
				System.out.println("error");
			}
		}

		for (int i = 0; i < keySet.length; i++) {
			if (set.contains(keySet[i]) != solver.search(keySet[i])) {
				System.out.println("error");
			}
		}
		System.out.println("success searching");
		return true;
	}
}