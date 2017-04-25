package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework;

import android.content.Context;
import dagger.ObjectGraph;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.dagger.context.config.AppContextModule;

public abstract class AbstractInstanceFactory
{
    private static Context context;

    public AbstractInstanceFactory(Context context)
    {
        AbstractInstanceFactory.context = context;
    }

    public Object createInstance(Class aClass)
    {
        ObjectGraph objectGraph = ObjectGraph.create(new AppContextModule());
        Object classInstance = objectGraph.get(aClass);
        ((ContextSetter) classInstance).setContext(context, getDB());
        return classInstance;
    }

    public static Object createInstance(Class aClass, DB db)
    {
        ObjectGraph objectGraph = ObjectGraph.create(new AppContextModule());
        Object classInstance = objectGraph.get(aClass);
        ((ContextSetter) classInstance).setContext(context, db);
        return classInstance;
    }

    protected abstract DB getDB();
}
