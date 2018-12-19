
package cn.edu.scau.cmi.colorCheck.domain.mysql;



import java.io.Serializable;
import java.util.Set;
public class Feature implements Serializable {
	private static final long serialVersionUID = 1L;

	Integer id;
	Integer point;
	Boolean isControl;
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
	 * �Ƿ��ǿ����ߣ�Ĭ�ϲ��ǣ�ȡֵΪ0��
	 *
	 */
	public void setIsControl(Boolean isControl) {
		this.isControl = isControl;
	}

	/**
	 * �Ƿ��ǿ����ߣ�Ĭ�ϲ��ǣ�ȡֵΪ0��
	 *
	 */
	public Boolean getIsControl() {
		return this.isControl;
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
	public Feature() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(Feature that) {
		setId(that.getId());
		setPoint(that.getPoint());
		setIsControl(that.getIsControl());
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
		buffer.append("isControl=[").append(isControl).append("] ");

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
		if (!(obj instanceof Feature))
			return false;
		Feature equalCheck = (Feature) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}