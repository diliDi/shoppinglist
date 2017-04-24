package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.ShoppingListService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.impl.ShoppingListServiceImpl;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.impl.converter.ShoppingListConverter;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.business.impl.converter.impl.ShoppingListConverterImpl;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.persistence.ShoppingListDao;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.persistence.impl.ShoppingListDaoImpl;

@Module(
        injects = {
                ShoppingListDao.class,
                ShoppingListService.class,
                ShoppingListConverter.class
        }
)
public class ShoppingListModule
{

    @Provides
    @Singleton
    ShoppingListDao provideShoppingListDao()
    {
        return new ShoppingListDaoImpl();
    }

    @Provides
    @Singleton
    ShoppingListConverter provideShoppingListConverter()
    {
        return new ShoppingListConverterImpl();
    }

    @Provides
    @Singleton
    ShoppingListService provideShoppingListService(
            ShoppingListDao shoppingListDao,
            ShoppingListConverter shoppingListConverter
    )
    {
        return new ShoppingListServiceImpl(
                shoppingListDao,
                shoppingListConverter
        );
    }
}
