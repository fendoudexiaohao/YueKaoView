package songzhihao.bwei.com.yuekaoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者： 宋智豪
 * * 时间： 2017/3/9 20:45
 * * 描述： 跳转过后的页面包括刷新和加载
 */
public class HomeMainActivity extends AppCompatActivity {

    private XrecyclerViewAdapter xrecyclerViewAdapter;
    private XRecyclerView xRecyclerView;
    private List<Bean.ResultBean.RowsBean.InfoBean> lista;
    private String path = "http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0&qtime=20160411091603";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        //找控件
        lista = new ArrayList<>();
        xRecyclerView = (XRecyclerView) findViewById(R.id.XrecyclerView);
        //设置布局管理器
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        xRecyclerView.setLoadingMoreEnabled(true);
        //请求数据
        getData(1);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新的方法
            @Override
            public void onRefresh() {
                path = "http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0&qtime=20170211091603";
                getData(2);
            }
            //加载的方法
            @Override
            public void onLoadMore() {
                path = "http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0&qtime=20160611091603";
                getData(3);
            }
        });

    }

    private void getData(final int ll) {
        OkHttputils httpUtils = OkHttputils.getHttpUtils();
        httpUtils.loadDataFromNet(path,Bean.class, new OkHttputils.CallBackListener<Bean>() {
            //请求成功的方法
            @Override
            public void onSuccess(Bean result) {
                List<Bean.ResultBean.RowsBean> rows = result.getResult().getRows();
                if (ll == 1){
                    for (Bean.ResultBean.RowsBean aa:rows ){
                        lista.add(aa.getInfo());
                    }
                    xrecyclerViewAdapter = new XrecyclerViewAdapter(lista, HomeMainActivity.this);
                    xRecyclerView.setAdapter(xrecyclerViewAdapter);
                }
                if (ll == 2){
                    lista.clear();
                    for (Bean.ResultBean.RowsBean aa : rows) {
                        lista.add(aa.getInfo());
                    }
                    xRecyclerView.refreshComplete();
                    xrecyclerViewAdapter.notifyDataSetChanged();
                }
                if (ll == 3){for (Bean.ResultBean.RowsBean aa : rows) {
                    lista.add(aa.getInfo());
                }
                    xRecyclerView.loadMoreComplete();
                    xrecyclerViewAdapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onFail() {

            }
        });
    }
}
