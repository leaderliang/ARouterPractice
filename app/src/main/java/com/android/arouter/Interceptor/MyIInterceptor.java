package com.android.arouter.Interceptor;

import android.content.Context;
import android.util.Log;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.android.arouter.RouterPath;

/**
 * 拦截器是ARouter这一款框架的亮点。说起拦截器这个概念，可能印象更加深刻的是OkHttp的拦截器，
 * OkHttp的拦截器主要是用来拦截请求体（比如添加请求Cookie）和拦截响应体（判断Token是否过期），
 * 在真正的请求和响应前做一些判断和修改然后在去进行操作，大抵这就是拦截器的简单概念。
 *
 * ARouter的拦截器，是通过实现 IInterceptor接口，重写init（）和process（）方法去完成拦截器内部操作的。
 *
 * @author liangyanqiao
 */
@Interceptor(priority = 1)
public class MyIInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.i(RouterPath.TAG," MyIInterceptor process "+" thread name:" + Thread.currentThread().getName());
        /*对 simple 页面进行拦截*/
        if(postcard.getPath().equals(RouterPath.ACTIVITY_URL_SIMPLE)){
            Log.i(RouterPath.TAG, " MyIInterceptor process " + " 对页面" + RouterPath.ACTIVITY_URL_SIMPLE + " 进行了拦截");
        }
        /*让继续执行*/
        callback.onContinue(postcard);
        /*截断并处理*/
//        callback.onInterrupt(exception);
    }

    @Override
    public void init(Context context) {
        Log.i(RouterPath.TAG," MyIInterceptor init ");
    }

}