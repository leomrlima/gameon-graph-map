package gameon.map.graph;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

import java.io.Serializable;
import java.util.Objects;

import static java.util.Optional.ofNullable;

@Entity
public class Site implements Serializable {

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
    private Coordinate coordinate = Coordinate.EMPTY;

    @Column
    private boolean empty;

    @Column
    private boolean doorAvaiable;

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


    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }


    public boolean isDoorAvaiable() {
        return doorAvaiable;
    }

    public long getWeight() {
        return ofNullable(coordinate).map(Coordinate::getWeight).orElse(0L);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setDoorAvaiable(boolean doorAvaiable) {
        this.doorAvaiable = doorAvaiable;
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
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", connectionType='").append(connectionType).append('\'');
        sb.append(", connectionTarget='").append(connectionTarget).append('\'');
        sb.append(", connectionToken='").append(connectionToken).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", coordinate=").append(coordinate);
        sb.append(", empty=").append(empty);
        sb.append(", doorAvaiable=").append(doorAvaiable);
        sb.append('}');
        return sb.toString();
    }
}
