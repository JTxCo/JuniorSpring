/*
 * regression_.s
 *
 * Author: Joel Carlson
 * Date: 02/13/2023
 *
 */

 // Section .crt0 is always placed from address 0
	.section .crt0, "ax"

_start:
	.global _start

 /*
 	Immediate (i-type) ALU operations
    
    Suggested tests or unique features of ITYPE instructions
    - there are 6 immediate arithematic operations
        - addi
        - slti
        - sltiu
        - xori
        - ori
        - andi
    - immediates are positive and negative
    - there are operations that use unsigned numbers
    - there are comparison operations that need to test the 3 options
         - less than
         - equal
         - greater than
    - there must be 3 nops between instructions in that this test will be
    used before data hazard detection and forwarding has been implemented

    Will use this test for Assignment 5: Data Path and Instruction Decode
 */
    addi x2, x0, -1


    // rest of the tests
 	addi x2, x0, 2				// load 2 into register x2
 	nop
 	nop
 	nop
 	addi x2, x2, (-1 & 0xfff)	// add -1 to x2, x2 = 1
 	nop					// x2 = 2
 	nop
 	nop
 	slti x3, x2, 2				// compare 1 to 2, less than so x3 = 1
 	nop					// x2 = 1
 	nop
 	nop
 	slti x3, x2, 1				// compare 1 to 1, not less than, so x3 = 0
 	nop					// x3 = 1
 	nop
 	nop
    slti x3, x2, 0              //compare 1 to 0, is greater than so x3 = 0
    nop					//50: x3 = 0
 	nop
 	nop
    sltiu x3, x2, -2            //compare 1 to 2, it is less than so x3 = 1
 	nop                 //53:  x3 = 0
 	nop					
    nop					
    sltiu x3, x2, -1            //compare 1 to (-1) which will be  1, it is eequal than so x3 = 0
    nop				    //57: x3 = 1
    nop				
    nop				
    sltiu x3, x2, 0	            //compare 1 to 0, it is greater than so x3 = 0
 	nop                 //61: x3 = 0
 	nop
    nop
    xori x3, x2, 0           // XOR x2=1 and the imm = 0 thus x3 = 1 as 1 XOR 0 = 1
    nop                 //65: x3 = 0
 	nop
 	nop
    ori x3, x2, 0           //Or with x2 = 1 and imm = 0, x3 will = 1 as 1 OR 0 = 1 
    nop                 //69: x3 = 1
 	nop
 	nop
    andi x3, x2, 0          // x2 AND 0 with x2 = 1 and 0 x3 = 0 
    nop                //73: x3 = 1
 	nop
 	nop

 	nop
 	nop                //77: x 3 = 0;
 	nop
 	nop
  	halt
 	nop
 	nop
 	nop
 	nop
 /*
 /*
 	RTYPE ALU operations

    Suggested tests or unique features of RTYPE instructions
    - there are 10 rtype arithematic operations
        - add
        - sub
        - sll
        - slt
        - sltu
        - xor
        - srl
        - sra
        - or
        - and
    - two register operands that can either be positive and negative
    - there are operations that use unsigned numbers
    - there are comparison operations that need to test the 3 options
         - less than
         - equal
         - greater than
    - there must be 3 nops between instructions in that this test will be
    used before data hazard detection and forwarding has been implemented
    
    - recommend that before testing the operations, use addi opertion to load
    different test values in registers that will be used by the different
    RTYPE instruction tests
         - example, addi x1, x0, 1   // loads a 1 in register x1
                    addi x2, x0, -1  // loads a -1 (0xffffffff) into register x2

    Will use this test for Assignment 5: Data Path and Instruction Decode
 */
    addi x1, x0, 1          //loading 1 into x1  
    nop 
	nop
	nop         
    add x2, x1, x1         // adding 2*x1 to x2 resulting in 2 in x2
    nop             //128 x1 = 1
	nop
	nop
    addi x3, x0, -3         //loading -1 into x2, this will give the negative addition
    nop             //132: x2 = x1 +x1 = 2
	nop
	nop
    add x4, x1, x3          //adding x1 + x2 to x3 = 1 + -1 = 0
    nop             //136: x3  = -3
	nop
	nop 
    //Addi with ± done
    //subtracting + minus +, + minus -, - minus +, and - minus -
    sub x5, x2, x1          //subtracting: x2-x1 = 2 - 1 = 1
    nop             //140: x4 = -2
    nop
    nop
    sub x4, x2, x3          //substracting x2-x3 = 2 - (-3) = 5
    nop             //146: x5 = 1
    nop
    nop
    sub x5, x3, x2          //subtracting x3-x2 = -3-2 = -5
    nop             //150: x4 = 5
    nop
    nop
    sub x4, x3,x3           //subtracting x3-x3 = -3 - (-3) = 0
    nop             //154: x5= -5
    nop
    nop
    sub x5, x0, x3          //subtracting x0-x3, 0-(-3) =3
    nop             //158: x4 = 0
    nop
    nop
    sub x4, x3, x0          //subtracting x3, x0  = -3-0= -3
    nop             //162: x5 = 3
    nop
    nop
    sub x5, x1, x0          //subtracting x1, x0 = 1-0 = 1
    nop             //166: x4 = -3
    nop
    nop
    //sub done
    //sll done on positive, neg, and 0 with pos, neg, and 0
    //pos by pos, neg, 0
    sll x4, x1, x2          //shifting 1 left by 2 = 100
    nop             //170: x5 = 1
    nop
    nop
    sll x5, x2, x3          //shifting x2 by x3 which is binary 10 by -3 so that would be 0x400...0
    nop             //177; x4 = 0x4
    nop
    nop
    sll x4, x1, x0          //shifting x1 by x0 is 0001 by 0 is 0001 in binary
    nop             //181: x5= 0x40000000
    nop
    nop
    //0 by pos, neg 0
    sll x5, x0, x1          //shifting x0 by x1 will still be 0 
    nop             //185: x4 = 1
    nop
    nop
    sll x4, x0, x3          //shifting x0 by x3 will still be 0 
    nop             //190; x5 = 0
    nop
    nop
    sll x5, x0, x0          //shifting x0 by x0 will still be 0 
    nop             //190; x4 = 0
    nop
    nop
    //negative by pos, neg, 0
    sll x4, x3, x2          //shfiting x3 by x2, = 0xD....FD by 0x10 will be 0xF....F4 
    nop              //198; x5 = 0
    nop
    nop
    sll x5, x3, x3          //shifting x3 by -3
    nop             //203: x4 = 0xfffffff4
    nop
    nop
    sll x4, x3, x0          //shifting x3 by x0 will stil leave x3
    nop             //207: x5 = a0000000
	nop
	nop
    // done with sll
    //doing slt which will be almost the same as slti
    //pos compared to all >< =
    slt x5, x2, x1          //copmaring if x2<x1 since not x5 = 0
    nop             //211 : x4 = x3
	nop
	nop
    slt x4, x2, x2          //comparing if x2<x2 since they are = x4 = 0
    nop             //219: x5 = 0
	nop 
	nop
    slt x5, x1, x2          //coompare x1<x2 since yes x5 = 1
    nop             //222: x4 = 0
	nop
	nop
    slt x6, x1, x3          //compare x1< x3, 1 is greater than -2 so 0
    nop             //226: x5 = 1
    nop
    nop
    //comparing 0 to all
    slt x4, x0, x1          //0 is less than 1 so 1
    nop             //230: x6 = 0
	nop
	nop
    slt x5, x0, x0          //0 is equal to 0 so 0
    nop             //231: x4 = 1 
	nop
	nop
    slt x4, x0, x3          // 0 is greater than -3 so 0
    nop             //235: x5 = 0
	nop
	nop
    //comparing neg to all
    slt x5, x3, x1          //-3 is less than pos 1 so 1
    nop             //239: x4= 0
	nop
	nop
    slt x4, x3, x0          //-3 is less than 0 so 1
    nop
	nop
	nop
    slt x5, x3, x3          //-3 is eql to -3, 0
    nop
	nop
	nop
    addi x6, x0, -6         //making x6 = -6
    nop
	nop
	nop
    slt x4, x3, x6          // -3 is greater than -6, 0
    nop
	nop
	nop
    //pos, neg, and 0 comparisons done
    //doing sltu 
    //using the same as above but will change a few values to match
    sltu x5, x2, x1          //copmaring if x2<x1 since not x5 = 0
    nop             //264: x4 = 0
	nop
	nop
    sltu x4, x2, x2          //comparing if x2<x2 since they are = x4 = 0
    nop             //271: x5 = 0
	nop 
	nop
    sltu x5, x1, x2          //coompare x1<x2 since yes x5 = 1
    nop             //275: x4 = 0
	nop
	nop
    # sltu x7 
    //comparing 0 to all
    sltu x4, x0, x1          //0 is less than 1 so 1
    nop             //279: x5 = 1 
	nop
	nop
    sltu x5, x0, x0          //0 is equal to 0 so 0
    nop             //231: x4 = 1 
	nop
	nop
    sltu x4, x0, x3          // 0 is greater than -3 so 0
    nop             //235: x5 = 0
	nop
	nop
    //comparing neg to all
    sltu x5, x3, x1          //-3 is less than pos 1 so 1
    nop             //239: x4= 0
	nop
	nop
    sltu x4, x3, x0          //-3 is less than 0 so 1
    nop
	nop
	nop
    sltu x5, x3, x3          //-3 is eql to -3, 0
    nop
	nop
	nop
    sltu x4, x3, x6          // -3 is greater than -6, 0
    nop
	nop
	nop
    //xor 
    xor x5, x1, x1          //xor of 1 and 1 = 0
    nop
	nop
	nop
    xor x5, x1, x0          //xor of 1 and 0 = 1
    nop
	nop
	nop
    xor x4, x0, x1           //xor of 0 and 1 = 1
    nop
    nop
    nop
    xor x5, x0, x0          //xor of 0 and 0 = 0
    nop
    nop
    nop

    //srl using the same as sll
    srl x4, x1, x2          //shifting 1 right by 2 = 100
    nop          
    nop
    nop
    srl x5, x2, x3          //shifting x2 by x3 which is binary 10 
    nop            
    nop
    nop
    srl x4, x1, x0          //shifting x1 by x0 is 0001 by 0 is 0001 in binary
    nop             
    nop
    nop
    //0 by pos, neg 0
    srl x5, x0, x1          //shifting x0 by x1 will still be 0 
    nop             
    nop
    nop
    srl x4, x0, x3          //shifting x0 by x3 will still be 0 
    nop            
    nop
    nop
    srl x5, x0, x0          //shifting x0 by x0 will still be 0 
    nop             
    nop
    nop
    //negative by pos, neg, 0
    srl x4, x3, x2          //shfiting x3 by x2, = 0xD....FD by 0x10 will be 0xF....F4 
    nop              
    nop
    nop
    srl x5, x3, x3          //shifting x3 by -3
    nop             
    nop
    nop
    srl x4, x3, x0          //shifting x3 by x0 will stil leave x3
    nop             
	nop
	nop

    //shifting right arithmetic
    sra x4, x1, x2          //shifting 1 right by 2 = 100
    nop          
    nop
    nop
    sra x5, x2, x1          //shifting x2 by x1 which is binary 10 
    nop            
    nop
    nop
    sra x4, x1, x0          //shifting x1 by x0 is 0001 by 0 is 0001 in binary
    nop             
    nop
    nop
    //0 by pos, neg 0
    sra x5, x0, x1          //shifting x0 by x1 will still be 0 
    nop             
    nop
    nop
    sra x4, x0, x3          //shifting x0 by x3 will still be 0 
    nop            
    nop
    nop
    sra x5, x0, x0          //shifting x0 by x0 will still be 0 
    nop             
    nop
    nop
    //negative by pos, neg, 0
    sra x4, x3, x2          //shfiting x3 by x2, = 0xD....FD by 0x10 will be 0xF....F4 
    nop              
    nop
    nop
    sra x5, x3, x3          //shifting x3 by -3
    nop             
    nop
    nop
    sra x4, x3, x0          //shifting x3 by x0 will stil leave x3
    nop             
	nop
	nop

    //or operations
    or x5, x1, x1          //or of 1 and 1 = 0
    nop
	nop
	nop
    or x4, x1, x0          //or of 1 and 0 = 1
    nop
	nop
	nop
    or x5, x0, x1           //or of 0 and 1 = 1
    nop
    nop
    nop
    or x4, x0, x0           //or of 0 and 0 = 0
    nop
    nop
    nop

    //and operations
    and x5, x1, x1          //and of 1 and 1 = 1
    nop
	nop
	nop
    and x4, x1, x0          //and of 1 and 0 = 0
    nop
	nop
	nop
    and x5, x0, x1           //and of 0 and 1 = 0
    nop
    nop
    and x4, x0, x0           //and of 0 and 0 = 0
    nop
    nop
    nop


	nop
	nop
	nop
	nop
 	halt
 	nop
 	nop
 	nop 
 	nop
 /*
 	Shift Immediate ALU operations
      
    Suggested tests or unique features of Shift Immediate instructions
    - there are 3 shift immediate operations  
        - slli
        - srli
        - srai
    - logical shifts do not maintain the sign of the operand (the number 
    of bits shifted are padded with 0s) while an arithematic shifts maintain 
    the sign (the number of bits shifted are padded with the sign bit value)
    - both negative and positive values must be tested for each operation

    Will use this test for Assignment 5: Data Path and Instruction Decode
 */
    //sll done on positive, neg, and 0 with pos, neg, and 0
    //pos by pos, neg, 0
    slli x4, x1, 2          //shifting 1 left by 2 = 100
    nop             //170: x5 = 1
    nop
    nop
    slli x5, x2, -3          //shifting x2 by -3 which is binary 10 by -3 so that would be 0x400...0
    nop             //177; x4 = 0x4
    nop
    nop
    slli x4, x1, 0          //shifting x1 by 0 is 0001 by 0 is 0001 in binary
    nop             //181: x5= 0x40000000
    nop
    nop
    //0 by pos, neg 0
    slli x5, x0, 1          //shifting x0 by 1 will still be 0 
    nop             //185: x4 = 1
    nop
    nop
    slli x4, x0, -3          //shifting x0 by -3 will still be 0 
    nop             //190; x5 = 0
    nop
    nop
    slli x5, x0, 0          //shifting x0 by 0 will still be 0 
    nop             //190; x4 = 0
    nop
    nop
    //negative by pos, neg, 0
    slli x4, x3, 2          //shfiting x3 by 2, = 0xD....FD by 0x10 will be 0xF....F4 
    nop              //198; x5 = 0
    nop
    nop
    slli x5, x3, -3          //shifting x3 by -3
    nop             //203: x4 = 0xfffffff4
    nop
    nop
    slli x4, x3, 0          //shifting x3 by 0 will stil leave x3
    nop             //207: x5 = a0000000
	nop
	nop
    // done with slli
      //srli using the same as sll
    srli x4, x1, 2          //shifting 1 right by 2 = 100
    nop          
    nop
    nop
    srli x5, x2, -1          //shifting x2 by -1 which is binary 10 
    nop            
    nop
    nop
    srli x4, x1, 0          //shifting x1 by 0 is 0001 by 0 is 0001 in binary
    nop             
    nop
    nop
    //0 by pos, neg 0
    srli x5, x0, 1          //shifting x0 by 1 will still be 0 
    nop             
    nop
    nop
    srli x4, x0, -3          //shifting x0 by x3 will still be 0 
    nop            
    nop
    nop
    srli x5, x0, 0          //shifting x0 by x0 will still be 0 
    nop             
    nop
    nop
    //negative by pos, neg, 0
    srli x4, x3, 2          //shfiting x3 by x2, = 0xD....FD by 0x10 will be 0xF....F4 
    nop              
    nop
    nop
    srli x5, x3, -3          //shifting x3 by -3
    nop             
    nop
    nop
    srli x4, x3, 0          //shifting x3 by x0 will stil leave x3
    nop             
	nop
	nop

    //shifting right arithmetic
    srai x4, x1, 2          //shifting 1 right by 2 = 100
    nop          
    nop
    nop
    srai x5, x2, 1          //shifting x2 by x1 which is binary 10 
    nop            
    nop
    nop
    srai x4, x1, 0          //shifting x1 by x0 is 0001 by 0 is 0001 in binary
    nop             
    nop
    nop
    srai x7, x1, -3         //shifting x1 by -1
    nop
    nop
    nop
    //0 by pos, neg 0
    srai x5, x0, 1          //shifting x0 by x1 will still be 0 
    nop             
    nop
    nop
    srai x4, x0, 3          //shifting x0 by x3 will still be 0 
    nop            
    nop
    nop
    srai x5, x0, 0          //shifting x0 by x0 will still be 0 
    nop             
    nop
    nop
    //negative by pos, neg, 0
    srai x4, x3, 2          //shfiting x3 by x2, = 0xD....FD by 0x10 will be 0xF....F4 
    nop              
    nop
    nop
    srai x5, x3, 3          //shifting x3 by -3
    nop             
    nop
    nop
    srai x4, x3, 0          //shifting x3 by x0 will stil leave x3
    nop             
	nop
	nop


	nop
	nop
	nop
	nop
 	halt
 	nop
 	nop
 	nop
 	nop





 /*
 	Data hazard detection and forwarding test sequences
    
    - Data forward removes the requirement of putting nops between each 
    instruction.  Starting at this test, there should not be a requirement
    to place nops between instructions
    - Data forwarding must be tested for each register operand, rs1 and rs1,
    for each instruction in the pipeline after the ID (Instruction Decode) stage,
    EX, MEM, and WB.  To test this combination, a minimum of 6 tests are required.

    Will use this test for Assignment 6: Data Hazard and Data Forwarding
 */
 	addi x2, x0, 1				// load x2 register with 1
 	addi x3, x0, 2				// load x3 register with 2
 	addi x4, x0, 3				// load x4 register with 3
 	addi x5, x0, -1				// load x5 register with -1
    
    add x3, x4, x5              // adding -1 plus 3 into x3 x3 = 2
    add x2, x3, x4              //adding  3 + 2, x2 = 5 
    add x5, x2, x3              //x5 = 8 = 5+3
    add x2, x5, x2              //x2 = 8 + 5 =13
    add x4, x2, x5              //x4 = 13 + 8 = 21
    add x2, x3, x4              //x2 = 2 + 21 = 23


	nop
	nop
	nop
	nop
  	halt
 	nop
 	nop
 	nop
 	nop
 /*
 	Branch (b-type) operations

    Suggested tests or unique features of BTYPE (branch) instructions
    - there are 6 BTYPE (branch) operations  
        - beq
        - bne
        - blt
        - bge
        - bltu
        - bgeu
    - there are operations that use unsigned values (bltu & bgeu)
    - both negative and positive operands should be tested
    - branches are comparison operations that need to test the 3 options
         - less than
         - equal
         - greater than
    - a test must ensure that the branch goes to the proper address
    - branch immediates are signed which indicates a branch should test
    both positive (forward) branches and negative (backword) branches
    - You can add new BRANCH labels for testing.  Highly recommended.
    - if a branch test fails, it should branch to the BRANCH_FAIL label to 
    help indicate which branch test fails

    Will use this test for Assignment 7: Branch and Jumps

 */
