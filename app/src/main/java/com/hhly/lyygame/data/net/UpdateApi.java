package com.hhly.lyygame.data.net;

import com.hhly.lyygame.data.net.protocol.update.ADInfoResp;
import com.hhly.lyygame.data.net.protocol.update.CheckUpdateResp;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ${HELY} on 17/2/20.
 * 邮箱：heli.lixiong@gmail.com
 */

/**
 * w网络请求接口
 */
public interface UpdateApi {

    /**
     * 应用更新
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("app/checkUpdate")
    Flowable<CheckUpdateResp> getUpdateInfo(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取广告图
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("app/getAdPic")
    Flowable<ADInfoResp> getADInfo(@FieldMap(encoded = true) Map<String, String> params);

}
