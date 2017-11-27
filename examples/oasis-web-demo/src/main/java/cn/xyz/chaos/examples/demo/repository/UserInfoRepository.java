package cn.xyz.chaos.examples.demo.repository;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.entity.UserInfo;
import cn.xyz.chaos.examples.demo.entity.criteria.UserInfoCriteria;
import cn.xyz.chaos.examples.demo.provider.dto.UserInfoDTO;
import cn.xyz.chaos.examples.demo.repository.dao.UserInfoDAO;

import com.google.common.base.Preconditions;

/**
 * UserInfoRepository <br/>
 * TODO
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 上午9:53:06
 * @author lcg
 */
@Service
public class UserInfoRepository {

    @Autowired
    private UserInfoDAO userInfoDAO;

    /**
     * 新增一个
     *
     * @param userInfoDTO
     */
    public UserInfoDTO add(UserInfoDTO userInfoDTO) {
        Preconditions.checkNotNull(userInfoDTO, "userInfoDTO shouldn't be null!");

        UserInfo userInfo = convert(userInfoDTO);
        userInfoDAO.insert(userInfo);

        return convert(userInfo);
    }

    /**
     * POJO TO DTO
     *
     * @param userInfo
     * @return
     */
    private UserInfoDTO convert(UserInfo userInfo) {
        if (null == userInfo) {
            return null;
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDTO);
        return userInfoDTO;
    }

    /**
     * DTO TO POJO
     *
     * @param userInfoDTO
     * @return
     */
    private UserInfo convert(UserInfoDTO userInfoDTO) {
        if (null == userInfoDTO) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfo);
        return userInfo;
    }

    /**
     * @param email
     */
    public UserInfoDTO getByEmail(String email) {
        Preconditions.checkArgument(StringUtils.isNotBlank(email), "email shouldn't be blank!");

        UserInfoCriteria criteria = new UserInfoCriteria();
        criteria.or().andEmailEqualTo(email);

        List<UserInfo> result = userInfoDAO.selectByCriteria(criteria);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return convert(result.get(0));
    }

    /**
     * @param id
     */
    public UserInfoDTO getById(int id) {
        UserInfo userInfo = userInfoDAO.selectByPrimaryKey(id);
        return convert(userInfo);
    }

}
