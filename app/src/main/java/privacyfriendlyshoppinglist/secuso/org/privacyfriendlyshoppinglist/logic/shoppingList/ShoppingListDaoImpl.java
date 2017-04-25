package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList;

import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.AbstractDao;

import java.util.List;

public class ShoppingListDaoImpl extends AbstractDao<ShoppingListEntity> implements ShoppingListDao
{
    @Override
    public Long save(ShoppingListEntity entity)
    {
        return saveOrUpdate(entity);
    }

    @Override
    public ShoppingListEntity getById(Long id)
    {
        return getById(id, ShoppingListEntity.class);
    }

    @Override
    public List<ShoppingListEntity> getAllEntities()
    {
        return getAllEntities(ShoppingListEntity.class);
    }

    @Override
    public Boolean deleteById(Long id)
    {
        return deleteById(id, ShoppingListEntity.class);
    }
}
