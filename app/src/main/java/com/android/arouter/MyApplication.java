package com.android.arouter;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;

public class MyApplication extends Application {

    private boolean isDebugARouter = true;

    @Override
    public void onCreate() {
        super.onCreate();

        if(isDebugARouter){
            /*下面两行必须写在init之前，否则这些配置在init过程中将无效*/
            ARouter.openLog();
            /*开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)*/
            ARouter.openDebug();
        }
        /*官方建议推荐在Application中初始化*/
        ARouter.init(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}