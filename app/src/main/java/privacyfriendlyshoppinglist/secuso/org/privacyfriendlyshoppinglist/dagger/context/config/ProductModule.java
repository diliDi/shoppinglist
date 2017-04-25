package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductServiceImpl;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductConverterService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductConverterServiceImpl;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductItemDao;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.ProductItemDaoImpl;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ShoppingListService;

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
