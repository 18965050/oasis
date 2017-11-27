package cn.xyz.chaos.guava.common.model;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * @author lvchenggang
 *
 */
public class State {

    private String name;
    private String code;
    private Set<City> mainCities = new HashSet<City>();
    private Region region;

    public State(String name, String code, Set<City> mainCities, Region region) {
        this.name = checkNotNull(name, "Name can't be null");
        this.code = checkNotNull(code, "Code can't be null");
        this.mainCities = checkNotNull(mainCities, "Cities can't be null");
        this.region = checkNotNull(region);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Region getRegion() {
        return region;
    }

    public Set<City> getMainCities() {
        return mainCities;
    }
}
