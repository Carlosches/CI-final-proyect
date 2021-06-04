package co.edu.icesi.ci.tallerfinal.back.model;



import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

import co.edu.icesi.ci.tallerfinal.back.groups.AddPhycheckup;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PHYSICALCHECKUP database table.
 * 
 */
@Entity
@NamedQuery(name="Physicalcheckup.findAll", query="SELECT p FROM Physicalcheckup p")
public class Physicalcheckup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PHYSICALCHECKUP_PHYCHEID_GENERATOR", sequenceName="PHYSICALCHECKUP_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PHYSICALCHECKUP_PHYCHEID_GENERATOR")
	@Column(name="PHYCHE_ID")
	private long phycheId;

	@NotNull(message="se debe seleccionar una fecha", groups= AddPhycheckup.class)
	@Temporal(TemporalType.DATE)
	@Column(name="PHYCHE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent(message="La fecha debe ser en el futuro", groups=AddPhycheckup.class)
	private Date phycheDate;

	//bi-directional many-to-one association to CheckMeasur
	@OneToMany(mappedBy="physicalcheckup")
	private List<CheckMeasur> checkMeasurs;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@NotNull(message="se debe seleccionar una persona", groups=AddPhycheckup.class)
	@JoinColumn(name="PERS_PERS_ID")
	private Person person;

	//bi-directional many-to-one association to Visit
	@ManyToOne
	@NotNull(message="se debe seleccionar una visita", groups=AddPhycheckup.class)
	//@JoinColumn(name = "VISIT_VISIT_ID")
	private Visit visit;

	public Physicalcheckup() {
	}

	public long getPhycheId() {
		return this.phycheId;
	}

	public void setPhycheId(long phycheId) {
		this.phycheId = phycheId;
	}

	public Date getPhycheDate() {
		return this.phycheDate;
	}

	public void setPhycheDate(Date phycheDate) {
		this.phycheDate = phycheDate;
	}

	public List<CheckMeasur> getCheckMeasurs() {
		return this.checkMeasurs;
	}

	public void setCheckMeasurs(List<CheckMeasur> checkMeasurs) {
		this.checkMeasurs = checkMeasurs;
	}

	public CheckMeasur addCheckMeasur(CheckMeasur checkMeasur) {
		getCheckMeasurs().add(checkMeasur);
		checkMeasur.setPhysicalcheckup(this);

		return checkMeasur;
	}

	public CheckMeasur removeCheckMeasur(CheckMeasur checkMeasur) {
		getCheckMeasurs().remove(checkMeasur);
		checkMeasur.setPhysicalcheckup(null);

		return checkMeasur;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Visit getVisit() {
		return this.visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

}