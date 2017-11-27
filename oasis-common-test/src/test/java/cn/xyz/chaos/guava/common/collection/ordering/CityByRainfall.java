package cn.xyz.chaos.guava.common.collection.ordering;

import java.util.Comparator;

import cn.xyz.chaos.guava.common.model.City;

import com.google.common.primitives.Doubles;

/**
 *
 * @author lvchenggang
 *
 */
public class CityByRainfall implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        return Doubles.compare(city1.getAverageRainfall(), city2.getAverageRainfall());
    }
}
