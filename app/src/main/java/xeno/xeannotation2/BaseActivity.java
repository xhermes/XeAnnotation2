package xeno.xeannotation2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

import xeno.xeannotation2.annotation.Find;


/**
 * Created by Administrator on 2018/7/13.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        findViews();

    }

    public abstract int getContentViewId();

    public void findViews() {
        Log.i("xeno", "findViews调用");
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field f : fields) {
            Log.i("xeno", "获取到的变量：" + f.getName());
            if(f.isAnnotationPresent(Find.class)) {
                if(!View.class.isAssignableFrom(f.getType())) {
                    Log.i("xeno", "不是View的子类，跳出");
                    continue;
                }

                f.setAccessible(true);

                Find fAnnotation = f.getAnnotation(Find.class);

                if(fAnnotation.value() == Find.DEFAULT) {
                    Log.i("xeno", "未赋值value，直接用变量名寻找");
                    String name = f.getName();
                    String idName = null;
                    //严格按照命名规则，m+业务名+View/Btn/Layout
                   //TODO 命名->id转变规则
                    idName = name;
                    try {
                        f.set(BaseActivity.this, findViewById(R.id.class.getField(idName).getInt(new R.id())));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }

            }
        }
    }

}
