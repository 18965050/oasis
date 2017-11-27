package cn.xyz.chaos.jakarta.common.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String name;
    private Author author;
    private List<Chapter> chapters = new ArrayList<Chapter>();

    public Book() {
    }

    public Book(String pName, Author pAuthor) {
        setName(pName);
        setAuthor(pAuthor);
    }

    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author pAuthor) {
        author = pAuthor;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> list) {
        chapters = list;
    }
}
