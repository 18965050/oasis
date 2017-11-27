package cn.xyz.chaos.examples.demo.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.provider.CommentProvider;
import cn.xyz.chaos.examples.demo.provider.dto.CommentDTO;

@Service
public class CommentService {

    @Autowired
    private CommentProvider commentProvider;

    public Map<String, Object> list(CommentDTO queryDTO) {
        return commentProvider.pageList(queryDTO);
    }

}
