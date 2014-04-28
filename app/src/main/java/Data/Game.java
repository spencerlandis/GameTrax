package Data;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by spencerlandis on 3/8/14.
 */
public class Game
{
    private long game_id;
    private String name;
    private String deck;
    private String site_detail_url;
    private Image image;

    public Game(long id, String name, String deck, String site_detail_url, Image image)
    {
        this.game_id = id;
        this.name = name;
        this.deck = deck;
        this.site_detail_url = site_detail_url;
        this.image = image;
    }

    public long getId()
    {
        return game_id;
    }

    public void setId(long id)
    {
        this.game_id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDeck()
    {
        return deck;
    }

    public void setDeck(String deck)
    {
        this.deck = deck;
    }

    public String getSite_detail_url()
    {
        return site_detail_url;
    }

    public void setSite_detail_url(String site_detail_url)
    {
        this.site_detail_url = site_detail_url;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public void loadImage(final ImageView img, final Activity activity)
    {
        Log.d("loading image", "made it here?");
        //makes sure image isn't null
        if(this.getImage() == null)
        {
            return;
        }
        //starts thread to load image
        new Thread(new Runnable()
        {
            public void run()
            {

                final Bitmap x;

                HttpURLConnection connection = null;
                try
                {
                    connection = (HttpURLConnection) new URL(image.getIconUrl().replace("\\", "")).openConnection();
                    Log.d("Game", connection.toString());
                }
                catch (Exception e)
                {

                    Log.d("Game", "5");
                }
                try
                {
                    connection.connect();
                }
                catch (Exception e) {
                    Log.d("Game", e.toString());

                }
                InputStream input = null;
                try
                {
                    input = connection.getInputStream();x = BitmapFactory.decodeStream(input);

                    activity.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            img.setImageBitmap(x);
                        }
                    });
                }
                catch (Exception e)
                {
                    Log.d("Game", "1");
                }
                connection.disconnect();
                try
                {
                    input.close();
                }
                catch (Exception e)
                {

                    Log.d("Game", "2");
                }

            }
        }).start();
    }
}
