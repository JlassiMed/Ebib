package entity;

import java.io.Serializable;

public class ResPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_Oeuv;
	private int id_Adh;
	public ResPK() {
		super();
	}
	public int getId_Oeuv() {
		return id_Oeuv;
	}
	public void setId_Oeuv(int id_Oeuv) {
		this.id_Oeuv = id_Oeuv;
	}
	public int getId_Adh() {
		return id_Adh;
	}
	public void setId_Adh(int id_Adh) {
		this.id_Adh = id_Adh;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_Adh;
		result = prime * result + id_Oeuv;
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
		ResPK other = (ResPK) obj;
		if (id_Adh != other.id_Adh)
			return false;
		if (id_Oeuv != other.id_Oeuv)
			return false;
		return true;
	}
	
	
}
