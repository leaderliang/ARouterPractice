package com.android.arouter.Interceptor;

import android.content.Context;
import android.util.Log;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.android.arouter.RouterPath;

/**
 * 1、使用Interceptor类注解的 priority 数值越小，越先执行，优先级越高。（四大组件中的广播，优先级的取值是 -1000到1000，数值越大优先级越高）
 * 2、如果两个拦截器的优先级一样，项目在编译时就会报错。所以，不同拦截器定义的优先级属性值不能相同
 *
 * @author liangyanqiao
 */
@Interceptor(priority = 2)
public class OtherIInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.i(RouterPath.TAG," OtherIInterceptor process "+" thread name:" + Thread.currentThread().getName());
        /*对 simple 页面进行拦截*/
        if(postcard.getPath().equals(RouterPath.ACTIVITY_URL_SIMPLE)){
            Log.i(RouterPath.TAG, " OtherIInterceptor process " + " 对页面" + RouterPath.ACTIVITY_URL_SIMPLE + " 进行了拦截");
        }
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        Log.i(RouterPath.TAG," OtherIInterceptor init ");
    }

}