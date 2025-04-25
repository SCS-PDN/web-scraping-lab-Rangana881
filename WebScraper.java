import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
    public class Main {
        public static void main(String[] args) {
            String url = "https://www.bbc.com";

            try {
                Document doc = Jsoup.connect(url).get();
                System.out.println("Title : " +"\n"+ doc.title());
                System.out.println("\nHeadings:");
                Elements headings = doc.select("h1, h2, h3, h4, h5, h6");
                for (Element heading : headings) {
                    System.out.println(heading.text());
                }

                System.out.println("\nLinks:");
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    System.out.println(link.attr("abs:href"));
                }

                // Part 2: Extract news articles and store in a list
                List<NewsArticle> newsArticles = new ArrayList<>();
                // Adjust the CSS selector based on the current structure of BBC's homepage
                Elements newsElements = doc.select("div.gs-c-promo");

                for (Element element : newsElements) {
                    String headline = element.select("h3.gs-c-promo-heading__title").text();
                    String date = element.select("div.sc-ac6bc755-0 kOnnpG > span").text();
                    String author = element.select("div.sc-801dd632-5 kRoBHa > span").text();

                    newsArticles.add(new NewsArticle(headline, date, author));
                }

                // Print the collected news articles
                System.out.println("\nNews Articles:");
                for (NewsArticle article : newsArticles) {
                    System.out.println(article);
                }

            } catch (IOException e) {
                System.err.println("Error fetching the webpage: " + e.getMessage());
            }

        }

        static class NewsArticle {
            private String headline;
            private String publicationDate;
            private String author;

            public NewsArticle(String headline, String publicationDate, String author) {
                this.headline = headline;
                this.publicationDate = publicationDate;
                this.author = author;
            }

            public String getHeadline() {
                return headline;
            }

            public void setHeadline(String headline) {
                this.headline = headline;
            }

            public String getPublicationDate() {
                return publicationDate;
            }

            public void setPublicationDate(String publicationDate) {
                this.publicationDate = publicationDate;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            @Override
            public String toString() {
                return
                        "Headline : " + headline +"\t"+
                                "Publication Date : " + publicationDate +"\t"+
                                "Author : " + author + "\n";
            }
        }
    }
