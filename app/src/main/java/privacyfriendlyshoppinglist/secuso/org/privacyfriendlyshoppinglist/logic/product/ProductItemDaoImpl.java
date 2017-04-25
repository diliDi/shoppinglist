package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product;

import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.AbstractDao;

import java.util.List;

public class ProductItemDaoImpl extends AbstractDao<ProductItemEntity> implements ProductItemDao
{
    @Override
    public Long save(ProductItemEntity entity)
    {
        return saveOrUpdate(entity);
    }

    @Override
    public ProductItemEntity getById(Long id)
    {
        return getById(id, ProductItemEntity.class);
    }

    @Override
    public List<ProductItemEntity> getAllEntities()
    {
        return getAllEntities(ProductItemEntity.class);
    }

    @Override
    public Boolean deleteById(Long id)
    {
        return deleteById(id, ProductItemEntity.class);
    }
}
