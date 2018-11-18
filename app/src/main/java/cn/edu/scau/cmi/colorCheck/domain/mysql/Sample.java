
package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Calendar;
import java.util.Set;

public class Sample implements Serializable {
	private static final long serialVersionUID = 1L;

	Integer id;

	String name;

	Calendar time;


	java.util.Set<Sample2project> sample2projects;

	java.util.Set<Samplefeature> samplefeatures;

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
	public void setTime(Calendar time) {
		this.time = time;
	}

	/**
	 */
	public Calendar getTime() {
		return this.time;
	}

	/**
	 */
	public void setSample2projects(Set<Sample2project> sample2projects) {
		this.sample2projects = sample2projects;
	}

	/**
	 */

	public Set<Sample2project> getSample2projects() {
		if (sample2projects == null) {
			sample2projects = new java.util.LinkedHashSet<Sample2project>();
		}
		return sample2projects;
	}

	/**
	 */
	public void setSamplefeatures(Set<Samplefeature> samplefeatures) {
		this.samplefeatures = samplefeatures;
	}

	/**
	 */

	public Set<Samplefeature> getSamplefeatures() {
		if (samplefeatures == null) {
			samplefeatures = new java.util.LinkedHashSet<Samplefeature>();
		}
		return samplefeatures;
	}

	/**
	 */
	public Sample() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Sample that) {
		setId(that.getId());
		setName(that.getName());
		setTime(that.getTime());
		setSample2projects(new java.util.LinkedHashSet<Sample2project>(that.getSample2projects()));
		setSamplefeatures(new java.util.LinkedHashSet<Samplefeature>(that.getSamplefeatures()));
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("time=[").append(time).append("] ");

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
		if (!(obj instanceof Sample))
			return false;
		Sample equalCheck = (Sample) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
