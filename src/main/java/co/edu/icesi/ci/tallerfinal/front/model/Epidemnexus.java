package co.edu.icesi.ci.tallerfinal.front.model;



import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the EPIDEMNEXUS database table.
 * 
 */
@Entity
@NamedQuery(name="Epidemnexus.findAll", query="SELECT e FROM Epidemnexus e")
public class Epidemnexus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EPIDEMNEXUS_EPINEXID_GENERATOR", sequenceName="EPIDEMNEXUS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPIDEMNEXUS_EPINEXID_GENERATOR")
	@Column(name="EPINEX_ID")
	private long epinexId;

	@Column(name="EPINEX_ISACTIVE")
	private String epinexIsactive;

	@Column(name="EPINEX_NAME")
	private String epinexName;

	//bi-directional many-to-one association to DenialNexus
	@OneToMany(mappedBy="epidemnexus")
	private List<co.edu.icesi.ci.tallerfinal.front.model.DenialNexus> denialNexuses;

	//bi-directional many-to-one association to Nexusquestion
	@OneToMany(mappedBy="epidemnexus")
	private List<co.edu.icesi.ci.tallerfinal.front.model.Nexusquestion> nexusquestions;

	public Epidemnexus() {
	}

	public long getEpinexId() {
		return this.epinexId;
	}

	public void setEpinexId(long epinexId) {
		this.epinexId = epinexId;
	}

	public String getEpinexIsactive() {
		return this.epinexIsactive;
	}

	public void setEpinexIsactive(String epinexIsactive) {
		this.epinexIsactive = epinexIsactive;
	}

	public String getEpinexName() {
		return this.epinexName;
	}

	public void setEpinexName(String epinexName) {
		this.epinexName = epinexName;
	}

	public List<co.edu.icesi.ci.tallerfinal.front.model.DenialNexus> getDenialNexuses() {
		return this.denialNexuses;
	}

	public void setDenialNexuses(List<co.edu.icesi.ci.tallerfinal.front.model.DenialNexus> denialNexuses) {
		this.denialNexuses = denialNexuses;
	}

	public co.edu.icesi.ci.tallerfinal.front.model.DenialNexus addDenialNexus(co.edu.icesi.ci.tallerfinal.front.model.DenialNexus denialNexus) {
		getDenialNexuses().add(denialNexus);
		denialNexus.setEpidemnexus(this);

		return denialNexus;
	}

	public co.edu.icesi.ci.tallerfinal.front.model.DenialNexus removeDenialNexus(DenialNexus denialNexus) {
		getDenialNexuses().remove(denialNexus);
		denialNexus.setEpidemnexus(null);

		return denialNexus;
	}

	public List<co.edu.icesi.ci.tallerfinal.front.model.Nexusquestion> getNexusquestions() {
		return this.nexusquestions;
	}

	public void setNexusquestions(List<co.edu.icesi.ci.tallerfinal.front.model.Nexusquestion> nexusquestions) {
		this.nexusquestions = nexusquestions;
	}

	public co.edu.icesi.ci.tallerfinal.front.model.Nexusquestion addNexusquestion(co.edu.icesi.ci.tallerfinal.front.model.Nexusquestion nexusquestion) {
		getNexusquestions().add(nexusquestion);
		nexusquestion.setEpidemnexus(this);

		return nexusquestion;
	}

	public co.edu.icesi.ci.tallerfinal.front.model.Nexusquestion removeNexusquestion(Nexusquestion nexusquestion) {
		getNexusquestions().remove(nexusquestion);
		nexusquestion.setEpidemnexus(null);

		return nexusquestion;
	}

}