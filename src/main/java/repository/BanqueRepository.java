package repository;

import bo.Banque;
import jakarta.persistence.EntityManager;

public class BanqueRepository {

	public static Banque create(String nom) {
		Banque banqueToCreate = new Banque(nom);

		EntityManager em = PersistenceHelper.getEntityManager();
		PersistenceHelper.beginTx(em);
		em.persist(banqueToCreate);
		PersistenceHelper.commitTxAndClose(em);

		return banqueToCreate;
	}

	public static Banque findById(int id) {
		return PersistenceHelper.getEntityManager().find(Banque.class, id);
	}
}
