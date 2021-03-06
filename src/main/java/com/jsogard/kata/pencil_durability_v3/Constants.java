package com.jsogard.kata.pencil_durability_v3;

public class Constants {

	public static final int UPPER_CASE_WRITE_COST = 2;
	public static final int LOWER_CASE_WRITE_COST = 1;
	public static final int WHITESPACE_WRITE_COST = 0;
	
	public static final char[] WHITESPACE_CHARACTERS = " \n".toCharArray();
	public static final char[] LOWERCASE_CHARACTERS = "qwertyuiopasdfghjklzxcvnmb".toCharArray();
	public static final char[] UPPERCASE_CHARACTERS = "QWERTYUIOPAFSDGHJKLVZXCBNM".toCharArray();
	
	public static final int CHARACTER_ERASE_COST = 1;
	public static final int WHITESPACE_ERASE_COST = 0;
	
	public static final char COLLISION_CHARACTER = '@';

}
