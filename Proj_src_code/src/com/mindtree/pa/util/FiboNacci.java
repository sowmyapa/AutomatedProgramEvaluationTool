package com.mindtree.pa.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FiboNacci{

public static void main() throws Exception {


BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

int f0 = 0;
int f1 = 1;
System.out.println("Enter the number of digits");
String data = stdin.readLine();
int N = Integer.parseInt(data);

for (int i = 0; i < N; ++i) {
System.out.println(f0);

final int temp = f1;
f1 += f0;
f0 = temp;
}

} 
}