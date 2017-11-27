package cn.xyz.chaos.examples.demo.provider;

import java.util.List;
import java.util.Map;

import cn.xyz.chaos.examples.demo.provider.dto.CompanyDTO;

public interface CompanyProvider {

    List<CompanyDTO> list();

    Map<String, Object> pageList(CompanyDTO queryDTO);

    void add(CompanyDTO companyDTO);

    void update(CompanyDTO companyDTO);

    CompanyDTO get(Integer id);

    void delete(Integer id);

}
