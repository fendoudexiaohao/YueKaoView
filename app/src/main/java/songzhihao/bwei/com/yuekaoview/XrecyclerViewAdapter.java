package songzhihao.bwei.com.yuekaoview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 作者： 宋智豪
 * * 时间： 2017/3/9 20:31
 * * 描述： 尚未编写描述
 */

public class XrecyclerViewAdapter extends XRecyclerView.Adapter<XrecyclerViewAdapter.MyViewHolder>{

    private List<Bean.ResultBean.RowsBean.InfoBean> lista;
    private Context context;
    public XrecyclerViewAdapter(List<Bean.ResultBean.RowsBean.InfoBean> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.adapteritem, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.text1.setText(lista.get(position).getAddress());
        holder.text2.setText(lista.get(position).getLoupan_name());
        holder.text3.setText(lista.get(position).getTags());
        holder.text4.setText(lista.get(position).getPrice()+"");
        Glide.with(context).load(lista.get(position).getDefault_image()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView text1, text2, text3, text4;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imager);
            text1 = (TextView) itemView.findViewById(R.id.text1);
            text2 = (TextView) itemView.findViewById(R.id.text2);
            text3 = (TextView) itemView.findViewById(R.id.text3);
            text4 = (TextView) itemView.findViewById(R.id.text4);
        }
    }
}
