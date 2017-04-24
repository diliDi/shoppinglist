package privacyfriendlyshoppinglist.secuso.org.privacyfriendlyshoppinglist.framework.persistence;

public enum DB
{
    APP("ShippingList.db", 7);

    private String dbName;
    private int dbVersion;

    DB(String dbName, int dbVersion)
    {
        this.dbName = dbName;
        this.dbVersion = dbVersion;
    }

    public String getDbName()
    {
        return dbName;
    }

    public int getDbVersion()
    {
        return dbVersion;
    }
}
