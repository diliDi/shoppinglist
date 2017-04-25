package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product;

import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.ContextSetter;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductItemEntity;

import java.util.List;

public interface ProductItemDao extends ContextSetter
{
    Long save(ProductItemEntity entity);

    ProductItemEntity getById(Long id);

    List<ProductItemEntity> getAllEntities();

    Boolean deleteById(Long id);
}
