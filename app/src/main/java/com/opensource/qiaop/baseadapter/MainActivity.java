package com.opensource.qiaop.baseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.opensource.qiaop.baseadapter.adapter.BaseRecyclerAdapter;
import com.opensource.qiaop.baseadapter.bean.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseRecyclerAdapter.OnEmptyViewClickListener, BaseRecyclerAdapter.OnItemClickListener {

    private List<News> mList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                initData();
                setHeaderView();
                setFooterView();
                adapter.notifyDataSetChanged();
                break;
            case R.id.clear:
                if (mList.size()>0){
                    mList.clear();
                    adapter.setFooterView(null);
                    adapter.setHeaderView(null);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData(){
        for (int i = 0; i < 10; i++) {
            mList.add(new News("朝鲜示威","朝鲜半岛演练美国三航母联手示威"));
        }
    }

    private void initView(){
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this,mList,R.layout.item_layout);
        setHeaderView();
        setFooterView();
        setEmptyView();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }

    /**
     * 设置HeaderView,任何View都可以，这里只是简单的放了一张图片,可以自己通过Inflate创建View
     */
    private void setHeaderView(){
        ImageView headerImg = new ImageView(this);
        headerImg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        headerImg.setImageDrawable(getResources().getDrawable(R.drawable.header));
        adapter.setHeaderView(headerImg);
    }

    /**
     * 设置FooterView,任何View都可以，这里只是简单的放了一张图片,可以自己通过Inflate创建View
     */
    private void setFooterView(){
        ImageView footerImg = new ImageView(this);
        footerImg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        footerImg.setImageDrawable(getResources().getDrawable(R.drawable.footer));
        adapter.setFooterView(footerImg);
    }

    /**
     * 设置EmptyView,任何View都可以，这里只是简单的放了一张图片,可以自己通过Inflate创建View
     * 并添加点击事件
     */
    private void setEmptyView(){
        ImageView emptyImg = new ImageView(this);
        emptyImg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        emptyImg.setImageDrawable(getResources().getDrawable(R.drawable.empty));
        adapter.setEmptyView(emptyImg);
        adapter.setOnEmptyViewClickListener(this);
    }

    @Override
    public void onEmptyViewClick() {
        Toast.makeText(MainActivity.this,"点击图片",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(MainActivity.this,"点击item  "+position,Toast.LENGTH_SHORT).show();
    }
}
