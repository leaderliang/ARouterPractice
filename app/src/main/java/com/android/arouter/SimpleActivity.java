package com.android.arouter;

import android.os.Bundle;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = RouterPath.ACTIVITY_URL_SIMPLE)
public class SimpleActivity extends BaseActivity {

    @Autowired
    int age;

    @Autowired
    String name;

    /**
     * 只有当 @Autowired(name = "object")，也就是key标签一致的情况下，才可以获取到对象的值，如果不写标签名，结果会为null，
     * 所以为了规避每一个可能会遇到的风险，建议在@Autowired里面 都写上与之对应具体的 key 名
     */

    @Autowired(name = "object")
    User user;

    private TextView simpleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        simpleId = $(R.id.simple_id);
        simpleId.setText(age + "--" + name + "--" + " user-> " + user);

    }


}
