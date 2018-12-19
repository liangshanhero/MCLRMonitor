package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;
import java.math.BigDecimal;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     */

    Integer id;
    /**
     * Ĭ��-1����ʾû�м�⡣
     *
     */

    BigDecimal value;

    /**
     */
    Rule rule;
    /**
     */
    Picture picture;
    /**
     */
    Item item;

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
     * Ĭ��-1����ʾû�м�⡣
     *
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Ĭ��-1����ʾû�м�⡣
     *
     */
    public BigDecimal getValue() {
        return this.value;
    }

    /**
     */
    public void setRule(Rule rule) {
        this.rule = rule;
    }

    /**
     */
    public Rule getRule() {
        return rule;
    }

    /**
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    /**
     */
    public Picture getPicture() {
        return picture;
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
    public Result() {
    }

    /**
     * Copies the contents of the specified bean into this bean.
     *
     */
    public void copy(Result that) {
        setId(that.getId());
        setValue(that.getValue());
        setRule(that.getRule());
        setPicture(that.getPicture());
        setItem(that.getItem());
    }

    /**
     * Returns a textual representation of a bean.
     *
     */
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("id=[").append(id).append("] ");
        buffer.append("value=[").append(value).append("] ");

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
        if (!(obj instanceof Result))
            return false;
        Result equalCheck = (Result) obj;
        if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
            return false;
        if (id != null && !id.equals(equalCheck.id))
            return false;
        return true;
    }
}
