package cn.edu.scau.cmi.colorCheck.domain.mysql;

import java.io.Serializable;

public class Rgb implements Serializable {
    private static final long serialVersionUID = 1L;

    Integer id;
    Integer point;
    Integer red;
    Integer green;
    Integer blue;
    Integer gray;

    Picture picture;

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
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     */
    public Integer getPoint() {
        return this.point;
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
    public void setGray(Integer gray) {
        this.gray = gray;
    }

    /**
     */
    public Integer getGray() {
        return this.gray;
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
    public Rgb() {
    }

    /**
     * Copies the contents of the specified bean into this bean.
     *
     */
    public void copy(Rgb that) {
        setId(that.getId());
        setPoint(that.getPoint());
        setRed(that.getRed());
        setGreen(that.getGreen());
        setBlue(that.getBlue());
        setGray(that.getGray());
        setPicture(that.getPicture());
    }

    /**
     * Returns a textual representation of a bean.
     *
     */
    public String toString() {

        StringBuilder buffer = new StringBuilder();

        buffer.append("id=[").append(id).append("] ");
        buffer.append("point=[").append(point).append("] ");
        buffer.append("red=[").append(red).append("] ");
        buffer.append("green=[").append(green).append("] ");
        buffer.append("blue=[").append(blue).append("] ");
        buffer.append("gray=[").append(gray).append("] ");

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
        if (!(obj instanceof Rgb))
            return false;
        Rgb equalCheck = (Rgb) obj;
        if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
            return false;
        if (id != null && !id.equals(equalCheck.id))
            return false;
        return true;
    }
}