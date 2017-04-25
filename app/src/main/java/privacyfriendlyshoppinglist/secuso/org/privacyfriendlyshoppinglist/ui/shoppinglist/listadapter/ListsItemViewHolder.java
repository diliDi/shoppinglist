package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.shoppinglist.listadapter;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageButton;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.AbstractInstanceFactory;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.InstanceFactory;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.AbstractViewHolder;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.TotalItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ShoppingListService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ListItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.main.MainActivity;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.main.ShoppingListActivityCache;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.products.ProductsActivity;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.shoppinglist.EditDeleteListDialog;

import java.util.ArrayList;
import java.util.List;

class ListsItemViewHolder extends AbstractViewHolder<ListItem, ShoppingListActivityCache>
{
    private ListItemCache listItemCache;
    private ProductService productService;
    private ShoppingListService shoppingListService;

    ListsItemViewHolder(final View parent, ShoppingListActivityCache cache)
    {
        super(parent, cache);
        this.listItemCache = new ListItemCache(parent);
        AbstractInstanceFactory instanceFactory = new InstanceFactory(cache.getActivity());
        this.productService = (ProductService) instanceFactory.createInstance(ProductService.class);
        this.shoppingListService = (ShoppingListService) instanceFactory.createInstance(ShoppingListService.class);
    }

    public void processItem(ListItem item)
    {
        listItemCache.getListNameTextView().setText(item.getListName());

        List<ProductItem> productItems = new ArrayList<>();
        productService.getAllProducts(item.getId())
                .filter(productItem -> !productItem.isChecked())
                .doOnNext(productItem -> productItems.add(productItem))
                .doOnCompleted(() ->
                {
                })
                .subscribe();


        final TotalItem[] totalItem = new TotalItem[ 1 ];
        productService.getInfo(item.getId())
                .doOnNext(result -> totalItem[ 0 ] = result)
                .doOnCompleted(() ->
                        {
                            listItemCache.getListDetails().setText(
                                    totalItem[ 0 ].getInfo(listItemCache.getCurrency(), cache.getActivity()) +
                                            item.getDetailInfo(listItemCache.getListCard().getContext()));
                            listItemCache.getNrProductsTextView().setText(String.valueOf(totalItem[ 0 ].getNrProducts()));
                        }
                ).subscribe();

        listItemCache.getListCard().setOnClickListener(v ->
        {
            Intent intent = new Intent(cache.getActivity(), ProductsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(MainActivity.LIST_ID_KEY, item.getId());
            cache.getActivity().startActivity(intent);
        });

        listItemCache.getListCard().setOnLongClickListener(view ->
        {

            DialogFragment editDeleteFragment = EditDeleteListDialog.newEditDeleteInstance(item, cache);
            editDeleteFragment.show(cache.getActivity().getSupportFragmentManager(), "List");

            return true;
        });

        ImageButton showDetailsButton = listItemCache.getShowDetailsImageButton();
        showDetailsButton.setOnClickListener(v ->
        {
            listItemCache.setDetailsVisible(!listItemCache.isDetailsVisible());
            if ( listItemCache.isDetailsVisible() )
            {
                showDetailsButton.setImageResource(R.drawable.ic_keyboard_arrow_up_white_48sp);
                listItemCache.getListDetails().setVisibility(View.VISIBLE);

            }
            else
            {
                showDetailsButton.setImageResource(R.drawable.ic_keyboard_arrow_down_white_48sp);
                listItemCache.getListDetails().setVisibility(View.GONE);
            }
        });

    }
}