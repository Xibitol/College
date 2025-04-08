/**
 * @file sync.c
 *
 * @author Xibitol <xibitol@pimous.dev>
 * @date 2025
 * @copyright GNU Lesser General Public License v3.0
 */

#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

#define EXEC_NAME "sync"

static char* jobs[] = {
	"uname",
	"ls -lA",
	"date -R"
};

static char** strsplit(char* str, const char* delim, const int count){
	char** tokens = (char**) malloc(count*__SIZEOF_POINTER__);
	char* treatedStr = (char*) malloc(strlen(str) + 1);
	strcpy(treatedStr, str);

	int i = 0;
	char* buffer = NULL;
	buffer = strtok(treatedStr, delim);
	for(; i < count && buffer != NULL; i++){
		tokens[i] = (char*) malloc(strlen(buffer) + 1);
		strcpy(tokens[i], buffer);

		buffer = strtok(NULL, delim);
	}

	tokens[i++] = NULL;
	tokens = (char **) realloc(tokens, i*__SIZEOF_POINTER__);

	free(treatedStr);
	return tokens;
}
static void free_strsplit(char** tokens){
	int i = 0;
	char* token;
	while((token = tokens[i]) != NULL){
		free(token);
		i++;
	}

	free(tokens);
}

int main(void){
	int exitCode = EXIT_SUCCESS;

	setbuf(stdout, NULL);
	
	for(int i = 0; i < (int) sizeof jobs/__SIZEOF_POINTER__; i++){
		char** cmd = strsplit(jobs[i], " ", 4);

		int fpid = fork();
		switch(fpid){
			case -1:
				perror(EXEC_NAME), exitCode =  EXIT_FAILURE;

				return exitCode;
			case 0:;
				int err = execvp(cmd[0], cmd);
				if(err == -1)
					perror(EXEC_NAME), exitCode = EXIT_FAILURE;

				return exitCode;
			default:;
				int status;
				pid_t pid = waitpid(fpid, &status, 0);

				if(pid == -1)
					perror(EXEC_NAME), exitCode = EXIT_FAILURE;
		}

		free_strsplit(cmd);

		if(exitCode != EXIT_SUCCESS) break;
	}

	return exitCode;
}