# 	nop
# 	nop
# 	nop
# 	nop
# 	beq x0, x0, PASS
#     nop
#     nop
#     nop
# BRANCH_FAIL:
# 	nop
# 	nop
# 	nop
# 	nop
	halt		// Branch test has failed, time to debug
# 	nop
# 	nop
# 	nop
# 	nop
# PASS:
# 	nop
# 	nop
# 	nop
  	halt
#  	nop
#  	nop
#  	nop
#  	nop
 /*
 	jump instruction operations

    Suggested tests or unique features of Jump instructions
    - there are 2 jump operations  
        - jalr
        - jal
    - jump operations change the flow of a program without the need to 
    pass a conditional statement such as less than
    - a test must ensure that the jump goes to the proper address
    - jump immediates are signed which indicates a jump should test
    both positive (forward) jumps and negative (backword) jumps
    - jump instructions store the return address, pc + 4, in the rd,
    destination register, which must be tested/validated
    - You can add new JUMP labels for testing.  Highly recommended.
    - if a jump test fails, it should branch to the JUMP_FAIL label to 
    help indicate which jump test fails

    Will use this test for Assignment 7: Branch and Jumps
 */

jal x0, Test_Jump



Pre_Jump:
    nop
    nop
    nop
    addi x4, x4, -2000
    jalr x0, 0(x2)
    jal x0, JUMP_FAIL

