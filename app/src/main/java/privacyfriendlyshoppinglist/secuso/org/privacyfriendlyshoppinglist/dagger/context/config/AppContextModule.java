package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config;

import dagger.Module;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config.ProductModule;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config.ShoppingListModule;
@Module(
        includes = {
                ProductModule.class,
                ShoppingListModule.class
        }
)
public class AppContextModule
{
}
