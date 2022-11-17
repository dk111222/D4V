package com.tvc.network.interactor;

import com.tvc.network.ServiceGenerator;
import com.tvc.network.api.DhtService;
import com.tvc.network.response.AdResponse;
import com.tvc.network.response.BaseResponse;
import com.tvc.network.response.DhtDetailResponse;
import com.tvc.network.response.DhtListResponse;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * token, 升级，广告相关接口
 */
public class DhtInteractor {
    private static DhtInteractor sInstance;

    public static DhtInteractor getInstance() {
        if (sInstance == null) {
            sInstance = new DhtInteractor();
        }
        return sInstance;
    }

    private DhtService appService = ServiceGenerator.createDhtService(DhtService.class);

    /**
     * 管理RxJava请求
     */
    protected CompositeDisposable compositeDisposable;

    private DhtInteractor() {
    }

    /**
     * 添加 rxJava 发出的请求
     */
    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void clearDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }

    public void resetService() {
        appService = ServiceGenerator.createDhtService(DhtService.class);
    }

    public Observable<DhtListResponse> btHashList(int pageNo, int pageSize) {
        return appService.btHashList(pageNo,pageSize);
    }

    public Observable<DhtDetailResponse> hashDetail(String id) {
        return appService.hashDetail(id);
    }

    public Observable<DhtListResponse> search(String keyword, int pageNo, int pageSize) {

        return appService.search(keyword,pageNo,pageSize);
    }

}
