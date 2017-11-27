package cn.xyz.chaos.jakarta.common.model;

import java.util.HashMap;
import java.util.Map;

public class Chapter {

    private String title;
    private Map<String, String> sections = new HashMap<String, String>();

    public Chapter() {
    }

    public Chapter(String title) {
        this.title = title;
    }

    public void addSection(String sectionName, String summary) {
        sections.put(sectionName, summary);
    }

    public Map<String, String> getSections() {
        return sections;
    }

    public void setSections(Map<String, String> map) {
        sections = map;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String string) {
        title = string;
    }

}
