package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.persistence;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.context.ContextSetter;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T extends AbstractEntity> implements ContextSetter
{
    private DataBaseHelper database;

    @Override
    public void setContext(Context context, DB db)
    {
        database = new DataBaseHelper(context, db);
    }

    protected Long saveOrUpdate(T entity)
    {
        try
        {
            @SuppressWarnings("unchecked")
            Dao<T, Long> dao = database.getDao((Class) entity.getClass());
            dao.createOrUpdate(entity);
            return entity.getId();
        }
        catch ( SQLException e )
        {
            return null;
        }
    }

    protected T getById(Long id, Class<T> type)
    {
        try
        {
            Dao<T, Long> dao = database.getDao(type);
            T entity = dao.queryForId(id);
            return entity;
        }
        catch ( SQLException e )
        {
            return null;
        }
    }

    protected List<T> getAllEntities(Class<T> type)
    {
        List<T> entities;
        try
        {
            Dao<T, Long> dao = database.getDao(type);
            entities = dao.queryForAll();
            return entities;
        }
        catch ( SQLException e )
        {
            return null;
        }
    }

    protected boolean deleteById(Long id, Class<T> type)
    {
        try
        {
            Dao<T, Long> dao = database.getDao(type);
            dao.deleteById(id);
            return true;
        }
        catch ( SQLException e )
        {
            return false;
        }
    }

    protected Dao<T, Long> getDao(Class<T> type)
    {
        try
        {
            Dao<T, Long> dao = database.getDao(type);
            return dao;
        }
        catch ( SQLException e )
        {
            return null;
        }
    }


}
