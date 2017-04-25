package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ShoppingListService;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ShoppingListServiceImpl;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ShoppingListConverter;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ShoppingListConverterImpl;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ShoppingListDao;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.ShoppingListDaoImpl;

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
