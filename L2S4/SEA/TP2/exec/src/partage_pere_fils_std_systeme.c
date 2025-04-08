#include <stdio.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

int main(){
    int outfd = open("fileout",O_RDWR+O_CREAT, 0600); // Xibitol: No permissions/No error checking ?
    int infd = open("fileout", O_RDWR); // Xibitol: Wrong file ?
    FILE*  outfile = fdopen(outfd,"w");

	setbuf(stdout, NULL); // Xibitol: Prevent printf to make late prints

    char buffer[4];
	buffer[3] = 0; // Xibitol: Better

    fprintf(outfile,"123");
    write(outfd,"456",3);
    read(infd, buffer, 3);

    printf("Before fork read '%s'\n", buffer);
    int p = fork();

    if (p > 0) {
        write(outfd,"p7p8",4);
        fprintf(outfile,"p9\n");
        read(infd, buffer, 3);
        printf("Parent after fork read '%s'\n", buffer);

    } else {
        write(outfd,"c7c8",4);
        fprintf(outfile,"c9\n");
        read(infd, buffer, 3);
        printf("Child after fork read '%s'\n", buffer);
    }
}
