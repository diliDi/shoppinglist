package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList;

import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.ContextSetter;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ShoppingListEntity;

import java.util.List;

public interface ShoppingListDao extends ContextSetter
{
    Long save(ShoppingListEntity entity);

    ShoppingListEntity getById(Long id);

    List<ShoppingListEntity> getAllEntities();

    Boolean deleteById(Long id);
}
