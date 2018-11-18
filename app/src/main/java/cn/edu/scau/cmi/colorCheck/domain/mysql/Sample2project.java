
package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;


public class Sample2project implements Serializable {
	private static final long serialVersionUID = 1L;


	Integer sampleField;

	Integer projectField;

	BigDecimal result;


	Project project;

	Sample sample;

	/**
	 */
	public void setSampleField(Integer sampleField) {
		this.sampleField = sampleField;
	}

	/**
	 */
	public Integer getSampleField() {
		return this.sampleField;
	}

	/**
	 */
	public void setProjectField(Integer projectField) {
		this.projectField = projectField;
	}

	/**
	 */
	public Integer getProjectField() {
		return this.projectField;
	}

	/**
	* һ�����������ж�������Ŀ��ÿһ������ÿһ����Ŀ��һ�����ֵ
	* 
	 */
	public void setResult(BigDecimal result) {
		this.result = result;
	}

	/**
	* һ�����������ж�������Ŀ��ÿһ������ÿһ����Ŀ��һ�����ֵ
	* 
	 */
	public BigDecimal getResult() {
		return this.result;
	}

	/**
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 */

	public Project getProject() {
		return project;
	}

	/**
	 */
	public void setSample(Sample sample) {
		this.sample = sample;
	}

	/**
	 */

	public Sample getSample() {
		return sample;
	}

	/**
	 */
	public Sample2project() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Sample2project that) {
		setSampleField(that.getSampleField());
		setProjectField(that.getProjectField());
		setResult(that.getResult());
		setProject(that.getProject());
		setSample(that.getSample());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("sampleField=[").append(sampleField).append("] ");
		buffer.append("projectField=[").append(projectField).append("] ");
		buffer.append("result=[").append(result).append("] ");

		return buffer.toString();
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((sampleField == null) ? 0 : sampleField.hashCode()));
		result = (int) (prime * result + ((projectField == null) ? 0 : projectField.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Sample2project))
			return false;
		Sample2project equalCheck = (Sample2project) obj;
		if ((sampleField == null && equalCheck.sampleField != null) || (sampleField != null && equalCheck.sampleField == null))
			return false;
		if (sampleField != null && !sampleField.equals(equalCheck.sampleField))
			return false;
		if ((projectField == null && equalCheck.projectField != null) || (projectField != null && equalCheck.projectField == null))
			return false;
		if (projectField != null && !projectField.equals(equalCheck.projectField))
			return false;
		return true;
	}
}
