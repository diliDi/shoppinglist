package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config;

import dagger.Module;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config.product.ProductModule;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config.shoppingList.ShoppingListModule;
@Module(
        includes = {
                // DEPENDENCY_INJECTION add all Modules here
                ProductModule.class,
                ShoppingListModule.class
        }
)
public class AppContextModule
{
}
