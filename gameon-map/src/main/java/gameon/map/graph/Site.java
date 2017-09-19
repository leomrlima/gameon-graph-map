package gameon.map.graph;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

import java.util.Objects;

@Entity
public class Site {

	@Id
	private Long id;

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

	/**
	 * Weight for private Site placement algorithm
	 */
	@Column
	private long weight;

	@Column
	private boolean empty;

	public Long getId() {
		return id;
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

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getWeight() {
		return weight;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Site site = (Site) o;
		return Objects.equals(name, site.name) && Objects.equals(owner, site.owner);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, owner);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Site{");
		sb.append("id='").append(id).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", connectionType='").append(connectionType).append('\'');
		sb.append(", connectionTarget='").append(connectionTarget).append('\'');
		sb.append(", connectionToken='").append(connectionToken).append('\'');
		sb.append(", fullName='").append(fullName).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append(", owner='").append(owner).append('\'');
		sb.append(", x=").append(x);
		sb.append(", y=").append(y);
		sb.append(", y=").append(weight);
		sb.append(", empty=").append(empty);
		sb.append('}');
		return sb.toString();
	}
}
