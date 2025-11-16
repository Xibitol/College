#include "cartes.h"

#include <stdio.h>
#include <stdlib.h>

#include "random.h"

static void swap_card(struct paquet* p, int a, int b){
	struct carte t = p->cartes[a];
	p->cartes[a] = p->cartes[b];
	p->cartes[b] = t;
}

static struct carte make_card(enum valeur v, enum couleur c)
{
    struct carte cd = {v, c};
    return cd;
}

struct carte retirer(struct paquet *p)
{
    return p->cartes[--p->nombre];
}

void ajouter(struct paquet *p, struct carte c)
{
    if (p->nombre >= MAX_CARDS) return;
    p->cartes[p->nombre++] = c;
}

void melange_paquet(struct paquet *p)
{
    for (int i = 0; i < p->nombre - 1; i++) {
        int idx = RAND_BTW(i, p->nombre);
        swap_card(p, i, idx);
    }
}

struct paquet nouveau_paquet(enum valeur plus_basse)
{
    struct paquet p = {};
    for (enum couleur c = TREFLE; c <= PIQUE; c ++)
        for (enum valeur v = plus_basse; v <= AS; v ++)
            ajouter(&p, make_card(v, c));
    return p;
}

int distribue_paquet(int cartes, struct paquet *pioche,
        int joueurs, struct paquet paquets[])
{
    for (int i = 0; i < cartes; i ++)
        for (int j = 0; j < joueurs; j++) {
            if (pioche->nombre == 0)
                return i*j + j - 1;
            ajouter(paquets + j, retirer(pioche));
        }
    return cartes * joueurs;
}

const char *texte_couleur(enum couleur c)
{
	static const char *couleurs[] = { "T", "K", "C", "P" };
    return couleurs[c - TREFLE];
}

const char *texte_valeur(enum valeur v)
{
	static const char *valeurs[] = {
		"2", "3", "4", "5", "6", "7", "8",
		"9", "0", "V", "D", "R", "A",
	};
    return valeurs[v - DEUX];
}

void affiche_carte(const struct carte *c)
{
    printf("<%s%s>",
            texte_valeur(c->valeur),
            texte_couleur(c->couleur));
}

void affiche_paquet(const struct paquet *p)
{
    for (int i = 0; i < p->nombre; ++i)
        affiche_carte(&p->cartes[i]);
    printf("\n");
}