Test_Jump:
    nop
    nop
    nop
    addi x4, x0, 1000           //intializing x4 to have 1000
    nop
    nop
    nop
    jal x1, Jump_to             //jumps donw to Jump_to
    nop
    nop
    nop
    jal x2, Pre_Jump
    nop
    nop
    nop
   jal x0, JUMP_PASS
   

Jump_to:
    nop
    nop
    nop
    addi x7, x0, 2100
    nop
    nop
    nop
    jalr x0, 0(x1)
    nop
    nop
    nop


    nop
	nop
	nop
	nop
	nop
  	halt
    nop
    nop
    nop
    jal x0, JUMP_PASS
 	nop
JUMP_FAIL:
 	nop
 	nop
 	nop
    halt
    nop
    nop
JUMP_PASS:
  	nop
    nop
	nop
	nop
  	halt
    nop
    nop
    nop
    nop

 /*
 	Store (s-type)  operations

    Suggested tests or unique features of store instructions
    - there are 3 store operations  
        - sw
        - sh
        - sb
    - store immediates are signed which indicates a store should test
    both a positive and negative immediate
    - a byte can be placed into one of the four byte locations of a 
    data word indicating a minimum of 4 sb (store byte) tests
    - a half-word can be placed into two of the four half-word locations
    of a data word indicating a minimum of 2 sh (store half-word) tests
    - you  will be using the memory view of the debugger to validate 
    the store operations
    
    Will use this test for Assignment 8: Stores and Loads
 */
 	// Loading test data into registers for Store / Load tests
    lui x20, 0x210
    srli x20, x20, 12
    lui x30, 0x76543
    or x20, x20, x30                        // x20 = 76543210
    lui x21, 0xdef
    srli x21, x21, 12
    lui x30, 0x89abc
    or x21, x21, x30                        // x21 = 0x89abcdef

 	// Load register x10 with base DATA memory location
 	lui x10, (DATA & 0xfff)		            // Use linker to obtain lower 12-bits
 	srli x10, x10, 12				        // Move the lower 12-bits to locations 11..0
    lui x30, ((DATA & 0xfffff000) >> 12)    // Move the upper DATA address bits into upper 20-bits
 	or x10, x10, x30           	        // x10 = DATA address
 	// Load register x11 with base DATA_MINUs location
 	lui x11, (DATA_MINUS & 0xfff)		    // Use linker to obtain lower 12-bits
 	srli x11, x11, 12				        // Move the lower 12-bits to locations 11..0
    lui x30, ((DATA_MINUS & 0xfffff000) >> 12)    // Move the upper DATA address bits into upper 20-bits
 	or x11, x11, x30           	        // x11 = DATA_MINUS address
 	// start of Store Instruction test

    //first, second, and the one previous 
    sw x20, 0(x10)          //each of these is storing x20 at the memory location of x10 with 0 then 4
    sw x20, 4(x10)
    sw x20, -4(x10)          // and then -4 the opposite direction of 

    sh x21, 0(x11)          //storing a half worrd at the register plus 2 half words in either direction to jsut look at everything
    sh x21, 2(x11)
    sh x21, 4(x11)
    sh x21, 6(x11)
    sh x21, -2(x11)
    sh x21, -4(x11)
    sh x21, -6(x11)         //offset of -6 to 6


    sb x20, 12(x10) 
    sb x20, 13(x10)
    sb x20, 14(x10)
    sb x20, 15(x10)
    sb x20, 16(x10)         //just doing sb wiht a large range 
    sb x20, -13(x10)
    sb x20, -14(x10)
    sb x20, -15(x10)
    sb x20, -16(x10)
    sb x20, -17(x10)


    
    
    

    
	nop
	nop
	nop
	nop
  	halt
  	nop
  	beq x0, x0, LOAD_TEST
  	nop
  	nop
 /*
 	Data Memory Space for regression test
 	- There are 8 NOP locations which are available
 		to be overwritten for test
 	- Accessing the first data location by 0 offset of x10 => 0(x10)
 	- Accessing the 1st byte in data space is 1 offset of x10 => 1(x10)
 	- Accessing the 2nd half-word in data space is 2 offset of x10 => 2(x10)
 	- Accessing the 2nd word in data space is 4 offset of x10 => 4(x10)
 */
