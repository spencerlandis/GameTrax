package Data;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by spencerlandis on 3/8/14.
 */
public class Game {
    private int id;
    private String name;
    private String deck;
    private String site_detail_url;
    private Image image;

    public Game(int id, String name, String deck, String site_detail_url, Image image) {
        this.id = id;
        this.name = name;
        this.deck = deck;
        this.site_detail_url = site_detail_url;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getSite_detail_url() {
        return site_detail_url;
    }

    public void setSite_detail_url(String site_detail_url) {
        this.site_detail_url = site_detail_url;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void loadImage(final ImageView img, final Activity activity) throws IOException {

        new Thread(new Runnable() {
            public void run() {

                final Bitmap x;

                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) new URL(image.getIcon_url()).openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    connection.connect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputStream input = null;
                try {
                    input = connection.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                x = BitmapFactory.decodeStream(input);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        img.setImageBitmap(x);
                    }
                });
            }
        }).start();
    }
}
