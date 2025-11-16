#pragma once

#define MAX_CARDS       52

enum couleur {
    TREFLE, CARREAU, COEUR, PIQUE
};
const char *texte_couleur(enum couleur c);

enum valeur {
    DEUX=2, TROIS, QUATRE, CINQ,
    SIX, SEPT, HUIT, NEUF, DIX,
    VALET, DAME, ROI, AS
};
const char *texte_valeur(enum valeur v);

struct carte {
  enum valeur valeur;
  enum couleur couleur;
};
extern void affiche_carte(const struct carte *c);

struct paquet {
    int nombre;
    struct carte cartes[MAX_CARDS];
};
extern struct paquet nouveau_paquet(enum valeur plus_basse);
extern void ajouter(struct paquet *p, struct carte c);
extern struct carte retirer(struct paquet *p);
extern void melange_paquet(struct paquet *p);
extern int distribue_paquet(int cartes, struct paquet *pioche,
        int joueurs, struct paquet paquets[]);
extern void affiche_paquet(const struct paquet *p);