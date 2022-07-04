package bo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String numero;
	private Double solde;

	@OneToMany(mappedBy = "compte")
	private List<Operation> operations;

	@ManyToMany(mappedBy = "comptes")
	private List<Client> clients;

	// Constructeurs
	public Compte() {
		super();
	}

	public Compte(String numero, Double solde) {
		super();
		this.numero = numero;
		this.solde = solde;
	}

	public Compte(int id, String numero, Double solde, List<Operation> operations, List<Client> clients) {
		super();
		this.id = id;
		this.numero = numero;
		this.solde = solde;
		this.operations = operations;
		this.clients = clients;
	}

	// getters setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "Compte [id=" + id + ", numero=" + numero + ", solde=" + solde + "]";
	}

}
