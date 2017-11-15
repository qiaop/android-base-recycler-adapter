# android-base-recycler-adapter
**RecyclerAdapter通用封装**
RecyclerView虽然很强大，但是在实际的生产使用中，我们往往要写很多重复的代码来完成同样的功能。很多功能都需要在适配器中去完成，例如添加点击事件等等，因此作者旨在封装一个功能完备，使用方便的通用适配器。
## 完成功能
- 可添加Header
- 可添加Footer
- 可添加EmptyView（列表空白占位）
- 可添加EmptyView点击事件
- 可添加点击事件
- 绑定数据直接拿Bean
<!-- more -->
## 实现思路
通过不同的ItemViewType，创建不同的ViewHolder。
例如，如果添加了HeaderView，那么就创建HeaderView的ViewHolder。
### 核心方法

```
   @Override
    public int getItemViewType(int position) {//判断顺序固定
        if (position == 0 && mHeaderView != null) return TYPE_HEADER;
        if (mEmptyView != null && mList.size() == 0) return TYPE_EMPTY;
        if (mHeaderView == null && mFooterView == null) return TYPE_NORMAL;
        if (mFooterView != null && position == getItemCount() - 1) return TYPE_FOOTER;
        return TYPE_NORMAL;
    }

```


```
@Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType){
            case TYPE_EMPTY:
                itemView = mEmptyView;
                break;
            case TYPE_HEADER:
                itemView = mHeaderView;
                break;
            case TYPE_FOOTER:
                itemView = mFooterView;
                break;
            case TYPE_NORMAL:
                itemView = LayoutInflater.from(mContext).inflate(itemLayoutId,parent,false);
                break;
            default:
                itemView = mEmptyView;
                break;
        }
        itemView.setOnClickListener(this);
        return BaseViewHolder.getHolder(itemView,viewType);
    }

```

## 如何使用

直接继承`BaseRecyclerAdapter`。

1. 添加构造方法
2. 实现`onBind`方法。

示例：

```
public class MyRecyclerAdapter extends BaseRecyclerAdapter<Bean> {

    public MyRecyclerAdapter(Context mContext, List<Bean> mList, int itemLayoutId) {
        super(mContext, mList, itemLayoutId);
    }

    @Override
    public void onBind(BaseViewHolder holder, Bean bean, int position) {
        TextView title = holder.getView(R.id.notice_title);
        TextView state = holder.getView(R.id.notice_status);
        TextView time = holder.getView(R.id.notice_time);
        title.setText(bean.getTitle());
        state.setText(bean.getState());
        time.setText(bean.getTime());

        }
}

```
就是这么简单。

