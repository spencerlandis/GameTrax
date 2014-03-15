package unl.edu.cse.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import Data.Game;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<Game> games;
    private Activity activity;

    public ExpandableListAdapter(Context context, List<Game> listDataHeader, Activity activity)
    {
        this._context = context;
        this.games = listDataHeader;
        this.activity = activity;

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon)
    {
        return getGroupId(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {

        //get game, group is what the BaseExpandableListAdapter requires
        final Game game = (Game) getGroup(groupPosition);

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }
        //set description
        TextView txtListChild = (TextView) convertView.findViewById(R.id.description);

        txtListChild.setText(game.getDeck());

        //load image
        ImageView img = (ImageView) convertView.findViewById(R.id.gameImage);

        try
        {
            game.loadImage(img, this.activity);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Button btn = (Button) convertView.findViewById(R.id.link);
        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse(game.getSite_detail_url()));
                activity.startActivity(myWebLink);
            }
        });

        //set id so add will work
        ((CheckBox) convertView.findViewById(R.id.checkBox)).setClickable(false);

        btn = (Button) convertView.findViewById(R.id.addGame);

        if(!HomeActivity.getUser().hasGame(game.getId()))
        {
            ((CheckBox) convertView.findViewById(R.id.checkBox)).setChecked(false);
            btn.setText("Add Game");
            btn.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    HomeActivity.getUser().addGame(v);
                }
            });

        }
        else
        {
            ((CheckBox) convertView.findViewById(R.id.checkBox)).setChecked(true);
            btn.setText("Remove");
            btn.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    HomeActivity.getUser().removeGame(v);
                }
            });

        }



        return convertView;
    }

    //only singletons
    @Override
    public int getChildrenCount(int groupPosition)
    {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return this.games.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return this.games.size();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return this.games.get(groupPosition).getId();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        Game game = ((Game) getGroup(groupPosition));
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.gameName);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(game.getName());

        //trying to set image from url
        ImageView img = (ImageView) convertView.findViewById(R.id.smallImage);

        try
        {
            game.loadImage(img, this.activity);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }


}