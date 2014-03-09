package Data;

/**
 * Created by spencerlandis on 3/8/14.
 */
public class Game {
    private long id;
    private String name;
    private String deck;
    private String imageUrl;
    private String site_detail_url;

    public Game(long gameID, String name, String deck, String imageUrl, String site_detail_url) {
        this.id = gameID;
        this.name = name;
        this.deck = deck;
        this.imageUrl = imageUrl;
        this.site_detail_url = site_detail_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return deck;
    }

    public void setDescription(String description) {
        this.deck = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGbUrl() {
        return site_detail_url;
    }

    public void setGbUrl(String gbUrl) {
        this.site_detail_url = gbUrl;
    }

}
