package com.mindtree.pa.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TestReverse
{
	public static void main(String args[]) throws IOException
 	{
		int i,len;
		System.out.println("Enter a line to be reversed");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inData;
		inData = br.readLine();
		len = inData.length();
		StringBuffer dest = new StringBuffer(len);

	    for (i = (len - 1); i >= 0; i--)
	      dest.append(inData.charAt(i));
		//ReverseString rs = new ReverseString();

		System.out.println("Reversed: " + dest.toString());	
	}

}


/*//class ReverseString {
  public String reverse(String source) {
    int i, len = source.length();
    StringBuffer dest = new StringBuffer(len);

    for (i = (len - 1); i >= 0; i--)
      dest.append(source.charAt(i));
    return dest.toString();
  }
}*/

           
       