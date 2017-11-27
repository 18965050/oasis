package cn.xyz.chaos.examples.demo.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.xyz.chaos.examples.demo.entity.CompanyInfo;
import cn.xyz.chaos.examples.demo.entity.criteria.CompanyInfoCriteria;
import cn.xyz.chaos.examples.demo.provider.CompanyProvider;
import cn.xyz.chaos.examples.demo.provider.dto.CompanyDTO;
import cn.xyz.chaos.examples.demo.repository.dao.CompanyInfoDAO;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@Repository
public class CompanyProviderImpl implements CompanyProvider {

    @Autowired
    private CompanyInfoDAO companyInfoDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public Map<String, Object> pageList(CompanyDTO queryDTO) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageBounds pageBounds = new PageBounds(queryDTO.getLimit(), queryDTO.getPageIndex());

        CompanyInfo queryInfo = this.dozerMapper.map(queryDTO, CompanyInfo.class, "companyDTO2companyInfo");

        CompanyInfoCriteria companyInfoCriteria = new CompanyInfoCriteria();
        CompanyInfoCriteria.Criteria criteria = companyInfoCriteria.or();

        if (StringUtils.isNotEmpty(queryInfo.getComName())) {
            criteria.andComNameLike("%" + queryInfo.getComName() + "%");
        }
        if (queryInfo.getStatus() != null) {
            criteria.andStatusEqualTo(queryInfo.getStatus());
        }

        List<CompanyInfo> companyInfoList = this.companyInfoDAO.selectByCriteriaWithRowbounds(companyInfoCriteria,
                pageBounds);

        PageList<CompanyInfo> pageList = (PageList<CompanyInfo>) companyInfoList;

        map.put("currentPage", pageList.getPaginator().getPage());
        map.put("totalPage", pageList.getPaginator().getTotalPages());

        List<CompanyDTO> companyDTOList = new ArrayList<CompanyDTO>();
        if (!CollectionUtils.isEmpty(companyInfoList)) {
            for (CompanyInfo companyInfo : companyInfoList) {
                companyDTOList.add(this.dozerMapper.map(companyInfo, CompanyDTO.class, "companyInfo2companyDTO"));
            }
        }
        map.put("companyList", companyDTOList);
        return map;
    }

    @Override
    public void add(CompanyDTO companyDTO) {
        this.companyInfoDAO.insert(this.dozerMapper.map(companyDTO, CompanyInfo.class, "companyDTO2companyInfo"));
    }

    @Override
    public void update(CompanyDTO companyDTO) {
        this.companyInfoDAO.updateByPrimaryKeySelective(
                this.dozerMapper.map(companyDTO, CompanyInfo.class, "companyDTO2companyInfo"));
    }

    @Override
    public CompanyDTO get(Integer id) {
        return this.dozerMapper.map(this.companyInfoDAO.selectByPrimaryKey(id), CompanyDTO.class,
                "companyInfo2companyDTO");
    }

    @Override
    public void delete(Integer id) {
        this.companyInfoDAO.deleteByPrimaryKey(id);
    }

    @Override
    public List<CompanyDTO> list() {
        List<CompanyInfo> companyInfoList = this.companyInfoDAO.selectAll();
        List<CompanyDTO> companyDTOList = new ArrayList<CompanyDTO>();
        if (!CollectionUtils.isEmpty(companyInfoList)) {
            for (CompanyInfo companyInfo : companyInfoList) {
                companyDTOList.add(this.dozerMapper.map(companyInfo, CompanyDTO.class, "companyInfo2companyDTO"));
            }
        }
        return companyDTOList;
    }
}
