package Data;

/**
 * Created by spencerlandis on 3/9/14.
 */
public class Image
{
    private String icon_url;
    private String medium_url;
    private String screen_url;
    private String small_url;
    private String thumb_url;
    private String tiny_url;

    public Image(String iconUrl, String medium_url, String screen_url, String small_url, String thumb_url, String tiny_url)
    {
        this.icon_url = iconUrl;
        this.medium_url = medium_url;
        this.screen_url = screen_url;
        this.small_url = small_url;
        this.thumb_url = thumb_url;
        this.tiny_url = tiny_url;
    }

    public String getIconUrl()
    {
        return icon_url;
    }

    public void setIconUrl(String iconUrl)
    {
        this.icon_url = iconUrl;
    }

    public String getMedium_url()
    {
        return medium_url;
    }

    public void setMedium_url(String medium_url)
    {
        this.medium_url = medium_url;
    }

    public String getScreen_url()
    {
        return screen_url;
    }

    public void setScreen_url(String screen_url)
    {
        this.screen_url = screen_url;
    }

    public String getSmall_url()
    {
        return small_url;
    }

    public void setSmall_url(String small_url)
    {
        this.small_url = small_url;
    }

    public String getThumb_url()
    {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url)
    {
        this.thumb_url = thumb_url;
    }

    public String getTiny_url()
    {
        return tiny_url;
    }

    public void setTiny_url(String tiny_url)
    {
        this.tiny_url = tiny_url;
    }
}

