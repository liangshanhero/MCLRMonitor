
package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;
import java.util.Set;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer id;
	String name;
	String memo;
	java.util.Set<Result> results;
	java.util.Set<Rule> rules;
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
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMemo() {
		return this.memo;
	}
	public void setResults(Set<Result> results) {
		this.results = results;
	}
	public Set<Result> getResults() {
		if (results == null) {
			results = new java.util.LinkedHashSet<Result>();
		}
		return results;
	}
	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}
	public Set<Rule> getRules() {
		if (rules == null) {
			rules = new java.util.LinkedHashSet<Rule>();
		}
		return rules;
	}
	public Item() {
	}
	public void copy(Item that) {
		setId(that.getId());
		setName(that.getName());
		setMemo(that.getMemo());
		setResults(new java.util.LinkedHashSet<Result>(that.getResults()));
		setRules(new java.util.LinkedHashSet<Rule>(that.getRules()));
	}
	public String toString() {

		StringBuilder buffer = new StringBuilder();

		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("memo=[").append(memo).append("] ");

		return buffer.toString();
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((id == null) ? 0 : id.hashCode()));
		return result;
	}
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Item))
			return false;
		Item equalCheck = (Item) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}