
package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;

	Integer id;
	String name;
	BigDecimal customResult;
	String path;
	Calendar checkTime;
	Picturetype picturetype;
	java.util.Set<Rgb> rgbs;
	java.util.Set<Result> results;
	java.util.Set<Feature> features;

	/**
	 * �����
	 *
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �����
	 *
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
	 * �˹�ָ���Ľ��.
	 *
	 */
	public void setCustomResult(BigDecimal customResult) {
		this.customResult = customResult;
	}

	/**
	 * �˹�ָ���Ľ��.
	 *
	 */
	public BigDecimal getCustomResult() {
		return this.customResult;
	}

	/**
	 * �洢·��
	 *
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * �洢·��
	 *
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * ͼƬ����ʱ��
	 *
	 */
	public void setCheckTime(Calendar checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * ͼƬ����ʱ��
	 *
	 */
	public Calendar getCheckTime() {
		return this.checkTime;
	}

	/**
	 */
	public void setPicturetype(Picturetype picturetype) {
		this.picturetype = picturetype;
	}

	/**
	 */
	public Picturetype getPicturetype() {
		return picturetype;
	}

	/**
	 */
	public void setRgbs(Set<Rgb> rgbs) {
		this.rgbs = rgbs;
	}

	/**
	 */
	public Set<Rgb> getRgbs() {
		if (rgbs == null) {
			rgbs = new java.util.LinkedHashSet<Rgb>();
		}
		return rgbs;
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
	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	/**
	 */
	public Set<Feature> getFeatures() {
		if (features == null) {
			features = new java.util.LinkedHashSet<Feature>();
		}
		return features;
	}

	/**
	 */
	public Picture() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Picture that) {
		setId(that.getId());
		setName(that.getName());
		setCustomResult(that.getCustomResult());
		setPath(that.getPath());
		setCheckTime(that.getCheckTime());
		setPicturetype(that.getPicturetype());
		setRgbs(new java.util.LinkedHashSet<Rgb>(that.getRgbs()));
		setResults(new java.util.LinkedHashSet<Result>(that.getResults()));
		setFeatures(new java.util.LinkedHashSet<Feature>(that.getFeatures()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("customResult=[").append(customResult).append("] ");
		buffer.append("path=[").append(path).append("] ");
		buffer.append("checkTime=[").append(checkTime).append("] ");

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
		if (!(obj instanceof Picture))
			return false;
		Picture equalCheck = (Picture) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
