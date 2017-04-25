package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class AbstractAdapter<Item extends AbstractItem, Cache, ViewHolder extends AbstractViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    protected List<Item> list;
    protected Cache cache;
    protected int layoutId;

    public AbstractAdapter(List<Item> list, Cache cache, int layoutId)
    {
        this.list = list;
        this.cache = cache;
        this.layoutId = layoutId;
    }

    public void setList(List<Item> list)
    {
        this.list = list;
    }

    public List<Item> getList()
    {
        return list;
    }

    protected void setLayoutId(int layoutId)
    {
        this.layoutId = layoutId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return createNewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        ViewHolder holder = (ViewHolder) viewHolder;
        Item item = list.get(position);
        holder.processItem(item);
    }

    @Override
    public int getItemCount()
    {
        return list == null ? 0 : list.size();
    }

    protected abstract ViewHolder createNewViewHolder(View view);
}
