package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.ui.shoppinglist.listadapter;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;

public class ListItemCache
{
    private TextView listNameTextView;
    private TextView nrProductsTextView;
    private TextView listDetails;
    private CardView listCard;
    private ImageButton showDetailsImageButton;
    private boolean detailsVisible;
    private String currency;

    public ListItemCache(View parent)
    {
        listNameTextView = (TextView) parent.findViewById(R.id.textview_list_name);
        nrProductsTextView = (TextView) parent.findViewById(R.id.textview_prod_quantity);
        listDetails = (TextView) parent.findViewById(R.id.textview_list_details);
        listCard = (CardView) parent.findViewById(R.id.cardview_item);
        showDetailsImageButton = (ImageButton) parent.findViewById(R.id.expand_button_list);
        detailsVisible = false;
        currency = "$";
    }

    public TextView getListNameTextView()
    {
        return listNameTextView;
    }

    public TextView getNrProductsTextView()
    {
        return nrProductsTextView;
    }

    public CardView getListCard()
    {
        return listCard;
    }

    public ImageButton getShowDetailsImageButton()
    {
        return showDetailsImageButton;
    }

    public TextView getListDetails()
    {
        return listDetails;
    }

    public boolean isDetailsVisible()
    {
        return detailsVisible;
    }

    public void setDetailsVisible(boolean detailsVisible)
    {
        this.detailsVisible = detailsVisible;
    }

    public String getCurrency()
    {
        return currency;
    }
}
