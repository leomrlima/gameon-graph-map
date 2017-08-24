package gameon.map.rest;

public class Site {

	public static enum RoomType {
		Room, Empty
	}

	/**
	 * Site id
	 */
	private String _id;

	private RoomInfo info;

	/**
	 * Owner
	 */
	private String owner;

	private Coordinates coord;

	private RoomType type;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public RoomInfo getInfo() {
		return info;
	}

	public void setInfo(RoomInfo info) {
		this.info = info;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Coordinates getCoord() {
		return coord;
	}

	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((coord == null) ? 0 : coord.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Site other = (Site) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (coord == null) {
			if (other.coord != null)
				return false;
		} else if (!coord.equals(other.coord))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Site [" + (_id != null ? "_id=" + _id + ", " : "") + (info != null ? "info=" + info + ", " : "")
				+ (owner != null ? "owner=" + owner + ", " : "") + (coord != null ? "coord=" + coord + ", " : "")
				+ (type != null ? "type=" + type : "") + "]";
	}

}
