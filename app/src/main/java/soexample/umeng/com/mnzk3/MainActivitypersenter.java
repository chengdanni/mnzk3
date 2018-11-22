package soexample.umeng.com.mnzk3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.mnzk3.gouwuc.GouwucheAdapter;
import soexample.umeng.com.mnzk3.bean.GouwucheBean;
import soexample.umeng.com.mnzk3.liushi.Fedui;
import soexample.umeng.com.mnzk3.mvp.view.AgeInter;
import soexample.umeng.com.mnzk3.net.HttpHelper;

public class MainActivitypersenter extends AgeInter implements View.OnClickListener{
    private String url = "http://www.zhaoapi.cn/product/getCarts?uid=71";
    private ImageView ivCricle;
    private TextView allPriceTxt;
    private TextView sunPrice;
    private List<GouwucheBean.DataBean> list = new ArrayList<>();
    private GouwucheAdapter mShopSellerAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initdata() {
        super.initdata();

        mRecyclerView = (RecyclerView) get(R.id.layout_top1);
        ivCricle = (ImageView) get(R.id.iv_cricle);
        allPriceTxt = (TextView) get(R.id.all_price);
        sunPrice = (TextView) get(R.id.sum_price_txt);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mShopSellerAdapter = new GouwucheAdapter(context);
        mRecyclerView.setAdapter(mShopSellerAdapter);
        odhttp();

        get(R.id.layout_all).setOnClickListener(this);
        mShopSellerAdapter.setListener(new GouwucheAdapter.ShopCallBackListener() {
            @Override
            public void callBack(List<GouwucheBean.DataBean> list) {
                double allPrice = 0;
                int num = 0;
                int numAll = 0;
                for (int a = 0; a < list.size(); a++) {
                    List<GouwucheBean.DataBean.ListBean> listAll = list.get(a).getList();//获取商家里商品
                    for (int i = 0; i < listAll.size(); i++) {
                        numAll = numAll + listAll.get(i).getNum();
                        if (listAll.get(i).isCheck()) {//取选中的状态
                            allPrice = allPrice + (listAll.get(i).getPrice() * listAll.get(i).getNum());
                            num = num + listAll.get(i).getNum();
                        }
                    }
                }
                if (num < numAll) {//不是全部选中
                    ivCricle.setImageResource(R.drawable.cricle_no);
                    isClick = true;
                } else {
                    //是全部选中
                    ivCricle.setImageResource(R.drawable.cricle_yes);
                    isClick = false;

                }
                //改变价格和个数
                allPriceTxt.setText("合计：" + allPrice);
                sunPrice.setText("去结算(" + num + ")");
            }
        });
    }

    private void odhttp() {
        new HttpHelper().get(url).result(new HttpHelper.HttpListener() {
            @Override
            public void success(String data) {
                if(data.contains("<")){
                    odhttp();
                    return;
                }
                GouwucheBean bean = new Gson().fromJson(data, GouwucheBean.class);
                list = bean.getData();
                list.remove(0);
                mShopSellerAdapter.setList(list);
            }

            @Override
            public void fail(String error) {

            }
        });
    }
    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }
    private boolean isClick;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_all://全选
                if (isClick) {
                    ivCricle.setImageResource(R.drawable.cricle_yes);
                    isClick = false;
                    checkSeller(true);
                } else {
                    ivCricle.setImageResource(R.drawable.cricle_no);
                    isClick = true;
                    checkSeller(false);
                }
                break;
        }
    }
    private void checkSeller(boolean bool){
        double allPrice=0;
        int num=0;
        for (int a=0;a<list.size();a++){
            List<GouwucheBean.DataBean.ListBean> listAll = list.get(a).getList();
            for (int i = 0; i <listAll.size() ; i++) {
                listAll.get(i).setCheck(bool);
                allPrice=allPrice+(listAll.get(i).getPrice()*listAll.get(i).getNum());
                num=num+listAll.get(i).getNum();
            }
        }
        if(bool){
            allPriceTxt.setText("合计："+allPrice);
            sunPrice.setText("去结算("+num+")");
        }else{
            allPriceTxt.setText("合计：0.00");
            sunPrice.setText("去结算(0)");
        }
        mShopSellerAdapter.notifyDataSetChanged();
    }

    //搜索Butnf
    @Override
    public void oned() {
        super.oned();
        context.startActivity(new Intent(context,Fedui.class));
    }


//    @Override
//    public void onimg(View view) {
//        super.onimg(view);
//        ivCricle = (ImageView) view;
//
//        }

}


