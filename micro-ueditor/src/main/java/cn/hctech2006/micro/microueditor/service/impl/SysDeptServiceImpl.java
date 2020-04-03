package cn.hctech2006.micro.microueditor.service.impl;

import cn.hctech2006.micro.microueditor.bean.SysDept;
import cn.hctech2006.micro.microueditor.mapper.SysDeptMapper;
import cn.hctech2006.micro.microueditor.page.MybatisPageHelper;
import cn.hctech2006.micro.microueditor.page.PageRequest;
import cn.hctech2006.micro.microueditor.page.PageResult;
import cn.hctech2006.micro.microueditor.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Override
    public int save(SysDept record) {
        return sysDeptMapper.insert(record);
    }

    @Override
    public int delete(SysDept record) {
        return 0;
    }

    @Override
    public int delete(List<SysDept> records) {
        return 0;
    }

    @Override
    public SysDept findById(Long id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysDeptMapper,
                "selectAll",pageRequest.getParam("sysDept"));

    }

    @Override
    public List<SysDept> findRootTree() {
        SysDept sysDept = new SysDept();
        sysDept.setDelFlag((byte)0);
        return sysDeptMapper.selectAll(sysDept);
    }

    @Override
    public List<SysDept> findByParentId(Long parentId) {
        return sysDeptMapper.selectDeptByParentId(parentId);
    }

    @Override
    public List<SysDept> findAll(SysDept records) {
        return sysDeptMapper.selectAll(records);
    }

    @Override
    public int update(SysDept record) {
        return sysDeptMapper.updateByPrimaryKey(record);
    }

    @Override
    public int update(List<SysDept> records) {
        return 0;
    }
}
