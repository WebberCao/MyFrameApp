package com.webber.myframeapp.net;


import com.webber.myframeapp.bean.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Webber on 2018/3/7.
 * Describe : api接口服务类
 */

public interface ApiService {

    /**
     * 用户登录
     */
    @POST("api/user/login.do")
    Observable<BaseResponse> login(@QueryMap Map<String, String> map);


}
