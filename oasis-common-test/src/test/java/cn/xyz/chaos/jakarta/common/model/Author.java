package cn.xyz.chaos.jakarta.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Author {
    private String name;
    private String favoriteColor;

    public Author() {
    }

    public Author(String name, String favoriteColor) {
        this.name = name;
        this.favoriteColor = favoriteColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("name", name)
                .append("favoriteColor", favoriteColor).toString();
    }
}
