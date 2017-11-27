package cn.xyz.chaos.guava.common.base.predicate;

import cn.xyz.chaos.guava.common.model.City;
import cn.xyz.chaos.guava.common.model.Climate;

import com.google.common.base.Predicate;

/**
 *
 * @author lvchenggang
 *
 */
public class TemperateClimatePredicate implements Predicate<City> {

    @Override
    public boolean apply(City input) {
        return input.getClimate().equals(Climate.TEMPERATE);
    }
}
