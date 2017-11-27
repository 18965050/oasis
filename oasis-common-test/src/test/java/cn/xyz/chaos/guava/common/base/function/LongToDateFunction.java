package cn.xyz.chaos.guava.common.base.function;

import com.google.common.base.Function;

import java.util.Date;

/**
 * 
 * @author lvchenggang
 *
 */
public class LongToDateFunction implements Function<Long, Date> {

    @Override
    public Date apply(Long input) {
        Date date = new Date();
        date.setTime(input);
        return date;
    }
}
