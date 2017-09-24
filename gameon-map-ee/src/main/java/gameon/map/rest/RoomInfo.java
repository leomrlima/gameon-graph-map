package gameon.map.rest;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoomInfo)) {
            return false;
        }
        RoomInfo roomInfo = (RoomInfo) o;
        return Objects.equals(name, roomInfo.name) &&
                Objects.equals(connectionDetails, roomInfo.connectionDetails) &&
                Objects.equals(fullName, roomInfo.fullName) &&
                Objects.equals(description, roomInfo.description) &&
                Objects.equals(doors, roomInfo.doors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, connectionDetails, fullName, description, doors);
    }
}
