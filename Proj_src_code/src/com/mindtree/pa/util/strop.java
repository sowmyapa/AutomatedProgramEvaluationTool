package com.mindtree.pa.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class strop
{
public static void main() throws IOException
{
String s1,S2;
int c=0,i=0,j=0,slen=0;
char b[]=new char[40];
BufferedReader s=new BufferedReader (new InputStreamReader(System.in));
System.out.println("\t\n Enter the String :");
s1= s.readLine();
slen=s1.length();
char wor[]=new char[slen];
s1.getChars(0,slen,wor,0);





for(i=wor.length-1,j=0;i>=0;i--,j++)
b[j]=wor[i];
System.out.println("\t\n Reversed String is:\n");
for(i=0;i<=wor.length;i++)
{
System.out.print(b[i]);
}


boolean pal = true;
i=0;
j=slen-1;
while(i!=j)
{
if(s1.charAt(i) == s1.charAt(j))
{
i++;
j--;
if(i>j)
break;
}
else
{
pal = false;
break;
}
}
if(pal == true)
System.out.println("The string is a palindrome");
else
System.out.println("The string is not a palindrome");


}

} 