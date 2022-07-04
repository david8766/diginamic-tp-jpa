package repository;

import bo.Adresse;
import jakarta.persistence.EntityManager;

public class AdresseRepository {

	public static Adresse create(int numero, String rue, int codePostal, String ville) {
		Adresse adresseToCreate = new Adresse(numero, rue, codePostal, ville);

		EntityManager em = PersistenceHelper.getEntityManager();
		PersistenceHelper.beginTx(em);
		em.persist(adresseToCreate);
		PersistenceHelper.commitTxAndClose(em);

		return adresseToCreate;
	}

	public static Adresse findById(int id) {
		return PersistenceHelper.getEntityManager().find(Adresse.class, id);
	}

}
