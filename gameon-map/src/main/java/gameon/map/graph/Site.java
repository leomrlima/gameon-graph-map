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
    private Coordinate coordinate;

    @Column
    private boolean empty;

    @Column
    private boolean doorAvailable;

    Site() {
    }

    Site(String name, String connectionType, String connectionTarget,
         String connectionToken, String fullName, String description, String owner,
         Coordinate coordinate, boolean empty, boolean doorAvailable) {
        this.name = name;
        this.connectionType = connectionType;
        this.connectionTarget = connectionTarget;
        this.connectionToken = connectionToken;
        this.fullName = fullName;
        this.description = description;
        this.owner = owner;
        this.coordinate = coordinate;
        this.empty = empty;
        this.doorAvailable = doorAvailable;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public String getConnectionTarget() {
        return connectionTarget;
    }

    public String getConnectionToken() {
        return connectionToken;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isDoorAvailable() {
        return doorAvailable;
    }

    public long getWeight() {
        return ofNullable(coordinate).map(Coordinate::getWeight).orElse(0L);
    }


    public void merge(Site site) {

        if (Objects.isNull(id)) {
            this.id = site.id;
        }
        ofNullable(site.name).ifPresent(n -> this.name = n);
        ofNullable(site.connectionType).ifPresent(n -> this.connectionType = n);
        ofNullable(site.connectionTarget).ifPresent(n -> this.connectionTarget = n);
        ofNullable(site.fullName).ifPresent(n -> this.fullName = n);
        ofNullable(site.description).ifPresent(n -> this.description = n);
        ofNullable(site.owner).ifPresent(n -> this.owner = n);
        ofNullable(site.doorAvailable).ifPresent(n -> this.doorAvailable = n);

    }

    public static SiteBuilder builder() {
        return new SiteBuilder();
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
        sb.append(", doorAvailable=").append(doorAvailable);
        sb.append('}');
        return sb.toString();
    }


}
