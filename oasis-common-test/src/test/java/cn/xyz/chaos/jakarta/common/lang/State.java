package cn.xyz.chaos.jakarta.common.lang;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 用ReflectionToStringBuilder实现toString()方法
 *
 * @author lvchenggang
 *
 */
public class State {

    private String abbreviation;
    private String name;

    public State() {
    }

    public State(String abbreviation, String name) {
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

}
