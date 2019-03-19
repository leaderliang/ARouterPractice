package com.android.arouter;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * ARouter 基本操作回顾
 * @author liangyanqiao
 */
@Route(path = RouterPath.ACTIVITY_URL_MAIN)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouter.getInstance().build(RouterPath.ACTIVITY_URL_SIMPLE).navigation();

                ARouter.getInstance().build(RouterPath.ACTIVITY_URL_SIMPLE)
                        .withInt("age",2)
                        .withString("name","liang")
                        .withParcelable("object", new User("wang",20))
                        .navigation();
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  在这里写会报错，initException
//        ARouter.getInstance().destroy();
    }
}
