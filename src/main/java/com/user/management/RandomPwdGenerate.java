package com.user.management;

import java.util.Random;

public class RandomPwdGenerate {

	public static void main(String[] args) {
		String text="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		Random random=new Random();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1;i<=6;i++)
		{
			int index = random.nextInt(text.length());
		    sb.append(text.charAt(index));	
			
		}
		System.out.println(sb.toString());
		
	}

}
