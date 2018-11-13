package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.util.HashSet;
import java.util.Set;




public class User implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String password;
	private String memo;
	private Set<Sample> samples = new HashSet<Sample>(0);
	private Set<Check> checks = new HashSet<Check>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String name, String password, String memo, Set<Sample> samples, Set<Check> checks) {
		this.name = name;
		this.password = password;
		this.memo = memo;
		this.samples = samples;
		this.checks = checks;
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


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}