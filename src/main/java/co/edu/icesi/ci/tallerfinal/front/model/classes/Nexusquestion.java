package co.edu.icesi.ci.tallerfinal.front.model.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class Nexusquestion implements Serializable {
	private static final long serialVersionUID = 1L;

	private long nexquesId;

	private String nexquesIsactive;

	private String nexquesName;

	private BigDecimal nexquesWeight;

	private Nexuspoll nexuspoll;

	public Nexusquestion() {
	}

	public long getNexquesId() {
		return this.nexquesId;
	}

	public void setNexquesId(long nexquesId) {
		this.nexquesId = nexquesId;
	}

	public String getNexquesIsactive() {
		return this.nexquesIsactive;
	}

	public void setNexquesIsactive(String nexquesIsactive) {
		this.nexquesIsactive = nexquesIsactive;
	}

	public String getNexquesName() {
		return this.nexquesName;
	}

	public void setNexquesName(String nexquesName) {
		this.nexquesName = nexquesName;
	}

	public BigDecimal getNexquesWeight() {
		return this.nexquesWeight;
	}

	public void setNexquesWeight(BigDecimal nexquesWeight) {
		this.nexquesWeight = nexquesWeight;
	}

	public Nexuspoll getNexuspoll() {
		return this.nexuspoll;
	}

	public void setNexuspoll(Nexuspoll nexuspoll) {
		this.nexuspoll = nexuspoll;
	}

}