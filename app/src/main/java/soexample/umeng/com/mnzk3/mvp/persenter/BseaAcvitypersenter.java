package soexample.umeng.com.mnzk3.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.mnzk3.R;
import soexample.umeng.com.mnzk3.mvp.view.AgeInter;


public abstract class BseaAcvitypersenter<T extends AgeInter>extends AppCompatActivity {

    public T delde;

    public abstract Class<T> getClassAgeid();

    public BseaAcvitypersenter() {
        try {
            delde = getClassAgeid().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delde.craete(getLayoutInflater(), null, savedInstanceState);
        setContentView(delde.rooView());
        delde.getContext(this);
        delde.initdata();



    }


}
