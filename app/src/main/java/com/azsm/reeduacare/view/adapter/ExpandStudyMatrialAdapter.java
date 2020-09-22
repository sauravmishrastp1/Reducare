package com.azsm.reeduacare.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.azsm.reeduacare.R;
import com.azsm.reeduacare.model.Menu_model_study_matrial;
import com.azsm.reeduacare.view.activity.Pdf_Activity;
import com.azsm.reeduacare.view.activity.Vedio_palyer_Activity;

import java.util.HashMap;
import java.util.List;

public class ExpandStudyMatrialAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Menu_model_study_matrial> listDataHeader;
    private HashMap<Menu_model_study_matrial, List<Menu_model_study_matrial>> listDataChild;

    public ExpandStudyMatrialAdapter(Context context, List<Menu_model_study_matrial> listDataHeader, HashMap<Menu_model_study_matrial, List<Menu_model_study_matrial>> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    @Override
    public Menu_model_study_matrial getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, final ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).menuName;
        final  int ico2 = getChild(groupPosition,childPosition).PDF;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.chid_list_layout2, null);
        }

        TextView txtListChild = convertView
                .findViewById(R.id.lblListItem);
        TextView textView3 = convertView.findViewById(R.id.lblListItem2);
        textView3.setText(childText);
        View v = convertView.findViewById(R.id.layou2);
        v.findViewById(R.id.layou2);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Pdf_Activity.class);
                intent.putExtra("url",getChild(groupPosition,childPosition).url);
                context.startActivity(intent);
            }
        });
        ImageView imageView = convertView.findViewById(R.id.imageview);
        imageView.setImageResource(ico2);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Vedio_palyer_Activity.class);
                intent.putExtra("url",getChild(groupPosition,childPosition).isGroup);
                 context.startActivity(intent);
            }
        });


        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null)
            return 0;
        else
            return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                    .size();
    }

    @Override
    public Menu_model_study_matrial getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).menuName;
        int iconimg = getGroup(groupPosition).PDF;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_header_layout2, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        ImageView icon = convertView.findViewById(R.id.imageview);
        icon.setImageResource(iconimg);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

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
