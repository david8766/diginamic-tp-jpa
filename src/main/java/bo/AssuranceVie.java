package bo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_assuranceVie")
public class AssuranceVie extends Compte {

	private Double taux;
	private LocalDate dateFin;

	public AssuranceVie() {
		super();
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	@Override
	public String toString() {
		return "AssuranceVie [taux=" + taux + ", dateFin=" + dateFin + "]";
	}

}
