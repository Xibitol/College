package dev.pimous.l2s4poa.tp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dev.pimous.javautils.AutoToString;

public class Banque extends AutoToString{

	private static final int COUT_TRANSFERT = 2;

	@SuppressWarnings("unused")
	private String nomBanque;
	private Collection<Client> clients = new ArrayList<>();
	// private Collection<CompteBancaire> comptes = new ArrayList<>();

	public Banque(String nomBanque){
		this.nomBanque = nomBanque;
	}

	// GETTERS
	public int getCoutTransfert(){ return COUT_TRANSFERT; }

	public Client rechercheClient(String nom){
		return clients.stream()
			.filter(c -> c.donneNom().equals(nom))
			.findFirst().orElse(null);
	}
	public List<CompteBancaire> rechercheCompte(String nomClient){
		Client c = rechercheClient(nomClient);

		return c != null ? c.donneComptes() : null;
	}
	public CompteBancaire rechercheCompte(int numeroCpte){
		return clients.stream()
			.flatMap(cl -> cl.donneComptes().stream())
			.filter(co -> co.donneNumero() == numeroCpte)
			.findFirst().orElse(null);
	}

	// SETTERS
	public boolean supprimerClient(String nomClient){
		return clients.removeIf(
			c -> c.donneNom().equals(nomClient) && c.donneComptes().size() == 0
		);
	}
	public boolean supprimerCompte(int numeroCpte){
		return clients.stream()
			.filter(c -> c.supprimeCompte(numeroCpte))
			.findAny().isPresent();
	}

	// FUNCTIONS
	public CompteBancaire creerCompteBancaire(
		double soldeInitial, String nomClient
	){
		Client c = rechercheClient(nomClient);
		CompteBancaire cb = null;

		if(c != null && c.donneComptes().size() < 3){
			cb = new CompteBancaire(soldeInitial, c);
			c.ajouteCompte(cb);
		}

		return cb;
	}
	public CompteBancaireRemunere creerCompteRemunere(
		double soldeInitial, double tauxInteret, String nomClient
	){
		Client c = rechercheClient(nomClient);
		CompteBancaireRemunere cb = null;

		if(c != null && c.donneComptes().size() < 3){
			cb = new CompteBancaireRemunere(soldeInitial, tauxInteret, c);
			c.ajouteCompte(cb);
		}

		return cb;
	}

	public Entreprise creerEntreprise(
		String nomEntreprise, int numSIRET, String adresse
	){
		Entreprise e = null;

		if(rechercheClient(nomEntreprise) == null){
			e = new Entreprise(nomEntreprise, numSIRET, adresse);
			clients.add(e);
		}

		return e;
	}
	public Particulier creerParticulier(
		String nomParticulier, String prenom, String adresse
	){
		Particulier p = null;

		if(rechercheClient(nomParticulier) == null){
			p = new Particulier(nomParticulier, prenom, adresse);
			clients.add(p);
		}

		return p;
	}

	public boolean transfertInterBancaire(
		int numeroCpteDebiteur, Banque banqueCrediteur,
		int numeroCpteCrediteur, double montant
	){
		CompteBancaire cpd = rechercheCompte(numeroCpteDebiteur);
		CompteBancaire cpc = banqueCrediteur.rechercheCompte(
			numeroCpteCrediteur
		);

		if(cpd != null && cpc != null){
			if(!this.equals(banqueCrediteur))
				cpd.debiter(getCoutTransfert());

			return cpd.transferer(cpc, montant);
		}

		return false;
	}
}
