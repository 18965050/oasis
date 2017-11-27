package cn.xyz.chaos.examples.demo.provider;

import java.util.List;
import java.util.Map;

import cn.xyz.chaos.examples.demo.provider.dto.TagDTO;

public interface TagProvider {

    List<TagDTO> list();

    Map<String, Object> pageList(TagDTO queryDTO);

    void add(TagDTO tagDTO);

    void update(TagDTO tagDTO);

    TagDTO get(Integer id);

    void delete(Integer id);

}
