
package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Set;




public class Rule implements Serializable {
	private static final long serialVersionUID = 1L;


	Integer id;
	String name;
	Boolean type;
	BigDecimal redCoefficient;
	BigDecimal greenCoefficient;
	BigDecimal blueCoefficient;
	BigDecimal correct;
	String memo;
Item item;
	Featureextramethod featureextramethod;
	java.util.Set<Picture> pictures;

	/**
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 */
	public String getName() {
		return this.name;
	}

	/**
	 */
	public void setType(Boolean type) {
		this.type = type;
	}

	/**
	 */
	public Boolean getType() {
		return this.type;
	}

	/**
	* ��ɫ����
	* 
	 */
	public void setRedCoefficient(BigDecimal redCoefficient) {
		this.redCoefficient = redCoefficient;
	}

	/**
	* ��ɫ����
	* 
	 */
	public BigDecimal getRedCoefficient() {
		return this.redCoefficient;
	}

	/**
	 */
	public void setGreenCoefficient(BigDecimal greenCoefficient) {
		this.greenCoefficient = greenCoefficient;
	}

	/**
	 */
	public BigDecimal getGreenCoefficient() {
		return this.greenCoefficient;
	}

	/**
	 */
	public void setBlueCoefficient(BigDecimal blueCoefficient) {
		this.blueCoefficient = blueCoefficient;
	}

	/**
	 */
	public BigDecimal getBlueCoefficient() {
		return this.blueCoefficient;
	}

	/**
	* ����
	* 
	 */
	public void setCorrect(BigDecimal correct) {
		this.correct = correct;
	}

	/**
	* ����
	* 
	 */
	public BigDecimal getCorrect() {
		return this.correct;
	}

	/**
	* ÿһ�������Ŀ�����ʹ�ò�ͬ�ĳ�ȡ���ݵķ�ʽ������Ӧ�ò�һ��������
	* 
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	* ÿһ�������Ŀ�����ʹ�ò�ͬ�ĳ�ȡ���ݵķ�ʽ������Ӧ�ò�һ��������
	* 
	 */
	public String getMemo() {
		return this.memo;
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
	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	/**
	 */

	public Set<Picture> getPictures() {
		if (pictures == null) {
			pictures = new java.util.LinkedHashSet<Picture>();
		}
		return pictures;
	}

	/**
	 */
	public Rule() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Rule that) {
		setId(that.getId());
		setName(that.getName());
		setType(that.getType());
		setRedCoefficient(that.getRedCoefficient());
		setGreenCoefficient(that.getGreenCoefficient());
		setBlueCoefficient(that.getBlueCoefficient());
		setCorrect(that.getCorrect());
		setMemo(that.getMemo());
		setItem(that.getItem());
		setFeatureextramethod(that.getFeatureextramethod());
		setPictures(new java.util.LinkedHashSet<Picture>(that.getPictures()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("redCoefficient=[").append(redCoefficient).append("] ");
		buffer.append("greenCoefficient=[").append(greenCoefficient).append("] ");
		buffer.append("blueCoefficient=[").append(blueCoefficient).append("] ");
		buffer.append("correct=[").append(correct).append("] ");
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
		if (!(obj instanceof Rule))
			return false;
		Rule equalCheck = (Rule) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
