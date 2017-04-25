package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product;

import android.content.Context;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;

public class TotalItem
{
    private String totalAmount;
    private String checkedAmount;
    private boolean equalsZero;
    private int nrProducts;

    public String getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getCheckedAmount()
    {
        return checkedAmount;
    }

    public void setCheckedAmount(String checkedAmount)
    {
        this.checkedAmount = checkedAmount;
    }

    public boolean isEqualsZero()
    {
        return equalsZero;
    }

    public void setEqualsZero(boolean equalsZero)
    {
        this.equalsZero = equalsZero;
    }

    public void setNrProducts(int nrProducts)
    {
        this.nrProducts = nrProducts;
    }

    public int getNrProducts()
    {
        return nrProducts;
    }

    public String getInfo(String currency, Context context)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getResources().getString(R.string.nr_items));
        sb.append(": ");
        sb.append(this.getNrProducts());
        sb.append("\n");
        sb.append(context.getResources().getString(R.string.total_list_amount));
        sb.append(": ");
        sb.append(this.getTotalAmount());
        sb.append(" ");
        sb.append(currency);
        sb.append("\n");
        sb.append("\n");

        return sb.toString();
    }
}
