package com.opensource.qiaop.baseadapter;

import android.content.Context;
import android.widget.TextView;

import com.opensource.qiaop.baseadapter.adapter.BaseRecyclerAdapter;
import com.opensource.qiaop.baseadapter.adapter.BaseViewHolder;
import com.opensource.qiaop.baseadapter.bean.News;

import java.util.List;

/**
 * Created by qiaopeng0809@gmail.com on 2017/11/15.
 */

public class MyAdapter extends BaseRecyclerAdapter<News> {
    /**
     * Construction method
     *
     * @param mContext
     * @param mList
     * @param itemLayoutId
     */
    public MyAdapter(Context mContext, List<News> mList, int itemLayoutId) {
        super(mContext, mList, itemLayoutId);
    }

    @Override
    public void onBind(BaseViewHolder holder, News news, int position) {
        TextView title = holder.getView(R.id.title);
        TextView content = holder.getView(R.id.content);
        title.setText(news.getTitle());
        content.setText(news.getContent());
    }
}
