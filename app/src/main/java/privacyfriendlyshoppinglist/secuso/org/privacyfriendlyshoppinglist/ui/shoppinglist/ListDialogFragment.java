package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.shoppinglist;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.context.AbstractInstanceFactory;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.context.InstanceFactory;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.ShoppingListService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.domain.ListItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.main.MainActivity;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.main.ShoppingListActivityCache;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.products.ProductsActivity;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.shoppinglist.listeners.ListsDialogFocusListener;

public class ListDialogFragment extends DialogFragment
{

    private ShoppingListActivityCache cache;
    private ListItem item;
    private ShoppingListService shoppingListService;
    private static boolean editDialog;
    private static boolean opened;
    private ListDialogCache dialogCache;

    public static ListDialogFragment newEditInstance(ListItem item, ShoppingListActivityCache cache)
    {
        editDialog = true;
        ListDialogFragment dialogFragment = getListDialogFragment(item, cache);
        return dialogFragment;
    }

    public static ListDialogFragment newAddInstance(ListItem item, ShoppingListActivityCache cache)
    {
        editDialog = false;
        ListDialogFragment dialogFragment = getListDialogFragment(item, cache);
        return dialogFragment;
    }

    private static ListDialogFragment getListDialogFragment(ListItem item, ShoppingListActivityCache cache)
    {
        ListDialogFragment dialogFragment = new ListDialogFragment();
        dialogFragment.setCache(cache);
        dialogFragment.setItem(item);
        return dialogFragment;
    }

    public void setCache(ShoppingListActivityCache cache)
    {
        this.cache = cache;
    }

    public void setItem(ListItem item)
    {
        this.item = item;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        opened = true; // flag to avoid opening this dialog twice
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);
        opened = false; // flag to avoid opening this dialog twice
    }

    public static boolean isOpened()
    {
        return opened;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AbstractInstanceFactory instanceFactory = new InstanceFactory(cache.getActivity().getApplicationContext());
        shoppingListService = (ShoppingListService) instanceFactory.createInstance(ShoppingListService.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogColourful);
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.shopping_list_dialog, null);
        dialogCache = new ListDialogCache(v);

        if ( editDialog )
        {
            dialogCache.getTitleTextView().setText(getActivity().getResources().getString(R.string.list_name_edit));
        }
        else
        {
            dialogCache.getTitleTextView().setText(getActivity().getResources().getString(R.string.list_name_new));
        }

        builder.setView(v);

        dialogCache.getListNameText().setOnFocusChangeListener(new ListsDialogFocusListener(dialogCache));
        dialogCache.getListNotes().setOnFocusChangeListener(new ListsDialogFocusListener(dialogCache));

        builder.setPositiveButton(cache.getActivity().getResources().getString(R.string.okay), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                item.setListName(dialogCache.getListNameText().getText().toString());
                item.setNotes(dialogCache.getListNotes().getText().toString());

                shoppingListService.saveOrUpdate(item)
                        .doOnCompleted(() ->
                        {
                            if ( !editDialog )
                            {
                                Intent intent = new Intent(cache.getActivity(), ProductsActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra(MainActivity.LIST_ID_KEY, item.getId());
                                cache.getActivity().startActivity(intent);
                            }
                            else
                            {
                                MainActivity mainActivity = (MainActivity) cache.getActivity();
                                mainActivity.updateListView();
                            }
                        })
                        .subscribe();
            }
        });

        builder.setNegativeButton(cache.getActivity().getResources().getString(R.string.cancel), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
            }
        });

        AlertDialog dialog = builder.create();
        if ( !editDialog )
        {
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        return dialog;
    }

}
