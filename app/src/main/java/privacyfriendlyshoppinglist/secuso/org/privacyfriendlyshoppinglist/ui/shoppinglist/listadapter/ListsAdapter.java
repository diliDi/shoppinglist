package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.shoppinglist.listadapter;

import android.view.View;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.AbstractAdapter;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ListItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.main.ShoppingListActivityCache;

import java.util.List;


public class ListsAdapter extends AbstractAdapter<ListItem, ShoppingListActivityCache, ListsItemViewHolder>
{
    public ListsAdapter(List<ListItem> shoppingList, ShoppingListActivityCache cache)
    {
        super(
                shoppingList,
                cache,
                R.layout.shopping_list_item);

        setLayoutId(getListItemLayout());
    }

    private int getListItemLayout()
    {
        int listItemLayout;
        listItemLayout = R.layout.shopping_list_item;
        return listItemLayout;
    }

    @Override
    protected ListsItemViewHolder createNewViewHolder(View view)
    {
        return new ListsItemViewHolder(view, cache);
    }
}
