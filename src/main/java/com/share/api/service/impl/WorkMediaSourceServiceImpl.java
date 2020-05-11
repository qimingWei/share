package com.share.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.api.convert.WorkMediaSourceConvert;
import com.share.api.dao.WorkMediaSourceDao;
import com.share.api.dto.WorkMediaSourceCreateDTO;
import com.share.api.dto.WorkMediaSourceQueryDTO;
import com.share.api.dto.WorkMediaSourceUpdateDTO;
import com.share.api.entity.WorkMediaSource;
import com.share.api.service.WorkMediaSourceService;
import com.share.api.vo.WorkMediaSourceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkMediaSourceServiceImpl extends ServiceImpl<WorkMediaSourceDao, WorkMediaSource> implements WorkMediaSourceService {

    @Override
    public IPage<WorkMediaSourceVO> listWorkMediaSources(WorkMediaSourceQueryDTO dto) {
        WorkMediaSourceVO vo = new WorkMediaSourceVO();
        BeanUtils.copyProperties(dto, vo);
        IPage<WorkMediaSource> page = this.page(new Page<>(dto.getCurrent(), dto.getSize()), new LambdaQueryWrapper<>(WorkMediaSourceConvert.toEntity(vo)).orderByDesc(WorkMediaSource::getCreateTime));
        List<WorkMediaSourceVO> voList = page.getRecords().stream().map(WorkMediaSourceConvert::toVO).collect(Collectors.toList());
        return new Page<WorkMediaSourceVO>(page.getCurrent(), page.getSize(), page.getTotal()).setPages(page.getPages()).setRecords(voList);
    }

    @Override
    public WorkMediaSourceVO createWorkMediaSource(WorkMediaSourceCreateDTO dto) {
        WorkMediaSourceVO vo = new WorkMediaSourceVO();
        BeanUtils.copyProperties(dto, vo);
        WorkMediaSource workMediaSource = WorkMediaSourceConvert.toEntity(vo);
        this.save(workMediaSource);
        return WorkMediaSourceConvert.toVO(workMediaSource);
    }

    @Override
    public WorkMediaSourceVO updateWorkMediaSource(String objectId, WorkMediaSourceUpdateDTO dto) {
        WorkMediaSource workMediaSource = this.getById(objectId);
        if (!StringUtils.isEmpty(dto.getBucket_name())) {
            workMediaSource.setBucket_name(dto.getBucket_name());
        }
        if (!StringUtils.isEmpty(dto.getObject_name())) {
            workMediaSource.setObject_name(dto.getObject_name());
        }
        if (!StringUtils.isEmpty(dto.getContent_type())) {
            workMediaSource.setContent_type(dto.getContent_type());
        }
        if (!StringUtils.isEmpty(dto.getDescription())) {
            workMediaSource.setDescription(dto.getDescription());
        }
        if (null != dto.getIsDel() && workMediaSource.getIsDel().intValue() != dto.getIsDel().intValue()) {
            workMediaSource.setIsDel(dto.getIsDel());
        }
        workMediaSource.setUpdateTime(dto.getUpdateTime());
        this.saveOrUpdate(workMediaSource);
        return WorkMediaSourceConvert.toVO(workMediaSource);
    }
}
