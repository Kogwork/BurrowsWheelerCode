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
		String[] input_names = {"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed viverra vitae ligula sit amet tincidunt. Nam pretium, purus at iaculis facilisis, dui justo ornare velit, vel placerat neque quam quis magna. Donec vestibulum maximus sollicitudin. Nulla lacus turpis, facilisis id risus eu, facilisis egestas risus. Cras rutrum quam in tempus interdum. Nulla vehicula nisi odio, in varius nulla laoreet ut. Nam iaculis accumsan tortor ut sodales. Praesent convallis, arcu sit amet elementum egestas, ipsum est mollis odio, nec euismod leo urna ac orci. Vivamus pellentesque aliquet egestas.\n" +
				"\n" +
				"Cras vitae rhoncus lorem. Integer nisl nibh, ullamcorper et ante pretium, euismod laoreet sapien. Pellentesque laoreet, arcu sit amet eleifend pharetra, nulla tortor congue odio, id malesuada tortor enim non urna. Proin rutrum eget neque pretium sollicitudin. Nunc rutrum tortor mi, vestibulum mattis felis venenatis eu. Integer malesuada condimentum est volutpat vestibulum. Morbi vel convallis eros. Quisque suscipit imperdiet sapien sit amet sagittis. Etiam ut elementum urna, non ullamcorper nibh. Suspendisse pellentesque, ante a gravida elementum, lacus lacus maximus augue, in faucibus ipsum leo non tellus. Ut ut lobortis turpis, ac scelerisque nisl.\n" +
				"\n" +
				"Pellentesque efficitur turpis risus, at pretium odio auctor eu. Aenean eu arcu eget sem condimentum convallis. Fusce ex eros, tempor sed enim sit amet, rhoncus ullamcorper odio. Vivamus cursus congue erat, at viverra nunc hendrerit a. Ut scelerisque quis sem a blandit. Proin placerat aliquet orci sed gravida. Cras sagittis euismod elit, sit amet hendrerit tellus faucibus et. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Nullam ut sem felis. Sed pellentesque blandit nunc, quis sodales metus dapibus elementum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.\n" +
				"\n" +
				"Quisque ornare velit eu condimentum sollicitudin. Quisque ut massa et metus tincidunt dignissim. Vivamus quis lectus dictum, ornare diam eu, eleifend ex. Phasellus eget urna felis. Nulla tristique massa risus, sit amet molestie metus suscipit quis. Aliquam id ante justo. Morbi vulputate nunc sed velit iaculis tincidunt. Cras sed erat ac nunc venenatis ultrices.",""}; //input names as instructed
		String[] output_names =  {"",""}; //output names as instructed
		Compress(input_names,output_names);
		Decompress(output_names,input_names); // run for decompression
		System.out.println("[Key string]: "+ input_names[0]);
		System.out.println("[Key index]: " + input_names[1]);
		System.out.println("[original String]: " + output_names[0]);
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
		output_names[0] = input_names[0];
		output_names[1] = input_names[1];
		input_names[0] = decString[Integer.parseInt(index)];
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
