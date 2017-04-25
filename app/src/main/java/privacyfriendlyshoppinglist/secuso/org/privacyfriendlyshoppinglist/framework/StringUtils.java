package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework;

import java.text.DecimalFormat;
import java.text.ParseException;

public abstract class StringUtils
{
    public static boolean isEmpty(String string)
    {
        return string == null || string.isEmpty();
    }

    public static Double getStringAsDouble(String numberAsString, String format)
    {
        DecimalFormat df = new DecimalFormat(format);
        try
        {
            Number parse = df.parse(numberAsString);
            return parse.doubleValue();
        }

        catch ( ParseException e )
        {
            return -1.0;
        }
    }

    public static Double getStringAsDouble(String productPrice, String priceFormat2, String priceFormat1, String priceFormat0)
    {
        Double stringAsDouble;
        try
        {

            stringAsDouble = StringUtils.getStringAsDouble(productPrice, priceFormat2);
        }
        catch ( Exception e1 )
        {
            try
            {
                stringAsDouble = StringUtils.getStringAsDouble(productPrice, priceFormat1);
            }
            catch ( Exception e2 )
            {
                try
                {
                    stringAsDouble = StringUtils.getStringAsDouble(productPrice, priceFormat0);
                }
                catch ( Exception e3 )
                {
                    stringAsDouble = -1.0;
                }
            }
        }

        return stringAsDouble;
    }
}
