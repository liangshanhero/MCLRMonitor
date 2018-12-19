
package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Set;

public class Rule implements Serializable {
	private static final long serialVersionUID = 1L;

	Integer id;
	String name;
	Integer distance;
	Integer deleta;
	BigDecimal redCoefficient;
	BigDecimal greenCoefficient;
	BigDecimal blueCoefficient;
	BigDecimal grayCoefficient;
	BigDecimal correct;
	String memo;
	Ruletype ruletype;
	Item item;
	java.util.Set<Result> results;

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
	 * �����Ŀ������ߵľ���
	 *
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	/**
	 * �����Ŀ������ߵľ���
	 *
	 */
	public Integer getDistance() {
		return this.distance;
	}

	/**
	 * ��Ϊ��ȡ�������������ƫ���ˣ���һ������+ƫ���������������
	 *
	 */
	public void setDeleta(Integer deleta) {
		this.deleta = deleta;
	}

	/**
	 * ��Ϊ��ȡ�������������ƫ���ˣ���һ������+ƫ���������������
	 *
	 */
	public Integer getDeleta() {
		return this.deleta;
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
	 */
	public void setGrayCoefficient(BigDecimal grayCoefficient) {
		this.grayCoefficient = grayCoefficient;
	}

	/**
	 */
	public BigDecimal getGrayCoefficient() {
		return this.grayCoefficient;
	}

	/**
	 * ����ƫ��
	 *
	 */
	public void setCorrect(BigDecimal correct) {
		this.correct = correct;
	}

	/**
	 * ����ƫ��
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
	public void setRuletype(Ruletype ruletype) {
		this.ruletype = ruletype;
	}

	/**
	 */
	public Ruletype getRuletype() {
		return ruletype;
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
	public void setResults(Set<Result> results) {
		this.results = results;
	}

	/**
	 */
	public Set<Result> getResults() {
		if (results == null) {
			results = new java.util.LinkedHashSet<Result>();
		}
		return results;
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
		setDistance(that.getDistance());
		setDeleta(that.getDeleta());
		setRedCoefficient(that.getRedCoefficient());
		setGreenCoefficient(that.getGreenCoefficient());
		setBlueCoefficient(that.getBlueCoefficient());
		setGrayCoefficient(that.getGrayCoefficient());
		setCorrect(that.getCorrect());
		setMemo(that.getMemo());
		setRuletype(that.getRuletype());
		setItem(that.getItem());
		setResults(new java.util.LinkedHashSet<Result>(that.getResults()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("distance=[").append(distance).append("] ");
		buffer.append("deleta=[").append(deleta).append("] ");
		buffer.append("redCoefficient=[").append(redCoefficient).append("] ");
		buffer.append("greenCoefficient=[").append(greenCoefficient).append("] ");
		buffer.append("blueCoefficient=[").append(blueCoefficient).append("] ");
		buffer.append("grayCoefficient=[").append(grayCoefficient).append("] ");
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
