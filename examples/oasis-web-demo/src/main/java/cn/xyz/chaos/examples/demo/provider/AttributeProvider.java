package cn.xyz.chaos.examples.demo.provider;

import java.util.List;
import java.util.Map;

import cn.xyz.chaos.examples.demo.provider.dto.AttributeDTO;

public interface AttributeProvider {

    Map<String, Object> pageList(AttributeDTO queryDTO);

    AttributeDTO get(Integer id);

    void add(AttributeDTO attrDTO);

    void update(AttributeDTO attrDTO);

    void delete(Integer id);

    List<AttributeDTO> getByTagId(Integer tagId);
}
