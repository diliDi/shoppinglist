package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.shoppinglist.listeners;

import android.view.View;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.shoppinglist.ListDialogCache;

public class ListsDialogFocusListener implements View.OnFocusChangeListener
{
    private ListDialogCache dialogCache;

    public ListsDialogFocusListener(ListDialogCache dialogCache)
    {
        this.dialogCache = dialogCache;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus)
    {
        if ( hasFocus )
        {
            dialogCache.getDeadlineLayout().setVisibility(View.GONE);
            dialogCache.getDeadlineExpansionButton().setImageResource(R.drawable.ic_keyboard_arrow_down_white_48sp);
        }
    }
}
