package cn.xyz.chaos.examples.demo.provider.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.entity.UserInfo;
import cn.xyz.chaos.examples.demo.provider.UserInfoProvider;
import cn.xyz.chaos.examples.demo.provider.consts.UserInfoConst;
import cn.xyz.chaos.examples.demo.provider.dto.UserInfoDTO;
import cn.xyz.chaos.examples.demo.repository.UserInfoRepository;
import cn.xyz.chaos.examples.demo.repository.dao.UserInfoDAO;

/**
 * AccountProviderImpl <br/>
 * Account服务实现
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月24日 下午6:59:07
 * @author lcg
 */
@Service
public class UserInfoProviderImpl implements UserInfoProvider {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoDAO userDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public UserInfoDTO add(UserInfoDTO userInfo) {
        if (null == userInfo) {
            return null;
        }
        Date now = new Date();
        userInfo.setStatus(UserInfoConst.STATUS_VALID);
        userInfo.setAddTime(now);
        userInfo.setUpdateTime(now);
        UserInfoDTO addedDto = userInfoRepository.add(userInfo);
        return addedDto;
    }

    @Override
    public UserInfoDTO getByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return null;
        }
        return userInfoRepository.getByEmail(email);
    }

    @Override
    public UserInfoDTO getById(int id) {
        userInfoRepository.getById(id);
        return null;
    }

    @Override
    public boolean isEmailExist(String email) {
        UserInfoDTO userInfo = getByEmail(email);
        return null != userInfo;
    }

    @Override
    public List<UserInfoDTO> list() {
        List<UserInfo> userInfoList = this.userDAO.selectByCriteria(null);
        List<UserInfoDTO> userDTOList = new ArrayList<UserInfoDTO>();
        if (!CollectionUtils.isEmpty(userInfoList)) {
            for (UserInfo userInfo : userInfoList) {
                userDTOList.add(this.dozerMapper.map(userInfo, UserInfoDTO.class, "userInfo2userInfoDTO"));
            }
        }
        return userDTOList;
    }

}
