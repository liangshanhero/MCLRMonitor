
package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;


public class Samplefeature implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	* ѵ������
	* 
	 */


	Integer sampleField;

	Integer projectField;

	Integer featureextramethodField;

	String name;

	Integer red;

	Integer green;

	Integer blue;

	BigDecimal gray;

	String memo;


	Featureextramethod featureextramethod;

	Item item;

	Sample sample;

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
	* ûɶ������ֶ�
	* 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	* ûɶ������ֶ�
	* 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setRed(Integer red) {
		this.red = red;
	}

	/**
	 */
	public Integer getRed() {
		return this.red;
	}

	/**
	 */
	public void setGreen(Integer green) {
		this.green = green;
	}

	/**
	 */
	public Integer getGreen() {
		return this.green;
	}

	/**
	 */
	public void setBlue(Integer blue) {
		this.blue = blue;
	}

	/**
	 */
	public Integer getBlue() {
		return this.blue;
	}

	/**
	 */
	public void setGray(BigDecimal gray) {
		this.gray = gray;
	}

	/**
	 */
	public BigDecimal getGray() {
		return this.gray;
	}

	/**
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 */
	public void setFeatureextramethod(Featureextramethod featureextramethod) {
		this.featureextramethod = featureextramethod;
	}

	/**
	 */

	public Featureextramethod getFeatureextramethod() {
		return featureextramethod;
	}

	/**
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 */

	public Item getItem() {
		return item;
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
	public Samplefeature() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Samplefeature that) {
		setSampleField(that.getSampleField());
		setProjectField(that.getProjectField());
		setFeatureextramethodField(that.getFeatureextramethodField());
		setName(that.getName());
		setRed(that.getRed());
		setGreen(that.getGreen());
		setBlue(that.getBlue());
		setGray(that.getGray());
		setMemo(that.getMemo());
		setFeatureextramethod(that.getFeatureextramethod());
		setItem(that.getItem());
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
		buffer.append("featureextramethodField=[").append(featureextramethodField).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("red=[").append(red).append("] ");
		buffer.append("green=[").append(green).append("] ");
		buffer.append("blue=[").append(blue).append("] ");
		buffer.append("gray=[").append(gray).append("] ");
		buffer.append("memo=[").append(memo).append("] ");

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
		result = (int) (prime * result + ((featureextramethodField == null) ? 0 : featureextramethodField.hashCode()));
		return result;
	}

	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Samplefeature))
			return false;
		Samplefeature equalCheck = (Samplefeature) obj;
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
}
