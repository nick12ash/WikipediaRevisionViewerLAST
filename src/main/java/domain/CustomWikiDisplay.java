package domain;

public class CustomWikiDisplay
{
    private Article article;
    private String displayString = "";

    public CustomWikiDisplay(Article article)
    {
        this.article = article;
    }

    public String toString()
    {
        if(article.getTitle() == null)
        {
            return("There was no article found with that title. Please try again.");
        }

        displayString += article.getChanges() + article.getRedirection();

        return(displayString);
    }
}
