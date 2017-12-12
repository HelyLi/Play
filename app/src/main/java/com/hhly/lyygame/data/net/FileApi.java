package com.hhly.lyygame.data.net;

import com.hhly.lyygame.data.net.protocol.file.ImageUploadResp;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by ${HELY} on 17/1/3.
 * 邮箱：heli.lixiong@gmail.com
 */

public interface FileApi {

    /**
     * 文件上传
     * @param
     * @return
     */
    @Multipart
    @POST("uploadImage.do")
    Flowable<ImageUploadResp> uploadImage(@Part MultipartBody.Part part);

}
