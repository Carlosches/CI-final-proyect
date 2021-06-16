
package co.edu.icesi.ci.tallerfinal.back.model;



import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.*;

import co.edu.icesi.ci.tallerfinal.back.groups.AddVisit;
import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * The persistent class for the VISIT database table.
 * 
 */
@Entity
@NamedQuery(name="Visit.findAll", query="SELECT v FROM Visit v")
public class Visit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VISIT_VISITID_GENERATOR", sequenceName="VISIT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VISIT_VISITID_GENERATOR")
	@Column(name="VISIT_ID")
	private long visitId;

	@Size(min =5, message="El tamaño debe ser mayor a 5",groups = AddVisit.class)
	@Column(name="VISIT_DETAIL")
	private String visitDetail;
	
	@NotNull(message="La fecha de ingreso es obligatoria", groups=AddVisit.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="VISIT_ENTRANCEDATE")
	@FutureOrPresent(message="La fecha de ingreso debe ser en el futuro", groups=AddVisit.class)
	private Date visitEntrancedate;

	@Temporal(TemporalType.DATE)
	@Column(name="VISIT_EXITDATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent(message="La fecha de egreso debe ser en el futuro", groups=AddVisit.class)
	private Date visitExitdate;

	//bi-directional many-to-one association to Physicalcheckup+
	@OneToMany(mappedBy="visit")
	private List<Physicalcheckup> physicalcheckups;

	//bi-directional many-to-one association to Institutioncampus
	@ManyToOne
	@JoinColumn(name="INSTCAM_INSTCAM_ID")
	@NotNull(message="Se debe elegir una institución", groups=AddVisit.class)
	private Institutioncampus institutioncampus;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="PERS_PERS_ID")
	@NotNull(message="Se debe elegir una persona", groups=AddVisit.class)
	private Person person;

	//bi-directional many-to-one association to VisitVisitreason
	@OneToMany(mappedBy="visit")
	private List<VisitVisitreason> visitVisitreasons;

	public Visit() {
	}

	public long getVisitId() {
		return this.visitId;
	}

	public void setVisitId(long visitId) {
		this.visitId = visitId;
	}

	public String getVisitDetail() {
		return this.visitDetail;
	}

	public void setVisitDetail(String visitDetail) {
		this.visitDetail = visitDetail;
	}

	public Date getVisitEntrancedate() {
		return this.visitEntrancedate;
	}

	public void setVisitEntrancedate(Date visitEntrancedate) {
		this.visitEntrancedate = visitEntrancedate;
	}

	public Date getVisitExitdate() {
		return this.visitExitdate;
	}

	public void setVisitExitdate(Date visitExitdate) {
		this.visitExitdate = visitExitdate;
	}

	public List<Physicalcheckup> getPhysicalcheckups() {
		return this.physicalcheckups;
	}

	public void setPhysicalcheckups(List<Physicalcheckup> physicalcheckups) {
		this.physicalcheckups = physicalcheckups;
	}

	public Physicalcheckup addPhysicalcheckup(Physicalcheckup physicalcheckup) {
		getPhysicalcheckups().add(physicalcheckup);
		physicalcheckup.setVisit(this);

		return physicalcheckup;
	}

	public Physicalcheckup removePhysicalcheckup(Physicalcheckup physicalcheckup) {
		getPhysicalcheckups().remove(physicalcheckup);
		physicalcheckup.setVisit(null);

		return physicalcheckup;
	}

	public Institutioncampus getInstitutioncampus() {
		return this.institutioncampus;
	}

	public void setInstitutioncampus(Institutioncampus institutioncampus) {
		this.institutioncampus = institutioncampus;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<VisitVisitreason> getVisitVisitreasons() {
		return this.visitVisitreasons;
	}

	public void setVisitVisitreasons(List<VisitVisitreason> visitVisitreasons) {
		this.visitVisitreasons = visitVisitreasons;
	}

	public VisitVisitreason addVisitVisitreason(VisitVisitreason visitVisitreason) {
		getVisitVisitreasons().add(visitVisitreason);
		visitVisitreason.setVisit(this);

		return visitVisitreason;
	}

	public VisitVisitreason removeVisitVisitreason(VisitVisitreason visitVisitreason) {
		getVisitVisitreasons().remove(visitVisitreason);
		visitVisitreason.setVisit(null);

		return visitVisitreason;
	}

}