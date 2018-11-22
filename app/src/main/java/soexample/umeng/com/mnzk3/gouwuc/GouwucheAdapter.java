package soexample.umeng.com.mnzk3.gouwuc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.mnzk3.R;
import soexample.umeng.com.mnzk3.bean.GouwucheBean;
import soexample.umeng.com.mnzk3.liushi.Fedui;

public class GouwucheAdapter extends RecyclerView.Adapter<GouwucheAdapter.sgouwuche> {
    private List<GouwucheBean.DataBean> list = new ArrayList<>();
    private Context context;

    public GouwucheAdapter(Context context) {
        this.context = context;
    }

    @Override
    public sgouwuche onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.gouwu, null);
        sgouwuche sgouwuche = new sgouwuche(view);
        return sgouwuche;
    }

    @Override
    public void onBindViewHolder(sgouwuche holder, int position) {
        holder.sellerName.setText(list.get(position).getSellerName());//设置商家的名字
        GouwuShop gouwuche = new GouwuShop(list.get(position).getList(),context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.mRecyclerView.setLayoutManager(linearLayoutManager);
        holder.mRecyclerView.setAdapter(gouwuche);
       holder.mRecyclerView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               context.startActivity(new Intent(context,Fedui.class));
           }
       });
        gouwuche.setListener(new GouwuShop.ShopCallBackListener() {
            @Override
            public void callBack() {
                //从商品适配里回调回来
                listener.callBack(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<GouwucheBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class sgouwuche extends RecyclerView.ViewHolder {

        private final TextView sellerName;
        private final RecyclerView mRecyclerView;

        public sgouwuche(View itemView) {
            super(itemView);
             sellerName = (TextView) itemView.findViewById(R.id.seller_name);
             mRecyclerView = (RecyclerView) itemView.findViewById(R.id.seller_recyclerview);

        }
    }


    private ShopCallBackListener listener;

    public void setListener(ShopCallBackListener listener) {
        this.listener = listener;
    }

    public interface ShopCallBackListener {
        void callBack(List<GouwucheBean.DataBean> list);
    }

}
