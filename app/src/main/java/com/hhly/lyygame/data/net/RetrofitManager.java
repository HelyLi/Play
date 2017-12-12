package com.hhly.lyygame.data.net;

import android.util.SparseArray;

import com.hhly.lyygame.data.net.client.ExceptionHandle;
import com.hhly.lyygame.data.net.protocol.BaseResp;
import com.hhly.lyygame.domain.config.SystemConfig;
import com.hhly.lyygame.presentation.rxbus.RxBus;
import com.hhly.lyygame.presentation.rxbus.event.OtherEvent;
import com.hhly.lyygame.presentation.utils.JsonUtil;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eric on 2017/1/16.
 */

/**
 * 网络请求使用开源Retrofit okhttp 框架
 */
public class RetrofitManager {

    private BannerApi bannerApi;
    private FileApi fileApi;
    private GameApi gameApi;
    private GoodsApi goodsApi;
    private UpdateApi updateApi;
    private UserApi userApi;
    private PayApi mPayApi;
    private TransferApi mTransferApi;

    public BannerApi getBannerApi() {
        return bannerApi;
    }

    public FileApi getFileApi() {
        return fileApi;
    }

    public GameApi getGameApi() {
        return gameApi;
    }

    public GoodsApi getGoodsApi() {
        return goodsApi;
    }

    public UpdateApi getUpdateApi() {
        return updateApi;
    }

    public UserApi getUserApi() {
        return userApi;
    }

    public PayApi getPayApi() {
        return mPayApi;
    }

    public TransferApi getTransferApi(){
        return mTransferApi;
    }

    private void createApi(@ApiType.ApiTypeChecker int apiType) {
        switch (apiType) {
            case ApiType.BANNER_API:
                createBannerApi(sRetrofit);
                break;
            case ApiType.FILE_API:
                createFileApi(sFileRetrofit);
                break;
            case ApiType.GAME_API:
                createGameApi(sRetrofit);
                break;
            case ApiType.GOODS_API:
                createGoodsApi(sRetrofit);
                break;
            case ApiType.UPDATE_API:
                createUpdateApi(sRetrofit);
                break;
            case ApiType.USER_API:
                createUserApi(sRetrofit);
                break;
            case ApiType.PAY_API:
                createPayApi(sRetrofit);
                break;
            case ApiType.TRANSFER_API:
                createTransferApi(sRetrofit);
                break;
            default:
                break;
        }
    }

    private void createPayApi(Retrofit retrofit) {
        this.mPayApi = retrofit.create(PayApi.class);
    }

    private void createBannerApi(Retrofit retrofit) {
        this.bannerApi = retrofit.create(BannerApi.class);
    }

    private void createFileApi(Retrofit retrofit) {
        this.fileApi = retrofit.create(FileApi.class);
    }

    private void createGameApi(Retrofit retrofit) {
        this.gameApi = retrofit.create(GameApi.class);
    }

    private void createGoodsApi(Retrofit retrofit) {
        this.goodsApi = retrofit.create(GoodsApi.class);
    }

    private void createUpdateApi(Retrofit retrofit) {
        this.updateApi = retrofit.create(UpdateApi.class);
    }

    private void createUserApi(Retrofit retrofit) {
        this.userApi = retrofit.create(UserApi.class);
    }

    private void createTransferApi(Retrofit retrofit){
        this.mTransferApi = retrofit.create(TransferApi.class);
    }

    private static volatile OkHttpClient sOkHttpClient;

    private static SparseArray<RetrofitManager> sRetrofitManager = new SparseArray<>(ApiType.TYPE_COUNT);

    private static Retrofit sRetrofit;
    private static Retrofit sFileRetrofit;

