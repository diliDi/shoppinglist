package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework;

import android.content.Context;

public class InstanceFactory extends AbstractInstanceFactory
{
    public InstanceFactory(Context context)
    {
        super(context);
    }

    @Override
    protected DB getDB()
    {
        return DB.APP;
    }
}
