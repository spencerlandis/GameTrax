package unl.edu.cse.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import Data.Game;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<Game> _listDataHeader;
    private Activity activity;

    public ExpandableListAdapter(Context context, List<Game> listDataHeader, Activity activity) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this.activity = activity;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return getGroupId(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Game game = (Game) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.description);

        txtListChild.setText(game.getDeck());

        ImageView img = (ImageView) convertView.findViewById(R.id.gameImage);

        try {
            game.loadImage(img, this.activity);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return this._listDataHeader.get(groupPosition).getId();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Game game = ((Game) getGroup(groupPosition));
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.gameName);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(game.getName());

        //trying to set image from url
        ImageView img = (ImageView) convertView.findViewById(R.id.smallImage);

        try {
            game.loadImage(img, this.activity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}