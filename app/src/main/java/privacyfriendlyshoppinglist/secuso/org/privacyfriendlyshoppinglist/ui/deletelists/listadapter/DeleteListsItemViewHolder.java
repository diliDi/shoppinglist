package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.deletelists.listadapter;

import android.content.res.Resources;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.AbstractInstanceFactory;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.InstanceFactory;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.AbstractViewHolder;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.TotalItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ShoppingListService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ListItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.deletelists.DeleteListsCache;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.shoppinglist.listadapter.ListItemCache;

import java.util.ArrayList;
import java.util.List;

class DeleteListsItemViewHolder extends AbstractViewHolder<ListItem, DeleteListsCache> {

    private static final String HIGH_PRIORITY_INDEX = "0";
    private ListItemCache listItemCache;
    private ProductService productService;
    private ShoppingListService shoppingListService;

    DeleteListsItemViewHolder(final View parent, DeleteListsCache deleteListsCache) {
        super(parent, deleteListsCache);
        this.listItemCache = new ListItemCache(parent);
        AbstractInstanceFactory instanceFactory = new InstanceFactory(deleteListsCache.getActivity());
        this.productService = (ProductService) instanceFactory.createInstance(ProductService.class);
        this.shoppingListService = (ShoppingListService) instanceFactory.createInstance(ShoppingListService.class);
    }

    public void processItem(ListItem item) {
        listItemCache.getListNameTextView().setText(item.getListName());
//        listItemCache.getDeadLineTextView().setText(item.getDeadlineDate());

        listItemCache.getShowDetailsImageButton().setVisibility(View.GONE);

        List<ProductItem> productItems = new ArrayList<>();
        productService.getAllProducts(item.getId())
                .filter(productItem -> !productItem.isChecked())
                .doOnNext(productItem -> productItems.add(productItem))
                .doOnCompleted(() ->
                {
//                    int reminderStatus = shoppingListService.getReminderStatusResource(item, productItems);
//                    listItemCache.getReminderBar().setImageResource(reminderStatus);
                })
                .subscribe();

        final TotalItem[] totalItem = new TotalItem[1];
        productService.getInfo(item.getId())
                .doOnNext(result -> totalItem[0] = result)
                .doOnCompleted(() ->
                        listItemCache.getNrProductsTextView().setText(String.valueOf(totalItem[0].getNrProducts()))
                ).subscribe();

//        setupPriorityIcon(item);
//        setupReminderIcon(item);

        updateVisibilityFormat(item);

        listItemCache.getListCard().setOnClickListener(v ->
        {
            item.setSelected(!item.isSelected());
            updateVisibilityFormat(item);
        });
    }

    private void updateVisibilityFormat(ListItem item) {
        CardView listCard = listItemCache.getListCard();
        TextView listNameTextView = listItemCache.getListNameTextView();
        TextView listNrProdTextView = listItemCache.getNrProductsTextView();
        Resources resources = listCard.getContext().getResources();
        if (item.isSelected()) {
            listCard.setCardBackgroundColor(resources.getColor(R.color.transparent));
            listNameTextView.setTextColor(resources.getColor(R.color.middlegrey));
            listNameTextView.setPaintFlags(listNameTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            listNrProdTextView.setPaintFlags(listNrProdTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            listCard.setCardBackgroundColor(resources.getColor(R.color.white));
            listNameTextView.setTextColor(resources.getColor(R.color.black));
            listNameTextView.setPaintFlags(listNameTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            listNrProdTextView.setPaintFlags(listNrProdTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }
}