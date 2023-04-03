package com.wipro.utils;

import java.util.Random;

public class AccountNumGenerator {
	public static long generate() {
		long accountNum = 0;
		Random rand = new Random();
		accountNum += accountNum + rand.nextInt(1000000000);
		return accountNum;
	}
}
