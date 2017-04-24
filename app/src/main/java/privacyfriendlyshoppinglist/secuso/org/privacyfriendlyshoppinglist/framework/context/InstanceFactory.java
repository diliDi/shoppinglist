package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.context;

import android.content.Context;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.persistence.DB;

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
