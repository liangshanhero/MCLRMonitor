
package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;

public class Sample2projectPK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public Sample2projectPK() {
	}


	public Integer sampleField;

	public Integer projectField;

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
		if (!(obj instanceof Sample2projectPK))
			return false;
		Sample2projectPK equalCheck = (Sample2projectPK) obj;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Sample2projectPK");
		sb.append(" sampleField: ").append(getSampleField());
		sb.append(" projectField: ").append(getProjectField());
		return sb.toString();
	}
}
