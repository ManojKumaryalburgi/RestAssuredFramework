package com.Rmgyantra.genericUtils;

import java.util.Random;

public class JavaUtils
{
	public static int getrandomnumber()
	{
		Random random=new Random();
		int ran = random.nextInt(1000);
		return ran;
	}

}
