package cn.xyz.chaos.examples.demo.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.xyz.chaos.examples.demo.provider.AttributeProvider;
import cn.xyz.chaos.examples.demo.provider.dto.AttributeDTO;

@Service
public class AttributeService {

    @Autowired
    private AttributeProvider attrProvider;

    public Map<String, Object> list(AttributeDTO queryDTO) {
        return attrProvider.pageList(queryDTO);
    }

    public AttributeDTO detail(Integer id) {
        return this.attrProvider.get(id);
    }

    public void add(AttributeDTO attrDTO) {
        this.attrProvider.add(attrDTO);
    }

    public void update(AttributeDTO attrDTO) {
        this.attrProvider.update(attrDTO);
    }

    public void delete(Integer id) {
        this.attrProvider.delete(id);
    }

    public String getTagAttrs(Integer tagId) {
        List<AttributeDTO> attrDTOList = this.attrProvider.getByTagId(tagId);
        StringBuilder sb = new StringBuilder("[");
        if (!CollectionUtils.isEmpty(attrDTOList)) {
            for (int i = 0; i < attrDTOList.size(); i++) {
                sb.append("{\"attrId\":\"" + attrDTOList.get(i).getSkuOptionId() + "\",\"attrName\":\""
                        + attrDTOList.get(i).getOptionName() + "\"}");
                if (i != attrDTOList.size() - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
