package assign2;

/**
 * Assignment 2
 * Submitted by: 
 * Student 1. 	ID# XXXXXXXXX
 * Student 2. 	ID# XXXXXXXXX
 */

import base.Compressor;

import java.util.Arrays;
import java.util.Collections;


public class BWEncoderDecoder implements Compressor
{
	static int SAVEDINDEX = 0;
	static String SAVEDSTRING = "";
	public static void main(String args[]) {
		new BWEncoderDecoder();
	}

	public BWEncoderDecoder()
	{
		String[] input_names = {"Banana"};
		String[] output_names =  {"John", "Mary", "Bob"};
		Compress(input_names,output_names);
	}

	@Override
	public void Compress(String[] input_names, String[] output_names)
	{
		String str = input_names[0];
		int length = str.length();

		char[][] shuffle_Matrix = new char[length][length];
		for(int i = 0;i < length;i++){
			for(int j = 0;j < length;j++){
				shuffle_Matrix[i][j] = str.charAt((j+i)%length);
			}
		}
		System.out.println(Arrays.deepToString(shuffle_Matrix));
		lexiArraySort(shuffle_Matrix,str);
		output_names[0] = SAVEDSTRING;
		output_names[1] = Integer.toString(SAVEDINDEX);
	}


	public void lexiArraySort(char[][] arr,String str){
		int length = arr.length;

		String[] sortedArr = new String[length];
		for(int i = 0;i<length;i++){
			sortedArr[i] = "";
			for(int j = 0;j<length;j++) {
				sortedArr[i] += arr[i][j];
			}
		}
		Arrays.sort(sortedArr);
		for(int i = 0;i < length;i++) {
			SAVEDSTRING += sortedArr[i].substring(length-1,length);
			if (sortedArr[i].equals(str)) {
				SAVEDINDEX = i;
			}
		}

	}
	public static String stringSorter(String str)
	{
		// convert input string to char array
		char tempArray[] = str.toCharArray();

		// sort tempArray
		Arrays.sort(tempArray);

		// return new sorted string
		return new String(tempArray);
	}
	public String BWdecode(String str,int index){
		int length = str.length();
		char[][] buildArr = new char[length][length];
		for(int i = 0;i < length;i++){
			for(int j = 0;j < length;j++){
				buildArr[i][length-j-1] = str.charAt(j);
			}

		}

		return "";
	}

	@Override
	public void Decompress(String[] input_names, String[] output_names)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] CompressWithArray(String[] input_names, String[] output_names)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] DecompressWithArray(String[] input_names, String[] output_names)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
