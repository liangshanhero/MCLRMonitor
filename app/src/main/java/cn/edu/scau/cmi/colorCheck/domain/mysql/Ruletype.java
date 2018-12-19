package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;
import java.util.Set;

public class Ruletype implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer id;
    String name;
    java.util.Set<Rule> rules;

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
    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    /**
     */
    public Set<Rule> getRules() {
        if (rules == null) {
            rules = new java.util.LinkedHashSet<Rule>();
        }
        return rules;
    }

    /**
     */
    public Ruletype() {
    }

    /**
     * Copies the contents of the specified bean into this bean.
     *
     */
    public void copy(Ruletype that) {
        setId(that.getId());
        setName(that.getName());
        setRules(new java.util.LinkedHashSet<Rule>(that.getRules()));
    }

    /**
     * Returns a textual representation of a bean.
     *
     */
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("id=[").append(id).append("] ");
        buffer.append("name=[").append(name).append("] ");

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
        if (!(obj instanceof Ruletype))
            return false;
        Ruletype equalCheck = (Ruletype) obj;
        if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
            return false;
        if (id != null && !id.equals(equalCheck.id))
            return false;
        return true;
    }
}
