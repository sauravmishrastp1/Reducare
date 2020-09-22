package com.azsm.reeduacare.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.azsm.reeduacare.R;
import com.azsm.reeduacare.model.CourceWiseModel;
import com.azsm.reeduacare.view.activity.VideoDetailsActivity;
import com.razorpay.Checkout;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

public class InternalCourceAdapter extends RecyclerView.Adapter<InternalCourceAdapter.ViewHolder> {
    public InternalCourceAdapter(ArrayList<CourceWiseModel> provideCourceModelClasses, Context context) {
        this.provideCourceModelClasses = provideCourceModelClasses;
        this.context = context;
    }

    private ArrayList<CourceWiseModel> provideCourceModelClasses;

    private Context context;


    @NonNull
    @Override
    public InternalCourceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.internalcourcecat, parent, false);
        return new InternalCourceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InternalCourceAdapter.ViewHolder holder, int position) {
      int img = provideCourceModelClasses.get(position).getCourceimg();
      String name = provideCourceModelClasses.get(position).getCourcebname();
        Picasso.with(context).load(provideCourceModelClasses.get(position).getCourceimg()).fit().centerCrop()
                .placeholder(R.drawable.placeholder);
        holder.courceimg.setImageResource(img);

      holder.courcename.setText(name);
//      holder.itemView.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              Intent intent=new Intent(context, CourceWiseVedio.class);
//              context.startActivity(intent);
//          }
//      });
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(context, VideoDetailsActivity.class);
              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              intent.putExtra("courseid","1");
              context.startActivity(intent);

          }

      });
    }

    @Override
    public int getItemCount() {
        return provideCourceModelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView courceimg;
        TextView courcename;
        Button buy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courceimg = itemView.findViewById(R.id.imgview);
            courcename = itemView.findViewById(R.id.courcename);
            buy = itemView.findViewById(R.id.buyycource);
        }
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Context activity = context;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "100");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "956018681");

            options.put("prefill", preFill);

          // co.open(options);
        } catch (Exception e) {
            Toast.makeText(context, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    //@Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(context, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")

    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(context, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Log.e(TAG, "Exception in onPaymentError", e);
        }
    }



}
