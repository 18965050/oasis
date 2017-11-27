package cn.xyz.chaos.examples.demo.provider;

import java.util.List;
import java.util.Map;

import cn.xyz.chaos.examples.demo.provider.dto.CommentDTO;

public interface CommentProvider {

    Map<String, Object> pageList(CommentDTO queryDTO);

    List<CommentDTO> getByProdId(Integer prodId);

    List<CommentDTO> selectBySelective(CommentDTO commentDTO);

    void add(CommentDTO commentDTO);
}
