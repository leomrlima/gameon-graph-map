package gameon.map.rest;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Site)) {
            return false;
        }
        Site site = (Site) o;
        return Objects.equals(_id, site._id) &&
                Objects.equals(info, site.info) &&
                Objects.equals(owner, site.owner) &&
                Objects.equals(coord, site.coord) &&
                type == site.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, info, owner, coord, type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Site{");
        sb.append("_id='").append(_id).append('\'');
        sb.append(", info=").append(info);
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", coord=").append(coord);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
