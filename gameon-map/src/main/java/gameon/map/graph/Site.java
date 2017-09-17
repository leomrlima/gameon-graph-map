package gameon.map.graph;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

@Entity
public class Site {

	@Id
	private String id;

	@Column
	private String name;

	/**
	 * Connection type
	 */
	@Column
	private String connectionType;

	/**
	 * Connection target, usually a URL
	 */
	@Column
	private String connectionTarget;

	/**
	 * (Optional) A token used for mutual identification between the room and
	 * the mediator during the initial handshake when the connection is
	 * established
	 */
	@Column
	private String connectionToken;

	/**
	 * (Optional) Human-friendly room name
	 */
	@Column
	private String fullName;

	/**
	 * (Optional) Player-friendly room description (140 characters)
	 */
	@Column
	private String description;

	@Column
	private String owner;

	@Column
	private long x;

	@Column
	private long y;

	@Column
	private boolean empty;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public String getConnectionTarget() {
		return connectionTarget;
	}

	public void setConnectionTarget(String connectionTarget) {
		this.connectionTarget = connectionTarget;
	}

	public String getConnectionToken() {
		return connectionToken;
	}

	public void setConnectionToken(String connectionToken) {
		this.connectionToken = connectionToken;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	@Override
	public String toString() {
		return "Site [" + (id != null ? "id=" + id + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ (connectionType != null ? "connectionType=" + connectionType + ", " : "")
				+ (connectionTarget != null ? "connectionTarget=" + connectionTarget + ", " : "")
				+ (connectionToken != null ? "connectionToken=" + connectionToken + ", " : "")
				+ (fullName != null ? "fullName=" + fullName + ", " : "")
				+ (description != null ? "description=" + description + ", " : "")
				+ (owner != null ? "owner=" + owner + ", " : "") + "x=" + x + ", y=" + y + ", empty=" + empty + "]";
	}

}
