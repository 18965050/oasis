/**
 * Author lvchenggang 
 * XYZ Reserved
 * Created on 2016年4月14日 下午4:09:30
 */
package cn.xyz.chaos.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON转换时忽略空值
 * @author lvchenggang
 *
 */
@SuppressWarnings("serial")
public class IOObjectMapper extends ObjectMapper {

	public IOObjectMapper() {
		super();
		this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

}
