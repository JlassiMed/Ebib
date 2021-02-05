package entity;

import java.io.Serializable;

public class EmprPK implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_Liv;
	private int id_Adh;
	
	
	public EmprPK() {
		super();
	}
	public int getId_Liv() {
		return id_Liv;
	}
	public void setId_Liv(int id_Liv) {
		this.id_Liv = id_Liv;
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
		result = prime * result + id_Liv;
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
		EmprPK other = (EmprPK) obj;
		if (id_Adh != other.id_Adh)
			return false;
		if (id_Liv != other.id_Liv)
			return false;
		return true;
	}
	
}
