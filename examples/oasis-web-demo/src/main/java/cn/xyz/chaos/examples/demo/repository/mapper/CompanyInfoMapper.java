package cn.xyz.chaos.examples.demo.repository.mapper;

import cn.xyz.chaos.examples.demo.entity.CompanyInfo;
import cn.xyz.chaos.examples.demo.entity.criteria.CompanyInfoCriteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

@MyBatisRepository
public interface CompanyInfoMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    int countByCriteria(CompanyInfoCriteria criteria);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    int deleteByCriteria(CompanyInfoCriteria criteria);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    int deleteByPrimaryKey(Integer comId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    int insert(CompanyInfo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    int insertSelective(CompanyInfo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    List<CompanyInfo> selectByCriteriaWithRowbounds(CompanyInfoCriteria criteria, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    List<CompanyInfo> selectByCriteria(CompanyInfoCriteria criteria);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    CompanyInfo selectByPrimaryKey(Integer comId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    int updateByCriteriaSelective(@Param("record") CompanyInfo record, @Param("example") CompanyInfoCriteria criteria);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    int updateByCriteria(@Param("record") CompanyInfo record, @Param("example") CompanyInfoCriteria criteria);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    int updateByPrimaryKeySelective(CompanyInfo record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table company_info
     *
     * @mbggenerated Mon Aug 25 10:22:14 CST 2014
     */
    int updateByPrimaryKey(CompanyInfo record);
}