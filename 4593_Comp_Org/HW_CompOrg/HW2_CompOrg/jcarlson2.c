#include "stdio.h"
#include "string.h"

/*
 *  c abstraction assignment
 */

int main() {  
    unsigned int letter = 'C';
    int result = 0;
    int i =0;
    for( i=0; i<letter; i++){
        result = result +1 + letter;  
    }
    printf("Reuslt= %d\n", result);  
    return result;
}

//took about 10 minutes to write pretty eay to debug just quite a few steps to get to it. Not difficult though