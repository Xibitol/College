d = 2000

r = None
while not r or not 0 < r < 200:
    r = int(input("Saisissez le montant du remboursement mensuel (en euros) : "))
    # 1. Une amélioration intéressante serait de capturer toute erreur concernant la conversion en nombre flottant.
    # 2. De plus, l'énoncé demande une valeur comprise entre 0 et 200 pour r sans présiser son aspect entier,
    #    mais l'exemple force à utiliser les entiers (Afin que les resultats correspondent). J'aurais donc autorisé les nombres décimaux.

print("Les 6 premiers mois de remboursement :")
for m in range(1, 7):
    print(f"Mois {m} - Montant restant : {d - m*r}")

print(f"Il faudra {d//r + 1} mois pour rembourser le crédit de {d} euros avec des remboursements mensuels de {r} euros.")