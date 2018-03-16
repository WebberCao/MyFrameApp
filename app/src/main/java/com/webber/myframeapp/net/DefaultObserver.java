package com.webber.myframeapp.net;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.webber.myframeapp.R;
import com.webber.myframeapp.application.MyApplication;
import com.webber.myframeapp.bean.BaseResponse;
import com.webber.myframeapp.utils.DialogUtil;
import com.webber.myframeapp.utils.LogUtil;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Webber on 2018/3/6.
 * Describe : 封装的Observer
 */
public abstract class DefaultObserver<T extends BaseResponse> implements Observer<T> {

    private DialogUtil dialogUtil;

    public DefaultObserver(Activity activity){
        dialogUtil = new DialogUtil();
        dialogUtil.showProgress(activity);
    }

    public DefaultObserver(Activity activity, boolean isShowLoading){
        dialogUtil = new DialogUtil();
        if(isShowLoading){
            dialogUtil.showProgress(activity,"Loading...");
        }
    }

    @Override
    public void onNext(T response) {
        dismissProgressDialog();
        if(response.getCode()==200){
            onSuccess(response);
        }else {
            onFail(response);
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("Retrofit", e.getMessage());
        dismissProgressDialog();
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }

    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                Toast.makeText(MyApplication.getInstance(),R.string.connect_error, Toast.LENGTH_SHORT).show();
                break;

            case CONNECT_TIMEOUT:
                Toast.makeText(MyApplication.getInstance(), R.string.connect_timeout, Toast.LENGTH_SHORT).show();
                break;

            case BAD_NETWORK:
                Toast.makeText(MyApplication.getInstance(),R.string.bad_network, Toast.LENGTH_SHORT).show();
                break;

            case PARSE_ERROR:
                Toast.makeText(MyApplication.getInstance(),R.string.parse_error, Toast.LENGTH_SHORT).show();
                break;

            case UNKNOWN_ERROR:
            default:
                Toast.makeText(MyApplication.getInstance(),R.string.unknown_error, Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void dismissProgressDialog(){
        if(dialogUtil!=null){
            dialogUtil.dismissProgress();
        }
    }

    public void onFail(T response){
        String message = response.getMessage();
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(MyApplication.getInstance(), R.string.response_return_error, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MyApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 抽象方法，调用者自行实现请求数据成功之后的操作
     * @param response
     */
    public abstract void onSuccess(T response);


    /**0
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }

}
