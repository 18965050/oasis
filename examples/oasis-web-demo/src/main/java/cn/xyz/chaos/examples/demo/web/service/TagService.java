package cn.xyz.chaos.examples.demo.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.provider.TagProvider;
import cn.xyz.chaos.examples.demo.provider.dto.TagDTO;

@Service
public class TagService {

    @Autowired
    private TagProvider tagProvider;

    public Map<String, Object> list(TagDTO queryDTO) {
        return tagProvider.pageList(queryDTO);
    }

    public void add(TagDTO tagDTO) {
        this.tagProvider.add(tagDTO);
    }

    public void update(TagDTO tagDTO) {
        this.tagProvider.update(tagDTO);
    }

    public TagDTO detail(Integer id) {
        return this.tagProvider.get(id);
    }

    public void delete(Integer id) {
        this.tagProvider.delete(id);
    }

}
