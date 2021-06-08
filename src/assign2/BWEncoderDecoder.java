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
		String[] input_names = {"Project Gutenberg's Frankenstein, by Mary Wollstonecraft (Godwin) Shelley\n" +
				"\n" +
				"This eBook is for the use of anyone anywhere at no cost and with\n" +
				"almost no restrictions whatsoever.  You may copy it, give it away or\n" +
				"re-use it under the terms of the Project Gutenberg License included\n" +
				"with this eBook or online at www.gutenberg.net\n" +
				"\n" +
				"\n" +
				"Title: Frankenstein\n" +
				"       or The Modern Prometheus\n" +
				"\n" +
				"Author: Mary Wollstonecraft (Godwin) Shelley\n" +
				"\n" +
				"Release Date: June 17, 2008 [EBook #84]\n" +
				"Last updated: January 13, 2018\n" +
				"\n" +
				"Language: English\n" +
				"\n" +
				"Character set encoding: UTF-8\n" +
				"\n" +
				"*** START OF THIS PROJECT GUTENBERG EBOOK FRANKENSTEIN ***\n" +

				"Archangel, 28th March, 17—.\n"};
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
		Decompress(output_names,output_names);
		System.out.println(SAVEDSTRING);
		System.out.println("INDEX" + SAVEDINDEX);

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
		System.out.println(Arrays.deepToString(sortedArr));

		System.out.println(SAVEDSTRING);


	}


	@Override
	public void Decompress(String[] input_names, String[] output_names)
	{
		String str = input_names[0];
		String index = input_names[1];

		int length = str.length();
		String[] decString = new String[length];
		Arrays.fill(decString,"");
		for(int i = 0;i < length;i++){
			for(int j = 0;j < length;j++){
				decString[j] = str.charAt(j) + decString[j];
			}
			Arrays.sort(decString);
			System.out.println(Arrays.deepToString(decString));
		}
		System.out.println(Arrays.deepToString(decString));

		output_names[0] = decString[Integer.parseInt(index)];

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
