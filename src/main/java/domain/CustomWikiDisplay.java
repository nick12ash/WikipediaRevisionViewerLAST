package domain;

public class CustomWikiDisplay
{
    private Article article;
    private String displayString = "";
    private String displayString2 = "";
    private String titleDisplay = "";
    public String display;

    public CustomWikiDisplay(Article article)
    {
        this.article = article;
    }

    public String toString() {
        if (article.getTitle() == null)
        {
            return ("There was no article found with that title. Please try again.");
        }
        displayString += article.getChanges() + article.getRedirection();
        return (displayString);
    }

    public String titleToString ()
    {
        article.getTitle();
        return (titleDisplay);
    }
}
