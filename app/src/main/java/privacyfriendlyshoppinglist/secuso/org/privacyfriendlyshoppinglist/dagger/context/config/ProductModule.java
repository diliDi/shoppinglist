package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config.ShoppingListModule;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.business.ProductService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.business.impl.ProductServiceImpl;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.business.impl.converter.ProductConverterService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.business.impl.converter.impl.ProductConverterServiceImpl;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.persistence.ProductItemDao;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.persistence.impl.ProductItemDaoImpl;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.ShoppingListService;

@Module(
        includes = {
                ShoppingListModule.class
        },
        injects = {
                ProductService.class,
                ProductItemDao.class,
                ProductConverterService.class,
        }
)
public class ProductModule
{
    @Provides
    @Singleton
    ProductItemDao provideProductItemDao()
    {
        return new ProductItemDaoImpl();
    }

    @Provides
    @Singleton
    ProductConverterService provideProductConverterService()
    {
        return new ProductConverterServiceImpl();
    }

    @Provides
    @Singleton
    ProductService provideProductService(
            ProductItemDao productItemDao,
            ProductConverterService converterService,
            ShoppingListService shoppingListService
    )
    {
        return new ProductServiceImpl(
                productItemDao,
                converterService,
                shoppingListService
        );
    }
}
