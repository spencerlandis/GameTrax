package Data;

/**
 * Created by spencerlandis on 3/8/14.
 */
public class Game {
    private long gameID;
    private String name;
    private String description;
    private String imageUrl;
    private String gbUrl;

    public Game(long gameID, String name, String description, String imageUrl, String gbUrl) {
        this.gameID = gameID;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.gbUrl = gbUrl;
    }

    public long getGameID() {
        return gameID;
    }

    public void setGameID(long gameID) {
        this.gameID = gameID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGbUrl() {
        return gbUrl;
    }

    public void setGbUrl(String gbUrl) {
        this.gbUrl = gbUrl;
    }

}
