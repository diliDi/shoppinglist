package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList;

import android.content.Context;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.DB;

public class ShoppingListConverterImpl implements ShoppingListConverter
{
    private String language;
    private String dateLongPattern;
    private String datePattern;
    private String timePattern;
    private Context context;

    @Override
    public void setContext(Context context, DB db)
    {
        this.context = context;
        this.language = context.getResources().getString(R.string.language);
        this.dateLongPattern = context.getResources().getString(R.string.date_long_pattern);
        this.datePattern = context.getResources().getString(R.string.date_short_pattern);
        this.timePattern = context.getResources().getString(R.string.time_pattern);
    }

    @Override
    public void convertItemToEntity(ListItem item, ShoppingListEntity entity)
    {
        Long id = getIdAsLong(item);
        entity.setId(id);
        entity.setListName(item.getListName());
        entity.setIcon(item.getIcon());
        entity.setNotes(item.getNotes());
    }

    @Override
    public void convertEntityToItem(ShoppingListEntity entity, ListItem item)
    {
        item.setId(entity.getId().toString());
        item.setListName(entity.getListName());
        item.setIcon(entity.getIcon());
        item.setNotes(entity.getNotes());
    }

    private Long getIdAsLong(ListItem item)
    {
        String stringId = item.getId();
        return stringId == null ? null : Long.valueOf(stringId);
    }
}
