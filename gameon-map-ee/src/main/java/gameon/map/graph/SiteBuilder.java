package gameon.map.graph;

public class SiteBuilder {

    private String name;

    private String connectionType;

    private String connectionTarget;

    private String connectionToken;

    private String fullName;

    private String description;

    private String owner;

    private Coordinate coordinate;

    private boolean empty;

    private boolean doorAvailable;

    SiteBuilder() {
    }

    public SiteBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SiteBuilder withConnectionType(String connectionType) {
        this.connectionType = connectionType;
        return this;
    }

    public SiteBuilder withConnectionTarget(String connectionTarget) {
        this.connectionTarget = connectionTarget;
        return this;
    }

    public SiteBuilder withConnectionToken(String connectionToken) {
        this.connectionToken = connectionToken;
        return this;
    }

    public SiteBuilder withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public SiteBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public SiteBuilder withOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public SiteBuilder withCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public SiteBuilder withEmpty(boolean empty) {
        this.empty = empty;
        return this;
    }

    public SiteBuilder withDoorAvailable(boolean doorAvailable) {
        this.doorAvailable = doorAvailable;
        return this;
    }

    public Site build() {
        return new Site(name, connectionType, connectionTarget, connectionToken, fullName, description, owner, coordinate, empty, doorAvailable);
    }
}