package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList;

import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.ContextSetter;

public interface ShoppingListConverter extends ContextSetter
{
    void convertItemToEntity(ListItem item, ShoppingListEntity entity);

    void convertEntityToItem(ShoppingListEntity entity, ListItem item);
}
