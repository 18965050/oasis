package cn.xyz.chaos.guava.common.collection.ordering;

import java.util.Comparator;

import cn.xyz.chaos.guava.common.model.City;

import com.google.common.primitives.Ints;

/**
 *
 * @author lvchenggang
 *
 */
public class CityByPopulation implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        return Ints.compare(city1.getPopulation(), city2.getPopulation());
    }
}
