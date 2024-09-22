package student;

public class Items implements ItemInterface 
{
    protected String name;
    protected String description;
    protected double price;
    protected boolean isBook;

    public Items(String name, String description, double price, boolean isBook) 
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isBook = isBook;
    }

    @Override
    public String getName() 
    {
        return name;
    }

    @Override
    public String getDescription() 
    {
        return description;
    }

    @Override
    public String getDataPath()
    {
        return ""; // Can be overridden if needed
    }

    @Override
    public double getPrice() 
    {
        return price;
    }

    @Override
    public boolean isBook()
    {
        return isBook;
    };

}
