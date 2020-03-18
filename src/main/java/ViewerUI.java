import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import domain.Article;
import domain.JsonRetriever;
import domain.CustomWikiDisplay;
import exceptions.ParameterIsNotJsonStringException;
import utils.ParseUtils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class ViewerUI extends JFrame{
    JLabel displayLabel;

    public ViewerUI() {
        super("Wikipedia Revisions Veiwer");

        // Make fonts bigger in the whole app
        UIManager.put("Label.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 15)));
        UIManager.put("Button.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 12)));

        JPanel panel = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        setContentPane(panel);

        JLabel instructionLabel = new JLabel("Please type the title of a Wikipedia article title.");
        var instructionLabelConstraints = new GridBagConstraints(0, 0, 4, 1, 1,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(instructionLabel, instructionLabelConstraints);

        JTextField userInput = new JTextField("Enter article name");
        var userInputConstraints = new GridBagConstraints(0, 1, 1, 1, 0,0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(userInput, userInputConstraints);

        JTextField results = new JTextField("Results");
        var resultsLabelConstraints = new GridBagConstraints(0, 3, 4, 2, 1,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(results, resultsLabelConstraints);

        JLabel articleTitle = new JLabel("---");
        var articleTitleConstraints = new GridBagConstraints(1, 2, 1, 1, 1,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(articleTitle, articleTitleConstraints);

        JButton searchButton = new JButton("fetch!");
        var searchButtonConstraints = new GridBagConstraints(1, 1, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        JLabel finalArticleTitle = articleTitle;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wikiSearch = userInput.getText();
                JsonRetriever retriever = new JsonRetriever(wikiSearch);
                String jsonString = null;
                try {
                    jsonString = retriever.search();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ParseUtils parser = new ParseUtils();
                try {
                    Article article = parser.parseJsonToArticle(jsonString);
                    CustomWikiDisplay wikiDisplay = new CustomWikiDisplay(article);
                    /*
                    JsonParser jsonParser = new JsonParser();
                    JsonElement rootElement = jsonParser.parse(jsonString);
                    JsonObject rootObject = rootElement.getAsJsonObject();
                    JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
                    JsonArray array;
                    for (Map.Entry<String, JsonElement> entry : pages.entrySet())
                    {
                        JsonObject entryObject = entry.getValue().getAsJsonObject();
                        Object title = entryObject.getAsJsonPrimitive("title").getAsString();
                        array = entryObject.getAsJsonArray("revisions");
                        for (int i = 0; i < array.size(); i++)
                        {
                            JsonObject individualRevisionObject = array.get(i).getAsJsonObject();
                            Object username = individualRevisionObject.getAsJsonPrimitive("user").getAsString();
                            Object time = individualRevisionObject.getAsJsonPrimitive("timestamp").getAsString();
                        }
                    }
                     */
                    results.setText(wikiDisplay.toString());
                    ///JLabel articleTitle = new JLabel();
                    finalArticleTitle.setText(wikiDisplay.titleToString());
                } catch (ParameterIsNotJsonStringException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(searchButton, searchButtonConstraints);

        JLabel titleLabel = new JLabel("Title of Article:");
        var titleLabelConstraints = new GridBagConstraints(0, 2, 1, 1, 1,1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(titleLabel, titleLabelConstraints);


/*
        JLabel instructionText = new JLabel("Please type the title of a Wikipedia article title.");
        var instructionTextConstraints = new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0);
        panel.add(instructionText, instructionTextConstraints);

        JTextField userSearch = new JTextField("Wikipedia Article Title");
        var userSearchConstraints = new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(50, 5, 5, 5), 0, 0);
        panel.add(userSearch, userSearchConstraints);
 */

        setPreferredSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewerUI();
    }

}
