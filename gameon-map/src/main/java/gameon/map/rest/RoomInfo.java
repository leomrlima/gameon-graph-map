package gameon.map.rest;

public class RoomInfo {

	/**
	 * Short/Terse name of the target room, must be unique within the owner's
	 * rooms ,
	 */
	private String name;

	private ConnectionDetails connectionDetails;

	/**
	 * (Optional) Human-friendly room name
	 */
	private String fullName;

	/**
	 * (Optional) Player-friendly room description (140 characters)
	 */
	private String description;

	private Doors doors;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ConnectionDetails getConnectionDetails() {
		return connectionDetails;
	}

	public void setConnectionDetails(ConnectionDetails connectionDetails) {
		this.connectionDetails = connectionDetails;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Doors getDoors() {
		return doors;
	}

	public void setDoors(Doors doors) {
		this.doors = doors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connectionDetails == null) ? 0 : connectionDetails.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((doors == null) ? 0 : doors.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		RoomInfo other = (RoomInfo) obj;
		if (connectionDetails == null) {
			if (other.connectionDetails != null)
				return false;
		} else if (!connectionDetails.equals(other.connectionDetails))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (doors == null) {
			if (other.doors != null)
				return false;
		} else if (!doors.equals(other.doors))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoomInfo [" + (name != null ? "name=" + name + ", " : "")
				+ (connectionDetails != null ? "connectionDetails=" + connectionDetails + ", " : "")
				+ (fullName != null ? "fullName=" + fullName + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (doors != null ? "doors=" + doors : "") + "]";
	}

}
