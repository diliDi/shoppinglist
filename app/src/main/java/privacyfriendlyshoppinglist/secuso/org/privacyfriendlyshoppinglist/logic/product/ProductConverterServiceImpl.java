package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.logic.product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.R;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.DB;
import privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.StringUtils;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

public class ProductConverterServiceImpl implements ProductConverterService
{
    private String priceFormat0;
    private String priceFormat1;
    private String priceFormat2;
    private Context context;

    @Override
    public void setContext(Context context, DB db)
    {
        this.context = context;
        this.priceFormat0 = context.getResources().getString(R.string.number_format_0_decimals);
        this.priceFormat1 = context.getResources().getString(R.string.number_format_1_decimal);
        this.priceFormat2 = context.getResources().getString(R.string.number_format_2_decimals);
    }

    @Override
    public void convertItemToEntity(ProductItem item, ProductItemEntity entity)
    {
        entity.setProductName(item.getProductName());

        if ( !StringUtils.isEmpty(item.getId()) )
        {
            entity.setId(Long.valueOf(item.getId()));
        }

        if ( !StringUtils.isEmpty(item.getQuantity()) )
        {
            entity.setQuantity(Integer.valueOf(item.getQuantity()));
        }
        else
        {
            entity.setQuantity(1);
        }

        entity.setNotes(item.getProductNotes());
        entity.setStore(item.getProductStore());

        String priceString = item.getProductPrice();
        if ( !StringUtils.isEmpty(priceString) )
        {
            Double price = getStringAsDouble(priceString);
            entity.setPrice(price);
        }
        else
        {
            entity.setPrice(0.0);
        }

        entity.setCategory(item.getProductCategory());
        entity.setSelected(item.isChecked());

        if ( item.getThumbnailBitmap() != null && !item.isDefaultImage() )
        {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            item.getThumbnailBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
            entity.setImageBytes(stream.toByteArray());
        }
        else
        {
            entity.setImageBytes(null);
        }
    }

    @Override
    public void convertEntitiesToItem(ProductItemEntity entity, ProductItem item)
    {
        item.setListId(entity.getShoppingList().getId().toString());
        item.setProductName(entity.getProductName());

        if ( entity.getId() != null )
        {
            item.setId(String.valueOf(entity.getId()));
        }

        if ( entity.getQuantity() != null )
        {
            item.setQuantity(String.valueOf(entity.getQuantity()));
        }

        item.setProductNotes(entity.getNotes());
        item.setProductStore(entity.getStore());

        if ( entity.getPrice() != null )
        {
            item.setProductPrice(getDoubleAsString(entity.getPrice()));
        }

        if ( entity.getQuantity() != null && entity.getPrice() != null )
        {
            item.setTotalProductPrice(getDoubleAsString(entity.getPrice() * entity.getQuantity()));
        }

        item.setProductCategory(entity.getCategory());
        item.setChecked(entity.getSelected());

        byte[] imageBytes = entity.getImageBytes();
        if ( imageBytes != null )
        {
            Bitmap imageBitMap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            item.setThumbnailBitmap(imageBitMap);
            item.setDefaultImage(false);
        }
        else
        {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_menu_camera);
            item.setThumbnailBitmap(bitmap);
            item.setDefaultImage(true);
        }
    }

    @Override
    public String getDoubleAsString(Double price)
    {
        String numberAsString;
        try
        {
            DecimalFormat df = new DecimalFormat(priceFormat2);
            numberAsString = df.format(price);
        }
        catch ( Exception e )
        {
            numberAsString = "";
        }
        return numberAsString;
    }

    @Override
    public Double getStringAsDouble(String priceString)
    {
        return StringUtils.getStringAsDouble(priceString, priceFormat2, priceFormat1, priceFormat0);
    }
}
