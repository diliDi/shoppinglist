package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.products.listadapter;

import android.view.View;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.AbstractAdapter;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.products.ProductActivityCache;

import java.util.List;

public class ProductsAdapter extends AbstractAdapter<ProductItem, ProductActivityCache, ProductsItemViewHolder>
{

    public ProductsAdapter(List<ProductItem> productsList, ProductActivityCache cache)
    {
        super(
                productsList,
                cache,
                R.layout.product_list_item);
        setLayoutId(getListItemLayout());
    }

    private int getListItemLayout()
    {
        int listItemLayout;
        listItemLayout = R.layout.product_list_item;
        return listItemLayout;
    }

    @Override
    protected ProductsItemViewHolder createNewViewHolder(View view)
    {
        return new ProductsItemViewHolder(view, cache);
    }
}
