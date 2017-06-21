package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataReader {
	
	public int[] getData(String fileName){
		File inputFile = new File(fileName);
		BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(inputFile));
		
			StringBuilder inputString = new StringBuilder("");
			while(bufferedReader.ready()){
				String str = bufferedReader.readLine();
				inputString = inputString.append(str);
			}
			String[] numbers = inputString.toString().split(",");
			int[] keySet = new int[numbers.length];
			for(int i=0;i<numbers.length;i++){
				keySet[i] = Integer.parseInt(numbers[i]);
			}
			return keySet;
		} catch (Exception e) {
			return new int[0];
		}
	}
}
