package cn.xyz.chaos.examples.demo.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.xyz.chaos.examples.demo.entity.Appraise;
import cn.xyz.chaos.examples.demo.entity.Comment;
import cn.xyz.chaos.examples.demo.entity.criteria.AppraiseCriteria;
import cn.xyz.chaos.examples.demo.provider.CommentProvider;
import cn.xyz.chaos.examples.demo.provider.dto.CommentDTO;
import cn.xyz.chaos.examples.demo.repository.dao.AppraiseDAO;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@Repository
public class CommentProviderImpl implements CommentProvider {

    @Autowired
    private AppraiseDAO appraiseDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public Map<String, Object> pageList(CommentDTO queryDTO) {

        Map<String, Object> map = new HashMap<String, Object>();

        PageBounds pageBounds = new PageBounds(queryDTO.getLimit(), queryDTO.getPageIndex());

        Appraise queryInfo = dozerMapper.map(queryDTO, Appraise.class, "commentDTO2appraise");

        PageList<Comment> commentList = this.appraiseDAO.selectBySelectiveWithRowbounds(queryInfo, pageBounds);

        map.put("currentPage", commentList.getPaginator().getPage());
        map.put("totalPage", commentList.getPaginator().getTotalPages());

        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        if (!CollectionUtils.isEmpty(commentList)) {
            for (Comment comment : commentList) {
                commentDTOList.add(this.dozerMapper.map(comment, CommentDTO.class, "comment2commentDTO"));
            }
        }
        map.put("commentList", commentDTOList);
        return map;
    }

    @Override
    public List<CommentDTO> getByProdId(Integer prodId) {
        List<Comment> commentList = this.appraiseDAO.selectByProdId(prodId);
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        if (CollectionUtils.isNotEmpty(commentList)) {
            for (Comment comment : commentList) {
                commentDTOList.add(this.dozerMapper.map(comment, CommentDTO.class, "comment2commentDTO"));
            }
        }
        return commentDTOList;
    }

    @Override
    public List<CommentDTO> selectBySelective(CommentDTO commentDTO) {
        AppraiseCriteria appraiseCriteria = new AppraiseCriteria();
        AppraiseCriteria.Criteria criteria = appraiseCriteria.or();
        if (commentDTO.getAppraiseId() != null) {
            criteria.andAppraiseIdEqualTo(commentDTO.getAppraiseId());
        }
        if (commentDTO.getProdId() != null) {
            criteria.andProdIdEqualTo(commentDTO.getProdId());
        }
        if (commentDTO.getUserId() != null) {
            criteria.andUserIdEqualTo(commentDTO.getUserId());
        }

        List<Appraise> appraiseList = this.appraiseDAO.selectByCriteria(appraiseCriteria);
        List<CommentDTO> commentDTOList = new ArrayList<CommentDTO>();
        if (CollectionUtils.isNotEmpty(appraiseList)) {
            for (Appraise appraise : appraiseList) {
                commentDTOList.add(this.dozerMapper.map(appraise, CommentDTO.class, "appraise2commentDTO"));
            }
        }

        return commentDTOList;
    }

    @Override
    public void add(CommentDTO commentDTO) {
        Appraise appraise = this.dozerMapper.map(commentDTO, Appraise.class, "commentDTO2appraise");
        this.appraiseDAO.insertSelective(appraise);
    }
}
