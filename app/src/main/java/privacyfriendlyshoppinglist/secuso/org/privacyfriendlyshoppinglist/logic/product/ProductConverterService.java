package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product;

import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.ContextSetter;

public interface ProductConverterService extends ContextSetter
{
    void convertItemToEntity(ProductItem item, ProductItemEntity entity);

    void convertEntitiesToItem(ProductItemEntity entity, ProductItem item);

    String getDoubleAsString(Double price);

    Double getStringAsDouble(String priceString);
}
