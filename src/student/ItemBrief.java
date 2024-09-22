package student;

public class ItemBrief implements ItemInterface
{
    public String name;
    public String dataPath;

    public ItemBrief(String n, String path)
    {
        name = n;
        dataPath = path;
    }

    @Override public String getName() { 
        return name; }

    @Override public String getDescription() { 
        return ""; }

    @Override public String getDataPath() { 
        return dataPath; }

    @Override public boolean isBook() { 
        return false; }

    @Override public double getPrice() { 
        return 0; }
}

