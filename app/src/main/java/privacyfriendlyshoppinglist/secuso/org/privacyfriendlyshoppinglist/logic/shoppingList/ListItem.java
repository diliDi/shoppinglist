package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.shoppingList;

import android.content.Context;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.AbstractItem;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.StringUtils;

public class ListItem extends AbstractItem
{
    private String listName;
    private int icon;
    private String notes;
    private boolean selected;

    public String getListName()
    {
        return listName;
    }

    public void setListName(String listName)
    {
        this.listName = listName;
    }

    public int getIcon()
    {
        return icon;
    }

    public void setIcon(int icon)
    {
        this.icon = icon;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public String getDetailInfo(Context context)
    {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");

        if ( !StringUtils.isEmpty(this.getNotes()) )
        {
            sb.append("\n");
            sb.append(context.getResources().getString(R.string.list_notes));
            sb.append(": ");
            sb.append(this.getNotes());
        }

        return sb.toString();
    }

    @Override
    public String toString()
    {
        return "ListItem{" +
                "listName='" + listName + '\'' +
                ", icon=" + icon +
                ", selected=" + selected +
                '}';
    }

}
