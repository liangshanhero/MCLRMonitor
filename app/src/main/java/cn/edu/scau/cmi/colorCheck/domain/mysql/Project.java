package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.util.HashSet;
import java.util.Set;

public class Project implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String memo;
	private Set<Sample> samples = new HashSet<Sample>(0);
	private Set<Check> checks = new HashSet<Check>(0);
	private Set<Rule> rules = new HashSet<Rule>(0);

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** full constructor */
	public Project(String name, String memo, Set<Sample> samples, Set<Check> checks, Set<Rule> rules) {
		this.name = name;
		this.memo = memo;
		this.samples = samples;
		this.checks = checks;
		this.rules = rules;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set<Sample> getSamples() {
		return this.samples;
	}

	public void setSamples(Set<Sample> samples) {
		this.samples = samples;
	}

	public Set<Check> getChecks() {
		return this.checks;
	}

	public void setChecks(Set<Check> checks) {
		this.checks = checks;
	}

	public Set<Rule> getRules() {
		return this.rules;
	}

	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}

}