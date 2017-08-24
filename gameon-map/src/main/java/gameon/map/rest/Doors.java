package gameon.map.rest;

public class Doors {

	/**
	 * North door (140 characters)
	 */
	private String n;

	/**
	 * West door (140 characters)
	 */
	private String w;

	/**
	 * South door (140 characters)
	 */
	private String s;

	/**
	 * East door (140 characters)
	 */
	private String e;

	/**
	 * Door in the ceiling (Up) (140 characters)
	 */
	private String u;

	/**
	 * Door in the floor (Down)(140 characters)
	 */
	private String d;

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	@Override
	public String toString() {
		return "Doors [" + (n != null ? "n=" + n + ", " : "") + (w != null ? "w=" + w + ", " : "")
				+ (s != null ? "s=" + s + ", " : "") + (e != null ? "e=" + e + ", " : "")
				+ (u != null ? "u=" + u + ", " : "") + (d != null ? "d=" + d : "") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((e == null) ? 0 : e.hashCode());
		result = prime * result + ((n == null) ? 0 : n.hashCode());
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		result = prime * result + ((u == null) ? 0 : u.hashCode());
		result = prime * result + ((w == null) ? 0 : w.hashCode());
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
		Doors other = (Doors) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		if (e == null) {
			if (other.e != null)
				return false;
		} else if (!e.equals(other.e))
			return false;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		if (u == null) {
			if (other.u != null)
				return false;
		} else if (!u.equals(other.u))
			return false;
		if (w == null) {
			if (other.w != null)
				return false;
		} else if (!w.equals(other.w))
			return false;
		return true;
	}

}
