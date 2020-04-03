package cn.hctech2006.micro.microueditor.service.impl;

import cn.hctech2006.micro.microueditor.bean.SysType;
import cn.hctech2006.micro.microueditor.mapper.SysTypeMapper;
import cn.hctech2006.micro.microueditor.page.MybatisPageHelper;
import cn.hctech2006.micro.microueditor.page.PageRequest;
import cn.hctech2006.micro.microueditor.page.PageResult;
import cn.hctech2006.micro.microueditor.service.SysTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysTypeServiceImpl implements SysTypeService {
    @Autowired
    private SysTypeMapper sysTypeMapper;
    @Override
    public int save(SysType record) {
        return sysTypeMapper.insert(record);
    }

    @Override
    public int delete(SysType record) {
        return 0;
    }

    @Override
    public int delete(List<SysType> records) {
        return 0;
    }

    @Override
    public SysType findById(Long id) {
        return sysTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysTypeMapper,"selectAll", pageRequest.getParam("sysType"));
    }

    @Override
    public int update(SysType record) {
        return sysTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysType> records) {
        return 0;
    }

    @Override
    public List<SysType> findAll(SysType records) {
        return sysTypeMapper.selectAll(records);
    }


}
