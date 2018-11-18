
package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;

public class SamplefeaturePK implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */
	public SamplefeaturePK() {
	}


	public Integer sampleField;

	public Integer projectField;

	public Integer featureextramethodField;

	/**
	* ѵ������
	* 
	 */
	public void setSampleField(Integer sampleField) {
		this.sampleField = sampleField;
	}

	/**
	* ѵ������
	* 
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
	public void setFeatureextramethodField(Integer featureextramethodField) {
		this.featureextramethodField = featureextramethodField;
	}

	/**
	 */
	public Integer getFeatureextramethodField() {
		return this.featureextramethodField;
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((sampleField == null) ? 0 : sampleField.hashCode()));
		result = (int) (prime * result + ((projectField == null) ? 0 : projectField.hashCode()));
		result = (int) (prime * result + ((featureextramethodField == null) ? 0 : featureextramethodField.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SamplefeaturePK))
			return false;
		SamplefeaturePK equalCheck = (SamplefeaturePK) obj;
		if ((sampleField == null && equalCheck.sampleField != null) || (sampleField != null && equalCheck.sampleField == null))
			return false;
		if (sampleField != null && !sampleField.equals(equalCheck.sampleField))
			return false;
		if ((projectField == null && equalCheck.projectField != null) || (projectField != null && equalCheck.projectField == null))
			return false;
		if (projectField != null && !projectField.equals(equalCheck.projectField))
			return false;
		if ((featureextramethodField == null && equalCheck.featureextramethodField != null) || (featureextramethodField != null && equalCheck.featureextramethodField == null))
			return false;
		if (featureextramethodField != null && !featureextramethodField.equals(equalCheck.featureextramethodField))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("SamplefeaturePK");
		sb.append(" sampleField: ").append(getSampleField());
		sb.append(" projectField: ").append(getProjectField());
		sb.append(" featureextramethodField: ").append(getFeatureextramethodField());
		return sb.toString();
	}
}
