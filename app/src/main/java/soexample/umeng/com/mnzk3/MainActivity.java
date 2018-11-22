package soexample.umeng.com.mnzk3;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.mnzk3.mvp.persenter.BseaAcvitypersenter;

public class MainActivity extends BseaAcvitypersenter<MainActivitypersenter> {


    @Override
    public Class<MainActivitypersenter> getClassAgeid() {
        return MainActivitypersenter.class;
    }

    @BindView(R.id.ed)
    EditText ed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delde.oned();
            }
        });
    }
}
