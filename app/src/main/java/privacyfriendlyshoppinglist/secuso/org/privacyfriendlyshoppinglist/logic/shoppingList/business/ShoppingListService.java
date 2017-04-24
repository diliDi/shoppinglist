package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business;

import org.joda.time.DateTime;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.context.ContextSetter;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.business.domain.ProductItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.domain.ListItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.persistence.entity.ShoppingListEntity;
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
