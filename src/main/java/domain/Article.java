package domain;

import java.util.Map;

public class Article
{
    private String title;
    private Map<String, String> timestampsAndUsernames;
    private String redirectionString;

    public Article(String title, Map timestampsAndUsernames, String redirectionString)
    {
        this.title = title;
        this.timestampsAndUsernames = timestampsAndUsernames;
        this.redirectionString = redirectionString;
    }

    public String getTitle()
    {
        return title;
    }

    public String getChanges()
    {
        String result = "";
        for (Map.Entry<String, String> entry : timestampsAndUsernames.entrySet())
        {
            result += "User: '" + (entry.getValue() + "' TimeStamp " + entry.getKey() + "\n");
        }
        return(result);
    }


    public String getTimestamps() {
        String result = "";
        for (Map.Entry<String, String> entry : timestampsAndUsernames.entrySet())
        {
            result += (entry.getKey()) + ", ";
        }
        return(result);
    }

    public String getUsernames()
    {
        String result = "";
        for (Map.Entry<String, String> entry : timestampsAndUsernames.entrySet())
        {
            result += (entry.getValue()) + ", ";
        }
        return(result);
    }

    public Integer getNumberChanges()
    {
        Integer result = 0;
        for (Map.Entry<String, String> entry : timestampsAndUsernames.entrySet())
        {
            if (getTimestamps().equals("2020")) {
                result += 1;
            }
        }
        return(result);
    }

    public String getRedirection()
    {
        return redirectionString;
    }
}
