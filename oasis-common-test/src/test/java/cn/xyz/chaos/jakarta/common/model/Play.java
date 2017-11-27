package cn.xyz.chaos.jakarta.common.model;

import java.util.ArrayList;
import java.util.Collection;

public class Play {

    private String name;
    private String author;
    private String genre;
    private String summary;
    private int year;
    private String language;
    private Collection characters = new ArrayList();

    public String getAuthor() {
        return author;
    }

    public Collection getCharacters() {
        return characters;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public int getYear() {
        return year;
    }

    public String getLanguage() {
        return language;
    }

    public void setAuthor(String pAuthor) {
        author = pAuthor;
    }

    public void setCharacters(Collection pCharacters) {
        characters = pCharacters;
    }

    public void setGenre(String pGenre) {
        genre = pGenre;
    }

    public void setName(String pName) {
        name = pName;
    }

    public void setSummary(String pSummary) {
        summary = pSummary;
    }

    public void setYear(int pYear) {
        year = pYear;
    }

    public void setLanguage(String pLanguage) {
        language = pLanguage;
    }

    public void addCharacter(Character pCharacter) {
        characters.add(pCharacter);
    }

}
