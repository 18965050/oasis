package cn.xyz.chaos.guava.common.base.predicate;

import cn.xyz.chaos.guava.common.model.Region;
import cn.xyz.chaos.guava.common.model.State;

import com.google.common.base.Predicate;

/**
 *
 * @author lvchenggang
 *
 */
public class MidwestOrSouthwestRegionPredicate implements Predicate<State> {

    @Override
    public boolean apply(State input) {
        return input.getRegion().equals(Region.MIDWEST) || input.getRegion().equals(Region.SOUTHWEST);
    }
}
