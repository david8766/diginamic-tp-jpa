import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bo.Adresse;
import bo.AssuranceVie;
import bo.Banque;
import bo.Client;
import jakarta.persistence.EntityManager;
import bo.Compte;
import bo.LivretA;
import bo.Operation;
import bo.Virement;
import repository.AdresseRepository;
import repository.BanqueRepository;
import repository.ClientRepository;
import repository.PersistenceHelper;

public class ConnectionBDD {

	public static void main(String[] args) {

		System.out.println("Création des informations en BDD");
		
		// Création via les repositories

		// Création des adresses
		AdresseRepository.create(22, "rue des lilas", 75000, "Paris");
		Adresse adresse1 = AdresseRepository.findById(1);
		AdresseRepository.create(25, "rue des lilas", 75000, "Paris");
		Adresse adresse2 = AdresseRepository.findById(2);
		AdresseRepository.create(13, "rue des églantiers", 75000, "Paris");
		Adresse adresse3 = AdresseRepository.findById(3);

		// Création de la Banque
		BanqueRepository.create("BNP");
		Banque banque1 = BanqueRepository.findById(1);

		// Création des clients
		LocalDate localDate = LocalDate.now();
		ClientRepository.create("Dupond", "Jean", localDate, adresse1, banque1);
		Client client1 = ClientRepository.findById(1);

		LocalDate localDate2 = LocalDate.now();
		ClientRepository.create("Dupont", "Maurice", localDate2, adresse2, banque1);
		Client client2 = ClientRepository.findById(2);

		LocalDate localDate3 = LocalDate.now();
		ClientRepository.create("Martin", "Philip", localDate3, adresse3, banque1);
		Client client3 = ClientRepository.findById(3);

		// Création des comptes
		EntityManager em = PersistenceHelper.getEntityManager();
		PersistenceHelper.beginTx(em);

		Compte compte1 = new Compte();
		compte1.setNumero("1");
		compte1.setSolde(500D);

		Compte compte2 = new Compte();
		compte2.setNumero("2");
		compte2.setSolde(500D);

		LivretA compte3 = new LivretA();
		compte3.setNumero("3");
		compte3.setSolde(400D);
		compte3.setTaux(4D);

		AssuranceVie compte4 = new AssuranceVie();
		compte4.setNumero("4");
		compte4.setSolde(800D);
		compte4.setTaux(4D);
		compte4.setDateFin(localDate3);

		em.persist(compte1);
		em.persist(compte2);
		em.persist(compte3);
		em.persist(compte4);

		List<Compte> c1Compte1 = new ArrayList<Compte>();
		c1Compte1.add(compte1);
		client1.setComptes(c1Compte1);

		List<Compte> c2Compte1 = new ArrayList<Compte>();
		c2Compte1.add(compte1);
		client2.setComptes(c2Compte1);

		em.merge(client1);
		em.merge(client2);

		List<Compte> c3Comptes = new ArrayList<Compte>();
		client3.setComptes(c3Comptes);
		client3.getComptes().add(compte3);
		client3.getComptes().add(compte4);

		em.merge(client3);

		// Ajouts des opérations
		Operation op1 = new Operation();
		op1.setDate(LocalDateTime.now());
		op1.setMontant(200D);
		op1.setMotif("operation1");
		op1.setCompte(compte3);

		Operation op2 = new Operation();
		op2.setDate(LocalDateTime.now());
		op2.setMontant(300D);
		op2.setMotif("operation2");
		op2.setCompte(compte3);

		Virement v1 = new Virement();
		v1.setDate(LocalDateTime.now());
		v1.setMontant(100D);
		v1.setMotif("virement1");
		v1.setBeneficiaire("Le client");
		v1.setCompte(compte4);

		Virement v2 = new Virement();
		v2.setDate(LocalDateTime.now());
		v2.setMontant(200D);
		v2.setMotif("virement2");
		v2.setBeneficiaire("Le client");
		v2.setCompte(compte4);

		em.persist(op1);
		em.persist(op2);
		em.persist(v1);
		em.persist(v2);

		PersistenceHelper.commitTxAndClose(em);

		// --------------------------------------------------------------------------------------------------------------------------

		// AFFICHAGE DES INFORMATIONS
		EntityManager em2 = PersistenceHelper.getEntityManager();
		PersistenceHelper.beginTx(em2);

		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.println("Affichage du compte associé à 2 clients");
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		Compte cAafficher = em2.find(Compte.class, 1);
		System.out.println(cAafficher.toString());
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.println("Affichage des 2 clients associés à ce compte");
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		List<Client> compte1Clients = cAafficher.getClients();
		for (Client client : compte1Clients) {
			System.out.println(client.toString());
		}
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.println("Affichage du client associé à 2 comptes");
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		Client clientAafficher = ClientRepository.findById(3);
		System.out.println(clientAafficher.toString());
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.println("Affichage des 2 comptes associés à ce client");
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		List<Compte> comptesDeCeClient = clientAafficher.getComptes();
		for (Compte compte : comptesDeCeClient) {
			System.out.println(compte.toString());
		}
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.println("Affichage des opérations associées d'un compte");
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		Compte cAafficher2 = em2.find(Compte.class, 3);
		System.out.println(cAafficher2.toString());
		List<Operation> operations = cAafficher2.getOperations();
		for (Operation operation : operations) {
			System.out.println(operation.toString());
		}

		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.println("Affichage des virements associés d'un compte");
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		Compte cAafficher3 = em2.find(Compte.class, 4);
		System.out.println(cAafficher3.toString());
		List<Operation> operations2 = cAafficher3.getOperations();
		for (Operation operation : operations2) {
			System.out.println(operation.toString());
		}

		PersistenceHelper.commitTxAndClose(em2);

	}

}
