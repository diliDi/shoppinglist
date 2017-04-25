package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.deletelists.listadapter;

import android.view.View;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.AbstractAdapter;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ListItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.deletelists.DeleteListsCache;

import java.util.List;

public class DeleteListsAdapter extends AbstractAdapter<ListItem, DeleteListsCache, DeleteListsItemViewHolder>
{

    public DeleteListsAdapter(List<ListItem> shoppingList, DeleteListsCache cache)
    {
        super(
                shoppingList,
                cache,
                R.layout.shopping_list_item);
    }

    @Override
    protected DeleteListsItemViewHolder createNewViewHolder(View view)
    {
        return new DeleteListsItemViewHolder(view, cache);
    }
}
