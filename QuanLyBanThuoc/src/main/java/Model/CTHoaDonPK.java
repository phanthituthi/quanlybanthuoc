package Model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CTHoaDonPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hoaDon;
	private String thuoc;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hoaDon == null) ? 0 : hoaDon.hashCode());
		result = prime * result + ((thuoc == null) ? 0 : thuoc.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTHoaDonPK other = (CTHoaDonPK) obj;
		if (hoaDon == null) {
			if (other.hoaDon != null)
				return false;
		} else if (!hoaDon.equals(other.hoaDon))
			return false;
		if (thuoc == null) {
			if (other.thuoc != null)
				return false;
		} else if (!thuoc.equals(other.thuoc))
			return false;
		return true;
	}
	public CTHoaDonPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
