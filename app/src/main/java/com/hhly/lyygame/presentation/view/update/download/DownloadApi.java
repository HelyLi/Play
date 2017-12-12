package com.hhly.lyygame.presentation.view.update.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 应用名称: BaseProject
 * 包 名 称: com.classic.android.http.download
 *
 * 文件描述: 下载接口
 */
public interface DownloadApi {

    @GET @Streaming Observable<ResponseBody> download(@Url String url);
}
