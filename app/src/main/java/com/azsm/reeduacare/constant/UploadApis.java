package com.azsm.reeduacare.constant;

import android.database.Observable;

import com.azsm.reeduacare.model.Example;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadApis {
    @Multipart
    @POST("gtc-form")
    Call<ResponseBody> updateProfile(@Part("name") String name,
                                           @Part("father_name") String father_name,
                                         @Part("school_name") String school_name,
                                        @Part("contact_number") String contact_number,
                                          @Part("class1") String class1,

                                     @Part("choose_membership") String choose_membership,
                                     @Part("user_id") String user_id,
                                     @Part("group_b") String group_b,
                                     @Part("group_c") String group_c,
                                     @Part("group_d") String group_d,
                                     @Part("group_e") String group_e,
                                     @Part("group_a_fee") String group_a_fee,
                                     @Part("group_b_fee") String group_b_fee,

                                     @Part("group_c_fee") String group_c_fee,
                                     @Part("group_d_fee") String group_d_fee,
                                     @Part("group_e_fee") String group_e_fee,
                                           @Part MultipartBody.Part image,
                                              @Part MultipartBody.Part sig_image);
    @Multipart
    @POST("gtc-form")
    Call<Example> doubtsection(@Part("user_id") String name,
                               @Part("dout_keyword") String father_name,
                               @Part MultipartBody.Part image);

}
