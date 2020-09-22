package com.azsm.reeduacare.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.model.UserCourcesModel;
import com.azsm.reeduacare.view.activity.VideoDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserCourcesAdapter extends RecyclerView.Adapter<UserCourcesAdapter.ViewHolder> {


    Context context;
    List<UserCourcesModel> userCourcesModelList;
    String type;
    View view;
    public static String totalrating;
    public UserCourcesAdapter(Context context, List<UserCourcesModel> userCourcesModelList, String type) {
        this.context = context;
        this.userCourcesModelList = userCourcesModelList;
        this.type=type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (type.equals("2"))
        {
            view = LayoutInflater.from(context).inflate(R.layout.video_item_layout, parent, false);
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.user_cources_item_layout, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        if (type.equals("1")) {
            String islive = userCourcesModelList.get(position).getIslive();
            holder.coursecategorytv.setText(userCourcesModelList.get(position).getCoursetitle());
            holder.coursepricetv.setText("\u20B9"+userCourcesModelList.get(position).getCourseprice());
            String imgurl = userCourcesModelList.get(position).getCourseicon();


            Picasso.with(context).load("https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+imgurl).noPlaceholder().into(holder.dashimage);
          // holder.dashimage.setImageResource(R.drawable.foundation);
            if (islive.equals("2")) {
                holder.liveswitch.setChecked(true);
                holder.liveswitch.setTextOn("Live");
            } else {
                holder.liveswitch.setChecked(false);
                holder.liveswitch.setTextOn("Not Live");
            }

            holder.totalvideocount.setText(userCourcesModelList.get(position).getTotalvideo());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    Intent intent = new Intent(context, VideoListActivity.class);
//                    intent.putExtra("courseid", userCourcesModelList.get(position).getCourseid());
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
//                    ((Activity)context).finish();
                }
            });

            holder.liveswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (b) {
                       // doLiveOrNotLive(userCourcesModelList.get(position).getCourseid(), "1");
                        //  notifyDataSetChanged();
                    } else {
                       // doLiveOrNotLive(userCourcesModelList.get(position).getCourseid(), "0");
                        // notifyDataSetChanged();
                    }
                }
            });


//            holder.editview.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent=new Intent(context, CreateCourseFormActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("courseid",userCourcesModelList.get(position).getCourseid());
//                    intent.putExtra("c-title",userCourcesModelList.get(position).getCoursetitle());
//                    intent.putExtra("c-desc",userCourcesModelList.get(position).getCoursediscription());
//                    intent.putExtra("c-price",userCourcesModelList.get(position).getCourseprice());
//                    intent.putExtra("c-icon",userCourcesModelList.get(position).getCourseicon());
//                    intent.putExtra("type","edit");
//                    context.startActivity(intent);
//                    ((Activity)context).finish();
//
//                }
//            });

        }

        else {

            holder.courseratingbar.setIsIndicator(true);
            holder.coursepricetvv.setText("\u20B9" +userCourcesModelList.get(position).getCourseprice());
            Picasso.with(context).load("https://swasthyaayur.com/adwogindia.com/raushanedu/public/"+userCourcesModelList.get(position).getCourseicon()).noPlaceholder().into(holder.courseImageview);
            holder.coursetitletv.setText(userCourcesModelList.get(position).getCoursetitle());
            holder.courseratingbar.setRating(userCourcesModelList.get(position).getRating());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   // IncrementCourseViews(userCourcesModelList.get(position).getCourseid());

                    Intent intent=new Intent(context, VideoDetailsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("cimage",userCourcesModelList.get(position).getCourseicon());
                    intent.putExtra("ctitle",userCourcesModelList.get(position).getCoursetitle());
                    intent.putExtra("cdesc",userCourcesModelList.get(position).getCoursediscription());
                    intent.putExtra("cprice",userCourcesModelList.get(position).getCourseprice());
                    intent.putExtra("totalrating", String.valueOf(userCourcesModelList.get(position).getRating()));
                    intent.putExtra("courseid", userCourcesModelList.get(position).getCourseid());
                    intent.putExtra("creatername",userCourcesModelList.get(position).getCreatedbyname());
                    intent.putExtra("createremail",userCourcesModelList.get(position).getCreaedbyemail());
                    intent.putExtra("intenttype","1");
                    context.startActivity(intent);

                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return userCourcesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView coursecategorytv, coursepricetv,totalvideocount;
        Switch liveswitch;
        ImageView dashimage;
        ImageView courseImageview;
        TextView coursetitletv,coursepricetvv;
        RatingBar courseratingbar;
        View editview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            coursecategorytv = itemView.findViewById(R.id.coursename);
            coursepricetv = itemView.findViewById(R.id.coursepricee);
            liveswitch = itemView.findViewById(R.id.simpleSwitch);
            dashimage = itemView.findViewById(R.id.dash_img);
            totalvideocount=itemView.findViewById(R.id.totalvideocount);
            courseImageview=itemView.findViewById(R.id.courseimage);
            coursetitletv=itemView.findViewById(R.id.coursetitle);
            coursepricetvv=itemView.findViewById(R.id.coursepricee);
            courseratingbar=itemView.findViewById(R.id.rating);
            editview=itemView.findViewById(R.id.editcourseview);



        }
    }


//        private void doLiveOrNotLive(final String courseid, final String islive) {
//
//            BecomeInstructorDashboard.progressBar.setVisibility(View.VISIBLE);
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.ISLIVE_COURSE,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//
//                            try {
//
//                                JSONObject obj = new JSONObject(response);
//                                String status = obj.getString("status");
//
//                                if (status.equals("200")) {
//
//                                   // Toast.makeText(context, obj.getString("msg"), Toast.LENGTH_SHORT).show();
//                                    BecomeInstructorDashboard.progressBar.setVisibility(View.GONE);
//
//                                } else {
//
//                                   // Toast.makeText(context, obj.getString("msg"), Toast.LENGTH_SHORT).show();
//                                    BecomeInstructorDashboard.progressBar.setVisibility(View.GONE);
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Toast.makeText(context, "somrthing went wrong", Toast.LENGTH_SHORT).show();
//                                BecomeInstructorDashboard.progressBar.setVisibility(View.GONE);
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(context, "Server Not Responding", Toast.LENGTH_SHORT).show();
//                            BecomeInstructorDashboard.progressBar.setVisibility(View.GONE);
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("course_id", courseid);
//                    params.put("is_live", islive);
//                    return params;
//                }
//            };
//
//            VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
//        }
//
//
//
//        private void IncrementCourseViews(final String course_id)
//        {
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.INCREAMENT_VIEW,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//
//                            try {
//                                JSONObject obj = new JSONObject(response);
//                                String status = obj.getString("status");
//
//                             //   Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
//
//                                if (status.equals("200")) {
//
//
//
//
//                                } else {
//
//
//                                    Toast.makeText(context, obj.getString("msg"), Toast.LENGTH_SHORT).show();
//
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Toast.makeText(context, "somrthing went wrong", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(context, "Server Not Responding", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }) {
//
//                @Override
//                protected Map<String, String> getParams()  {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("course_id", course_id);
//                    return params;
//                }
//
//            };
//
//            VolleySingleton.getInstance(context).getRequestQueue().getCache().clear();
//            VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
//        }
//
//

}




