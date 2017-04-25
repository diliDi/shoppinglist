package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList;

import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.ContextSetter;

/**
 * Description:
 * Author: Grebiel Jose Ifill Brito
 * Created: 11.06.16 creation date
 */
public interface ShoppingListConverter extends ContextSetter
{
    void convertItemToEntity(ListItem item, ShoppingListEntity entity);

    void convertEntityToItem(ShoppingListEntity entity, ListItem item);
}
