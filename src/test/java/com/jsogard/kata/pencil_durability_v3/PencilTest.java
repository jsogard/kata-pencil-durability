package com.jsogard.kata.pencil_durability_v3;

import junit.framework.TestCase;

public class PencilTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/* 
	 * TC01-PENCIL_INIT
	 * When a pencil is created, it can be provided with a value for point durability
	 * A pencil should also be created with an initial length value.
	 * When a pencil is created, it can be provided with a value for eraser durability
	 */
	
	/*
	 * TC03-POINTDEG_WHITESPACE_WRITE
	 * Writing spaces and newlines expends no graphite; therefore these characters should not affect the pencil point
	 */
	
	/*
	 * TC04-POINTDEG_LOWERCASE_WRITE
	 * Lowercase letters should degrade the pencil point by a value of one
	 */
	
	/*
	 * TC05-POINTDEG_UPPERCASE_WRITE
	 * capital letters should degrade the point by two
	 */
	
	/*
	 * TC06-SHARPEN_POINT_DUR
	 * When a pencil is sharpened, it regains its initial point durability
	 */
	
	/*
	 * TC07-SHARPEN_REDUCE_LENGTH
	 * The pencil's length is reduced by one each time it is sharpened
	 */
	
	/*
	 * TC08-SHARPEN_NO_LENGTH
	 * When a pencil's length is zero, then sharpening it no longer restores its point durabliity
	 */
	
	/*
	 * TC10-ERASERDEG_WHITESPACE
	 * all characters except for white space should degrade the eraser by a value of one
	 */
	
	/*
	 * TC11-ERASERDEG_CHARACTER
	 * all characters except for white space should degrade the eraser by a value of one
	 */
	
	

}
