package soexample.umeng.com.mnzk3.gouwuc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.mnzk3.R;
import soexample.umeng.com.mnzk3.bean.GouwucheBean;

public class ShopCarPriceLayout extends RelativeLayout implements View.OnClickListener {

    private EditText editCar;

    public ShopCarPriceLayout(Context context, List<GouwucheBean.DataBean.ListBean> list) {
        super(context);
        init(context);
    }

    public ShopCarPriceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShopCarPriceLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.jia, null);
        ImageView addIamge = (ImageView) view.findViewById(R.id.add_car);
        ImageView jianIamge = (ImageView) view.findViewById(R.id.jian_car);
        editCar = (EditText) view.findViewById(R.id.edit_shop_car);
        addIamge.setOnClickListener(this);
        jianIamge.setOnClickListener(this);
        addView(view);
    }

    private int num;
    private Context context;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_car://增加
                num++;
                editCar.setText(num + "");
                list.get(position).setNum(num);
                listener.callBack();
                shopSellerCarAdapter.notifyItemChanged(position);
                break;
            case R.id.jian_car://减
                if (num > 1) {
                    num--;
                } else {
                    toast("我是有底线的啊");
                }
                editCar.setText(num + "");
                list.get(position).setNum(num);
                listener.callBack();
                shopSellerCarAdapter.notifyItemChanged(position);
                break;

        }

    }

    private void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    //传递的数据
    private List<GouwucheBean.DataBean.ListBean> list = new ArrayList<>();
    private int position;
    private GouwuShop shopSellerCarAdapter;

    public void setData(GouwuShop shopSellerCarAdapter, List<GouwucheBean.DataBean.ListBean> list, int i) {
        this.list = list;
        this.shopSellerCarAdapter = shopSellerCarAdapter;
        position = i;
        num = list.get(i).getNum();
        editCar.setText(num + "");
    }


    private CallBackListener listener;

    public void setOnCallBack(CallBackListener listener) {
        this.listener = listener;
    }

    public interface CallBackListener {
        void callBack();
    }
}
