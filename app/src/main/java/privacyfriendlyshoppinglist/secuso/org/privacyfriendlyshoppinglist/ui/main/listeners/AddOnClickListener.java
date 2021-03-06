package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.main.listeners;

import android.view.View;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ListItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.main.ShoppingListActivityCache;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.shoppinglist.ListDialogFragment;

public class AddOnClickListener implements View.OnClickListener
{
    private ShoppingListActivityCache cache;

    public AddOnClickListener(ShoppingListActivityCache cache)
    {
        this.cache = cache;

    }

    @Override
    public void onClick(View v)
    {
        ListItem item = new ListItem();

        if ( !ListDialogFragment.isOpened() )
        {
            ListDialogFragment listDialogFragment = ListDialogFragment.newAddInstance(item, cache);
            listDialogFragment.show(cache.getActivity().getSupportFragmentManager(), "DialogFragment");
        }
    }
}