    /**
     * @param apiType
     */
    public RetrofitManager(@ApiType.ApiTypeChecker int apiType) {

        if (apiType != ApiType.FILE_API && sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(SystemConfig.get().getHost(apiType))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if (apiType == ApiType.FILE_API && sFileRetrofit == null) {

            sFileRetrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(SystemConfig.get().getHost(apiType))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        createApi(apiType);
    }

    private static final int CONN_TIMEOUT = 15;
    private static final int READ_TIMEOUT = 20;
    private static final int WRITE_TIMEOUT = 20;

    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {

            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            sOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logInterceptor)
//                    .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getContext())))
                    .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
        return sOkHttpClient;
    }

    /**
     * @param apiType
     */
    public static RetrofitManager getInstance(int apiType) {
        RetrofitManager retrofitManager = sRetrofitManager.get(apiType);
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager(apiType);
            sRetrofitManager.put(apiType, retrofitManager);
            return retrofitManager;
        }
        return retrofitManager;
    }

    public static class HttpLogger implements HttpLoggingInterceptor.Logger {

        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.delete(0, mMessage.length());
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if ((message.trim().startsWith("{") && message.trim().endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))) {

                message = JsonUtil.json(message);
            }
            mMessage.append(message.concat("\n"));
            // 请求或者响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                Logger.d(mMessage.toString());
                mMessage.delete(0, mMessage.length());
            }
        }
    }

    public static final ObservableTransformer ERROR_TRANSFORMER =
            new ObservableTransformer() {
                @Override
                public ObservableSource apply(Observable upstream) {
                    return upstream.onErrorResumeNext(new HttpResponseFunc());
                }
            };

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> composeError() {
        return (ObservableTransformer<T, T>) ERROR_TRANSFORMER;
    }

    public static final FlowableTransformer ERROR_TRANSFORMER_BACKPRESSURE = new FlowableTransformer() {
        @Override
        public Publisher apply(Flowable upstream) {
            return upstream.onErrorResumeNext(new HttpResponseFuncBackpressure());
        }
    };

    public static final FlowableTransformer OTHER_TRANSFORMER_BACKPRESSURE = new FlowableTransformer() {
        @Override
        public Publisher apply(Flowable upstream) {
            return upstream.doAfterNext(new Consumer() {
                @Override
                public void accept(@NonNull Object o) throws Exception {
                    if (o instanceof BaseResp){
                        BaseResp resp = (BaseResp) o;
                        if (resp.getResult() == 401){
                            Logger.d("other 异端");
                            RxBus.get().post(new OtherEvent(OtherEvent.OTHER_401_TYPE));
                        }else if (resp.getResult() == 402){
                            Logger.d("other 异地");
                            RxBus.get().post(new OtherEvent(OtherEvent.OTHER_402_TYPE));
                        }
                    }
                }
            });
        }
    };

    public static final SingleTransformer ERROR_TRANSFORMER_SINGLE = new SingleTransformer() {
        @Override
        public SingleSource apply(Single upstream) {
            return upstream.onErrorResumeNext(new HttpResponseFuncSingle());
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> composeBackpressureError() {
        return (FlowableTransformer<T, T>) ERROR_TRANSFORMER_BACKPRESSURE;
    }

    public static <T> FlowableTransformer<T, T> composeBackpressureOther() {
        return (FlowableTransformer<T, T>) OTHER_TRANSFORMER_BACKPRESSURE;
    }

    @SuppressWarnings("unchecked")
    public static <T> SingleTransformer<T, T> composeSingleError() {
        return (SingleTransformer<T, T>) ERROR_TRANSFORMER_SINGLE;
    }

    public static class HttpResponseFunc implements Function<Throwable, Observable> {
        @Override
        public Observable apply(Throwable throwable) throws Exception {

            return Observable.error(ExceptionHandle.handleException(throwable));
        }
    }

    public static class HttpResponseFuncBackpressure implements Function<Throwable, Flowable> {
        @Override
        public Flowable apply(@NonNull Throwable throwable) throws Exception {
            return Flowable.error(ExceptionHandle.handleException(throwable));
        }
    }

    public static class HttpResponseFuncSingle implements Function<Throwable, Single> {
        @Override
        public Single apply(@NonNull Throwable throwable) throws Exception {
            return Single.error(ExceptionHandle.handleException(throwable));
        }
    }

}