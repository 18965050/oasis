package cn.xyz.chaos.examples.demo.web.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.provider.CompanyProvider;
import cn.xyz.chaos.examples.demo.provider.dto.CompanyDTO;

@Service
public class CompanyService {

    @Autowired
    private CompanyProvider companyProvider;

    public Map<String, Object> list(CompanyDTO queryDTO) {
        return companyProvider.pageList(queryDTO);
    }

    public void add(CompanyDTO companyDTO) {
        companyDTO.setAddTime(new Date());
        this.companyProvider.add(companyDTO);
    }

    public void update(CompanyDTO companyDTO) {
        this.companyProvider.update(companyDTO);
    }

    public CompanyDTO detail(Integer id) {
        return this.companyProvider.get(id);
    }

    public void delete(Integer id) {
        this.companyProvider.delete(id);
    }
}