DATA:
 	nop
 	nop
 	nop
 	nop
 	nop
 	nop
 	nop
 	nop
 DATA_MINUS:
    nop
    nop
    nop
    nop

 /*
 	Load operations
    
    Suggested tests or unique features of load instructions
    - there are 5 load operations  
        - lw
        - lh
        - lhu
        - lb
        - lbu
    - load immediates are signed which indicates a load should test
    both a positive and negative immediate
    - a byte can be read from one of the four byte locations of a 
    data word indicating a minimum of 4 lb (load byte) tests
    - a minimum of two unsigned load byte (lbu) to test a byte with 
    bit 7 a 0 and a 1 to validate proper signed bit extension
    - a half-word can be placed into two of the four half-word locations
    of a data word indicating a minimum of 2 lh (load half-word) tests
    - a minimum of two unsigned load byte (lhu) to test a half-word with 
    bit 15 a 0 and a 1 to validate proper signed bit extension
    - You can use the test values in memory from the store testss for the load 
    memory tests
         - You can use the DATA and DATA_MINUS labels that were used for stores
    - if you have branches working at this point, use a branch to validate 
    the proper load operation and upon failure, branch to LOAD_FAIL label to
    indicate a failed load operation to debug
    
    Will use this test for Assignment 8: Stores and Loads
 */

    lw x22, 0(x10)          //each of these is loading x20 at the memory location of x10 with 0 then 4
    lw x22, 4(x10)
    lw x22, -4(x10)          // and then -4 the opposite direction of 

    lh x23, 0(x11)          //loading a half worrd at the register plus 2 half words in either direction to jsut look at everything
    lh x23, 2(x11)
    lh x23, 4(x11)
    lh x23, 6(x11)
    lh x23, -2(x11)
    lh x23, -4(x11)
    lh x23, -6(x11)         //offset of -6 to 6

    lhu x25, 6(x10)         // in order to get the 15th bit it will be contained with in this result 
    //x25 will have the 1
    lhu x26, 6(x11)         //done a second time as the instructions requested
    //x26 will have the 0
    lb x24, 12(x10) 
    lb x24, 13(x10)
    lb x24, 14(x10)
    lb x24, 15(x10)
    lb x24, 16(x10)         //just doing lb wiht a large range 
    lb x24, -13(x10)
    lb x24, -14(x10)
    lb x24, -15(x10)
    lb x24, -16(x10)
    lb x24, -17(x10)

    lbu x27, 15(x10)        //these are done to look at the 15th bit of each word to see if ti si a 1 or a 0
    //x27 will have the 1
    lbu x28, 15(x11)
    //x26 wil have the 26




LOAD_TEST:
	nop
	nop
	nop
	nop
  	halt
 	nop
 	nop
 	nop
 	nop
 LOAD_FAIL:							// Using branch statements, if load does not
 	nop								// return result expected, branch to LOAD_FAIL label
 	nop
 	nop
 	halt
 	nop
 	nop
 	nop
 	nop


