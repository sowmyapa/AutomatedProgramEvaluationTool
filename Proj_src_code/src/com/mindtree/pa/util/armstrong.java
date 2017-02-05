package com.mindtree.pa.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class armstrong{
public static void main() throws Exception
{

BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
System.out.println("enter the number");
String data = stdin.readLine();
int k=Integer.parseInt(data);
int n=k;
int d=0,s=0;
while(n>0)
{
d=n%10;
s=s+(d*d*d);
n=n/10;
}
if(k==s)

System.out.println("armstrong number");
else
System.out.println("not armstrong number");

}

} 