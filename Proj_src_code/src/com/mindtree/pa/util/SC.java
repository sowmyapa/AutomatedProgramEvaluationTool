package com.mindtree.pa.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SC {
	public static void main() throws Exception
	{
		int num1,num2,result;
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		//System.out.prinln("1.Addition");
		//System.out.println("2.Subtraction");
		//System.out.println("3.Multiplication");
		//System.out.println("4.Division");
		//System.out.println("Choose your option);
		int ch=Integer.parseInt(stdin.readLine());
		switch(ch)
		{
		case 1:	try
		       {
		         num1=Integer.parseInt(stdin.readLine());
		         num2=Integer.parseInt(stdin.readLine());
		         result=num1+num2+num1;
		         System.out.println(result);
		       }
		       catch(IOException e)
		       {
		         System.out.println("input error"+e);
	           }
		       break;
		case 2:try
	           {
	             num1=Integer.parseInt(stdin.readLine());
	             num2=Integer.parseInt(stdin.readLine());
	             result=num1-num2;
	             System.out.println(result);
	           }
	           catch(IOException e)
	           {
	             System.out.println("input error"+e);
               }
	           break;
		case 3:try
	           {
	             num1=Integer.parseInt(stdin.readLine());
	             num2=Integer.parseInt(stdin.readLine());
	             result=num1*num2;
	             System.out.println(result);
	           }
	           catch(IOException e)
	           {
	             System.out.println("input error"+e);
               }
	           break;
		case 4:try
	           {
	             num1=Integer.parseInt(stdin.readLine());
	             num2=Integer.parseInt(stdin.readLine());
	             result=num1/num2;
	             System.out.println(result);
	           }
	           catch(IOException e)
	           {
	             System.out.println("input error"+e);
               }  
	           break;
	  default: System.exit(0);
	  
	  }

}

}
