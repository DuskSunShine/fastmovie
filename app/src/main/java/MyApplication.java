import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by SCY on 2016/12/27 10:41.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        SDKInitializer.initialize(getApplicationContext());
    }
}
