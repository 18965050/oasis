package cn.xyz.chaos.orm.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.ConversionService;

/**
 * Created by lcg on 14/12/18 018.
 */
public class OrmContext {
    private ConversionService conversionService;

    private Map<String, Object> sessions = new HashMap<String, Object>();

    public Map<String, Object> getSessions() {
        return sessions;
    }

    public void setSessions(Map<String, Object> sessions) {
        this.sessions = sessions;
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

}
