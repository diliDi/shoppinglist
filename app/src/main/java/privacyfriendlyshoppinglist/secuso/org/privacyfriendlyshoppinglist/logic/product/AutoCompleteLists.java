package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product;

import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.StringUtils;

import java.util.Set;
import java.util.TreeSet;

public class AutoCompleteLists
{
    private Set<String> stores;
    private Set<String> categories;
    private Set<String> products;

    public AutoCompleteLists()
    {
        stores = new TreeSet<>();
        categories = new TreeSet<>();
        products = new TreeSet<>();
    }

    public void updateLists(ProductItem item)
    {
        String name = item.getProductName();
        String store = item.getProductStore();
        String category = item.getProductCategory();

        if ( !StringUtils.isEmpty(name) )
        {
            products.add(name);
        }

        if ( !StringUtils.isEmpty(category) )
        {
            categories.add(category);
        }

        if ( !StringUtils.isEmpty(store) )
        {
            stores.add(store);
        }
    }

    public Set<String> getStores()
    {
        return stores;
    }

    public Set<String> getCategories()
    {
        return categories;
    }

    public Set<String> getProducts()
    {
        return products;
    }

    public String[] getProductsArray()
    {
        return getStringArray(this.products);
    }

    public String[] getStoresArray()
    {
        return getStringArray(this.stores);
    }

    public String[] getCategoryArray()
    {
        return getStringArray(this.categories);
    }

    private String[] getStringArray(Set<String> aSet)
    {
        String[] stringArray = new String[ aSet.size() ];
        aSet.toArray(stringArray);
        return stringArray;
    }

    public void copyTo(AutoCompleteLists autoCompleteLists)
    {
        autoCompleteLists.stores = this.stores;
        autoCompleteLists.categories = this.categories;
        autoCompleteLists.products = this.products;
    }
}
