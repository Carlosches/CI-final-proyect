package co.edu.icesi.ci.tallerfinal.front.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Nexuspoll implements Serializable {

	private static final long serialVersionUID = 1L;

	private long nexpollId;

	private BigDecimal instInstId;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date nexpollEnddate;

	private String nexpollName;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date nexpollStartdate;

	@JsonIgnore
	private List<Nexusquestion> nexusquestions;

	public Nexuspoll() {
	}

	public long getNexpollId() {
		return this.nexpollId;
	}

	public void setNexpollId(long nexpollId) {
		this.nexpollId = nexpollId;
	}

	public BigDecimal getInstInstId() {
		return this.instInstId;
	}

	public void setInstInstId(BigDecimal instInstId) {
		this.instInstId = instInstId;
	}

	public Date getNexpollEnddate() {
		return this.nexpollEnddate;
	}

	public void setNexpollEnddate(Date nexpollEnddate) {
		this.nexpollEnddate = nexpollEnddate;
	}

	public String getNexpollName() {
		return this.nexpollName;
	}

	public void setNexpollName(String nexpollName) {
		this.nexpollName = nexpollName;
	}

	public Date getNexpollStartdate() {
		return this.nexpollStartdate;
	}

	public void setNexpollStartdate(Date nexpollStartdate) {
		this.nexpollStartdate = nexpollStartdate;
	}

	public List<co.edu.icesi.ci.tallerfinal.front.model.classes.Nexusquestion> getNexusquestions() {
		return this.nexusquestions;
	}

	public void setNexusquestions(List<co.edu.icesi.ci.tallerfinal.front.model.classes.Nexusquestion> nexusquestions) {
		this.nexusquestions = nexusquestions;
	}

	public co.edu.icesi.ci.tallerfinal.front.model.classes.Nexusquestion addNexusquestion(co.edu.icesi.ci.tallerfinal.front.model.classes.Nexusquestion nexusquestion) {
		getNexusquestions().add(nexusquestion);
		nexusquestion.setNexuspoll(this);

		return nexusquestion;
	}

	public co.edu.icesi.ci.tallerfinal.front.model.classes.Nexusquestion removeNexusquestion(Nexusquestion nexusquestion) {
		getNexusquestions().remove(nexusquestion);
		nexusquestion.setNexuspoll(null);

		return nexusquestion;
	}

}