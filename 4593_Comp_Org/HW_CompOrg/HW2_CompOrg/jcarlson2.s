

 // Section .crt0 is always placed from address 0
	.section .crt0, "ax"

_start:
	.global _start

 /*
 	Assembly Abstraction assignment
 	Note:
 	  - As a minimum, you should plan to allocate registers for the following to
 	    to implement the routine

 	    - maximum count of loop which will be initialized to your first initial, lowercase
	         - you can tell the assembler to evaluate your initial by specifying a 
		 character
		 - example:  addi  x10, x0, 'k'
 	    - count variable, i, that will go from 0 to maximum count
 	    	- or, the count variable could be assigned max value and count to 0
 	    - result variable which is initialized to 0
 	    - memory address to store result initialized to 0x2000


 	    hint:  you can use the RISCV add immediate instruction, addi, to initialize
 	        a register

 	Start here
 */

// Plan (Declare) by making a comment for each register that you will be using and its purpose
// These comments will be helpful to refer back to determine which register to use 
/* 
x1 = 0x200 for the result address
x10 = letter J unsigned
x11 = loop count set to 0
x12 = resulting variable
x13 = temp reg to assist with the moving if needed

*/


// Initialize all of your registers.  Immediate instructions are recommended where possible
    lui x1, 0x2 // 0x20b7
    addi x10, x0, 'k' //  0x8b00513
    addi x11, x0, 0 //i.  0x593
    addi x12, x0, 0 // result.  0x613


// Implement your for loop
    loop:
        beq x11, x10 , end # if  == t1breaktarget  //0xa58a63
        addi x12,  x12, 1 # result = result + 1  //0x160613
        add x12, x12, x10 // result  = result + J //0xa60633
        addi x11, x11, 1 // 0x158593
        j loop //0xff1ff06f
    end://0xa58a63
// Store into memory the result(s)
    sw x10,  0(x1)// store letter 0xa0a023
    sw x12,  4(x1) // 0xc0a223
    sw x11,  8(x1)// //0xb0a423


// time to code took much longer because i struggled to get it to work , but in general it did take longer 
//decently difficult for me but not too bad. In machine code it would take forever. Some of them just verifing they were correct took a while
 /*
 	Add your assembly code above this line
 */
	nop//0x13
	nop//0x13
	nop//0x13
	halt	// end of simulation
	nop
	nop
	nop
