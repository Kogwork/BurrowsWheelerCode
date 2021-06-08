package assign2;

/**
 * Assignment 2
 * Submitted by: 
 * Student 1.Danny Kogel 	ID# 318503257
 * Student 2.Alex Breger 	ID# 205580087
 */

import base.Compressor;

import java.util.Arrays;
import java.util.Collections;


public class BWEncoderDecoder implements Compressor
{
	static int SAVEDINDEX = 0; //saves the key index
	static String SAVEDSTRING = ""; //saves the key string

	public static void main(String args[]) {
		new BWEncoderDecoder();
	}

	public BWEncoderDecoder()
	{
		String[] input_names = {"Project Gutenberg's Frankenstein,I by Mary Wollstonecraft (Godwin) Shelley",""}; //input names as instructed
		String[] output_names =  {"",""}; //output names as instructed
		Compress(input_names,output_names);
		Decompress(output_names,input_names); // run for decompression
		System.out.println("[Key string]: "+ output_names[0]);
		System.out.println("[Key index]: " + output_names[1]);
		System.out.println("[original String]: " + input_names[0]);
	}

	@Override
	public void Compress(String[] input_names, String[] output_names)
	{
		String str = input_names[0];
		int length = str.length();

		//we'll build a char 2d array to implement all permutations of rotating chars
		char[][] shuffle_Matrix = new char[length][length];
		for(int i = 0;i < length;i++){
			for(int j = 0;j < length;j++){
				shuffle_Matrix[i][j] = str.charAt((j+i)%length);
			}
		}
		//transfer the char array into string array so we could use the Array.sort function
		//to sort it lexicographicly.
		String[] sortedArr = new String[length];
		for(int i = 0;i<length;i++){
			sortedArr[i] = "";
			for(int j = 0;j<length;j++) {
				sortedArr[i] += shuffle_Matrix[i][j];
			}
		}

		Arrays.sort(sortedArr);
		//saves the last char in each string to create the key string
		for(int i = 0;i < length;i++) {
			SAVEDSTRING += sortedArr[i].substring(length-1,length);
			if (sortedArr[i].equals(str)) { //if string matches input string, save the index
				SAVEDINDEX = i;
			}
		}
		output_names[0] = SAVEDSTRING; //we'll remember it in a global var
		output_names[1] = Integer.toString(SAVEDINDEX);


	}


	@Override
	public void Decompress(String[] input_names, String[] output_names)
	{
		String str = input_names[0];
		String index = input_names[1];
		int length = str.length();

		//create string array and input the key string
		//from the end of the string and sort each iteration
		//until we'll have to original char matrix
		String[] decString = new String[length];
		Arrays.fill(decString,"");
		for(int i = 0;i < length;i++){
			for(int j = 0;j < length;j++){
				decString[j] = str.charAt(j) + decString[j];
			}
			Arrays.sort(decString);
		}

		output_names[0] = decString[Integer.parseInt(index)];

		//the string at the key index will be the original string
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
