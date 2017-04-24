package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.logger.PFALogger;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product.persistence.entity.ProductItemEntity;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList.persistence.entity.ShoppingListEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class DataBaseHelper extends OrmLiteSqliteOpenHelper
{
    private static final String ON_CREATE = "onCreate";
    private static final String START = "start";
    private static final String ON_UPGRADE = "onUpgrade";
    private static final String ORMLITE_CONFIG_TXT = "ormlite_config.txt";
    private static List<Class<? extends AbstractEntity>> entityClasses;

    DataBaseHelper(Context context, DB db)
    {
        super(context, db.getDbName(), null, db.getDbVersion(), R.raw.ormlite_config);
    }

    private void setupClasses()
    {
        entityClasses = new ArrayList<>();
        entityClasses.add(ProductItemEntity.class);
        entityClasses.add(ShoppingListEntity.class);
    }

    @Override
    public void onCreate(SQLiteDatabase database, final ConnectionSource connectionSource)
    {
        try
        {
            setupClasses();
            PFALogger.debug(getClass().getName(), ON_CREATE, START);
            for ( Class aClass : entityClasses )
            {
                TableUtils.createTable(connectionSource, aClass);
            }
        }
        catch ( Exception e )
        {
            PFALogger.error(getClass().getName(), ON_CREATE, e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            setupClasses();
            for ( Class aClass : entityClasses )
            {
                TableUtils.dropTable(connectionSource, aClass, true);
            }
            onCreate(database, connectionSource);
        }
        catch ( SQLException e )
        {
            PFALogger.error(getClass().getName(), ON_UPGRADE, e);
        }
    }

    private static class DataBaseConfig extends OrmLiteConfigUtil
    {
        public static void main(String[] args) throws Exception
        {
            writeConfigFile(ORMLITE_CONFIG_TXT);
        }
    }
}
