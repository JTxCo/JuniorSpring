/*
 * assignment_1_test.s
 *
 *  Created on: April 18th, 2022
 *      Author: Joel Carlson
 */

 // Section .crt0 is always placed from address 0
	.section .crt0, "ax"

_start:
	.global _start

 addi	x4, x0, 'J'			// place your first initial, 'lower case', between the single quotes such as 'f'
 slli	x4, x4, 8
 addi	x4, x4, 'C'			// place your last initial, 'lower case', between the single quotes such as 'l'
 slli	x4, x4, 8
 addi	x3, x0, 0xa5		// load 0xa5 into x3
 nop	//0xa5				// use the register view and record the value in register x3
 or		x5, x4, x3
 nop					// use the register view and record the value in register x5
 nop
 nop                    // at the end of this comment, provide the value in the register x3. x3 = 0xa5
 nop                    // at the end of this comment, provide the value in the register x4, x4 = 0x4a4300
 nop                    // at the end of this comment, provide the value in the register x5, x5 =0x4a43a5
 halt
 nop
 nop
 nop
