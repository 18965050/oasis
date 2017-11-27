#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package}.repository.mapper;

import ${package}.entity.criteria.UserCriteria;import ${package}.entity.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

@MyBatisRepository
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    int countByCriteria(UserCriteria criteria);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    int deleteByCriteria(UserCriteria criteria);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    List<User> selectByCriteriaWithRowbounds(UserCriteria criteria, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    List<User> selectByCriteria(UserCriteria criteria);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    User selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    int updateByCriteriaSelective(@Param("record") User record, @Param("example") UserCriteria criteria);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    int updateByCriteria(@Param("record") User record, @Param("example") UserCriteria criteria);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table ACC_USER
     *
     * @mbggenerated Mon Jul 28 13:47:12 CST 2014
     */
    int updateByPrimaryKey(User record);
}
