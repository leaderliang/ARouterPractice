package com.android.arouter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * ARouter 基本操作回顾
 *
 * Postcard 一个包含路线图的容器
 *
 * 自定义分组
 * 自定义分组来跳转界面,需要修改三处代码即可：
 * 1、类注解新增 group，赋值我们自定义的组名 @Route(path = RouterPath.ACTIVITY_URL_MAIN, group = RouterPath.DEFINE_GROUP)
 * 2、在build方法里面（这是一个方法重载），添加我们的与之对应的组名
 * 3、在被跳转的Activity里面的类注释，加上同样的组名
 *
 *
 * onActivityResult
 * 需要在跳转的navigation方法（这是一个方法重载）里面的第二个参数，设置定义的 requestCode
 *
 * @author dev.liang
 */
@Route(path = RouterPath.ACTIVITY_URL_MAIN, group = RouterPath.DEFINE_GROUP)
public class MainActivity extends BaseActivity {

    private final int REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_id).setOnClickListener(v -> {
//                ARouter.getInstance().build(RouterPath.ACTIVITY_URL_SIMPLE).navigation();
            /*ARouter 框架也可以使用 URI 进行匹配跳转，或者 String*/
            /*Uri uri = Uri.parse(RouterPath.ACTIVITY_URL_SIMPLE);
            ARouter.getInstance().build(uri)*/
            ARouter.getInstance().build(RouterPath.ACTIVITY_URL_SIMPLE, RouterPath.DEFINE_GROUP)
                    .withInt("age", 2)
                    .withString("name", "liang")
                    .withParcelable("object", new User("wang", 20))
                    //界面跳转动画
//                      .withTransition(int enterAnim, int exitAnim).navigation();
                    .navigation(this, REQUEST_CODE, new NavigationCallback() {
                        @Override
                        public void onFound(Postcard postcard) {
                        /*路由目标被发现时调用*/
                            Log.e(RouterPath.TAG," onFound");
                            String path = postcard.getPath();
                            String group = postcard.getGroup();
                            Log.e(RouterPath.TAG, " onFound" + " path == " + path + " group == " + group);
                        }

                        @Override
                        public void onLost(Postcard postcard) {
                            /*路由被丢失调用*/
                            Log.e(RouterPath.TAG," onLost");

                            String path = postcard.getPath();
                            String group = postcard.getGroup();
                            Log.e(RouterPath.TAG, " onLost" + " path == " + path + " group == " + group);
                        }

                        @Override
                        public void onArrival(Postcard postcard) {
                            /*路由到达之后调用*/
                            Log.e(RouterPath.TAG," onArrival");

                            String path = postcard.getPath();
                            String group = postcard.getGroup();
                            Log.e(RouterPath.TAG, " onArrival" + " path == " + path + " group == " + group);
                        }

                        @Override
                        public void onInterrupt(Postcard postcard) {
                            /*路由被拦截时调用*/
                            Log.e(RouterPath.TAG," onInterrupt");

                            String path = postcard.getPath();
                            String group = postcard.getGroup();
                            Log.e(RouterPath.TAG, " onInterrupt" + " path == " + path + " group == " + group);


                        }
                    });
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE:
                Log.e(RouterPath.TAG, " onActivityResult" + " requestCode == " + requestCode + " resultCode == " + resultCode);
                break;
                default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  在这里写会报错，initException
//        ARouter.getInstance().destroy();
    }
}
