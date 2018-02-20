package com.black_dog20.gadgetron.utility;

import java.util.Random;

public class Helper {

	public static int GetRandonBetween(Random random, int min, int max){
		return random.nextInt((max-min) +1) + min;
	}
}
