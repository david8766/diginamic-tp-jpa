package repository;

import java.time.LocalDate;
import java.util.List;

import bo.Adresse;
import bo.Banque;
import bo.Client;
import bo.Compte;
import jakarta.persistence.EntityManager;

public class ClientRepository {

	public static Client create(String nom, String prenom, LocalDate dateNaissance, Adresse adresse, Banque banque) {
		Client clientToCreate = new Client(nom, prenom, dateNaissance, adresse, banque);

		EntityManager em = PersistenceHelper.getEntityManager();
		PersistenceHelper.beginTx(em);
		em.persist(clientToCreate);
		PersistenceHelper.commitTxAndClose(em);

		return clientToCreate;
	}

	public static Client findById(int id) {
		return PersistenceHelper.getEntityManager().find(Client.class, id);
	}

	public static Client createWithCompte(String nom, String prenom, LocalDate dateNaissance, Adresse adresse,
			List<Compte> comptes, Banque banque) {
		Client clientToCreate = new Client(nom, prenom, dateNaissance, adresse, comptes, banque);

		EntityManager em = PersistenceHelper.getEntityManager();
		PersistenceHelper.beginTx(em);
		em.persist(clientToCreate);
		PersistenceHelper.commitTxAndClose(em);

		return clientToCreate;
	}
}
