package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework;

import com.j256.ormlite.field.DatabaseField;

public abstract class AbstractEntity
{
    @DatabaseField(generatedId = true)
    private Long id;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}

