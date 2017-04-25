package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList;

import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.ContextSetter;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductItem;
import rx.Observable;

import java.util.List;

public interface ShoppingListService extends ContextSetter
{
    Observable<Void> saveOrUpdate(ListItem item);

    Void saveOrUpdateSync(ListItem item);

    Observable<ListItem> getById(String id);

    ShoppingListEntity getEntityByIdSync(String id);

    Observable<Void> deleteById(String id);

    Observable<ListItem> getAllListItems();

    Observable<String> deleteSelected(List<ListItem> shoppingListItems);

    List<ListItem> moveSelectedToEnd(List<ListItem> shoppingListItems);

    String getShareableText(ListItem listItem, List<ProductItem> productItems);
}
