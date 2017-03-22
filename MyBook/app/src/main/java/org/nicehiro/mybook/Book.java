package org.nicehiro.mybook;

import java.util.ArrayList;

/**
 * Created by hiro on 17-3-17.
 */

public class Book {

    private String title;
    private ArrayList<String> author;
    private ArrayList<String> translator;
    private String price;
    private String publisher;
    private String pubdate;
    private String pages;
    private String isbn13;
    private String summary;
    private String image;

    public String getTags() {
        return tags.get(tags.size()-1).getTitle();
    }

    private ArrayList<Tag> tags;

    private class Tags {
        private ArrayList<Tag> tags;

        public String getTags() {
            return tags.get(tags.size()-1).getTitle();
        }

        public void setTags(ArrayList<Tag> tags) {
            this.tags = tags;
        }
    }

    class Tag {
        private String count;
        private String name;
        private String title;

        public void setCount(String count) {
            this.count = count;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCount() {
            return count;
        }

        public String getName() {
            return name;
        }

        public String getTitle() {
            return title;
        }
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }

    public void setTranslator(ArrayList<String> translator) {
        this.translator = translator;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getTranslator() {
        return translator;
    }

    public String getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPubdate() {
        return pubdate;
    }

    public String getPages() {
        return pages;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getSummary() {
        return summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + " Author: " + ArrayListToString.listToString(getAuthor());
    }
}
