
package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Set;




public class Check implements Serializable {
	private static final long serialVersionUID = 1L;


	Integer id;
	String name;
	Integer red;
	Integer green;
	Integer blue;
	BigDecimal gray;
	BigDecimal result;
	String memo;
	Rule rule;
	Project project;
	Featureextramethod featureextramethod;
	public void setId(Integer id) {
		this.id = id;
	}
public Integer getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setRed(Integer red) {
		this.red = red;
	}
	public Integer getRed() {
		return this.red;
	}
	public void setGreen(Integer green) {
		this.green = green;
	}
	public Integer getGreen() {
		return this.green;
	}
	public void setBlue(Integer blue) {
		this.blue = blue;
	}
	public Integer getBlue() {
		return this.blue;
	}
	public void setGray(BigDecimal gray) {
		this.gray = gray;
	}
	public BigDecimal getGray() {
		return this.gray;
	}
	public void setResult(BigDecimal result) {
		this.result = result;
	}
	public BigDecimal getResult() {
		return this.result;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMemo() {
		return this.memo;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public Rule getRule() {
		return rule;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Project getProject() {
		return project;
	}
	public void setFeatureextramethod(Featureextramethod featureextramethod) {
		this.featureextramethod = featureextramethod;
	}
	public Featureextramethod getFeatureextramethod() {
		return featureextramethod;
	}
	public Check() {
	}
	public void copy(Check that) {
		setId(that.getId());
		setName(that.getName());
		setRed(that.getRed());
		setGreen(that.getGreen());
		setBlue(that.getBlue());
		setGray(that.getGray());
		setResult(that.getResult());
		setMemo(that.getMemo());
		setRule(that.getRule());
		setProject(that.getProject());
		setFeatureextramethod(that.getFeatureextramethod());
	}
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("red=[").append(red).append("] ");
		buffer.append("green=[").append(green).append("] ");
		buffer.append("blue=[").append(blue).append("] ");
		buffer.append("gray=[").append(gray).append("] ");
		buffer.append("result=[").append(result).append("] ");
		buffer.append("memo=[").append(memo).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((id == null) ? 0 : id.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Check))
			return false;
		Check equalCheck = (Check) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
