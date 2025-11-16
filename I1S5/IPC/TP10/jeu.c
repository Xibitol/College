#include <stdio.h>
#include <stdlib.h>

#include "cartes.h"
#include "random.h"

#define MAX_PLAYERS       4

int main(int argc, char *argv[])
{
    init_rand("SEED");

    if (argc < 2) {
        printf("%s <nombre de cartes a donner>\n",
                argv[0]);
        return EXIT_FAILURE;
    }
    struct paquet mains[4] = {};
    struct paquet pioche = nouveau_paquet(SEPT);

    melange_paquet(&pioche);
    distribue_paquet(atoi(argv[1]), &pioche, 4, mains);

    for (int i = 0; i < MAX_PLAYERS; ++i)
        affiche_paquet(mains + i);
    affiche_paquet(&pioche);

    return EXIT_SUCCESS;
}