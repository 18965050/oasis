package cn.xyz.chaos.guava.common.base.predicate;

import cn.xyz.chaos.guava.common.model.City;

import com.google.common.base.Predicate;

/**
 *
 * @author lvchenggang
 *
 */
public class LowRainfallPredicate implements Predicate<City> {

    @Override
    public boolean apply(City input) {
        return input.getAverageRainfall() < 45.7;
    }
}
