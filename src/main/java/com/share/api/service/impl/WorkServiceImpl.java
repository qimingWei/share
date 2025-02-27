package com.share.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.api.convert.WorkConvert;
import com.share.api.dao.WorkDao;
import com.share.api.dto.WorkCreateDTO;
import com.share.api.dto.WorkQueryDTO;
import com.share.api.dto.WorkUpdateDTO;
import com.share.api.entity.Work;
import com.share.api.service.WorkService;
import com.share.api.vo.WorkVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkServiceImpl extends ServiceImpl<WorkDao, Work> implements WorkService {


    @Override
    public List<WorkVO> listAllWorks(WorkQueryDTO dto) {
        WorkVO vo = new WorkVO();
        BeanUtils.copyProperties(dto, vo);
        return this.list(new QueryWrapper<>(WorkConvert.toEntity(vo))).stream().map(WorkConvert::toVO).collect(Collectors.toList());
    }

    @Override
    public List<WorkVO> listAllWorksBase(WorkQueryDTO dto) {
        WorkVO vo = new WorkVO();
        BeanUtils.copyProperties(dto, vo);
        return this.list(new QueryWrapper<>(WorkConvert.toEntity(vo))).stream().map(WorkConvert::toVO).collect(Collectors.toList());
    }

    @Override
    public IPage<WorkVO> listWorks(WorkQueryDTO dto) {
        WorkVO vo = new WorkVO();
        BeanUtils.copyProperties(dto, vo);
        IPage<Work> page = this.page(new Page<>(dto.getCurrent(), dto.getSize()), new LambdaQueryWrapper<>(WorkConvert.toEntity(vo)).orderByDesc(Work::getCreateTime));
        List<WorkVO> voList = page.getRecords().stream().map(WorkConvert::toVO).collect(Collectors.toList());
        return new Page<WorkVO>(page.getCurrent(), page.getSize(), page.getTotal()).setPages(page.getPages()).setRecords(voList);
    }

    @Override
    public WorkVO createWork(WorkCreateDTO dto) {
        WorkVO vo = new WorkVO();
        BeanUtils.copyProperties(dto, vo);
        Work work = WorkConvert.toEntity(vo);
        this.save(work);
        return WorkConvert.toVO(work);
    }

    @Override
    public WorkVO updateWork(Long work_id, WorkUpdateDTO dto) {
        Work work = this.getById(work_id);
        if (!StringUtils.isEmpty(dto.getTitle())) {
            work.setTitle(dto.getTitle());
        }
        if (!StringUtils.isEmpty(dto.getDescription())) {
            work.setDescription(dto.getDescription());
        }
        if (!StringUtils.isEmpty(dto.getCoverImageUrl())) {
            work.setCoverImageUrl(dto.getCoverImageUrl());
        }
        if (!StringUtils.isEmpty(dto.getQrCodeGenerator())) {
            work.setQrCodeGenerator(dto.getQrCodeGenerator());
        }
        if (!CollectionUtils.isEmpty(dto.getPages())) {
            work.setPages(JSON.toJSONString(dto.getPages()));
        }
        if (null != dto.getIsPublish() && work.getIsPublish().intValue() != dto.getIsPublish().intValue()) {
            work.setIsPublish(dto.getIsPublish());
        }
        if (null != dto.getIsTemplate() && work.getIsTemplate().intValue() != dto.getIsTemplate().intValue()) {
            work.setIsTemplate(dto.getIsTemplate());
        }
        if (null != dto.getIsDownload() && work.getIsDownload().intValue() != dto.getIsDownload().intValue()) {
            work.setIsDownload(dto.getIsDownload());
        }
        work.setUpdateTime(LocalDateTime.now());
        this.saveOrUpdate(work);
        return WorkConvert.toVO(work);
    }

    @Override
    public WorkVO markWorkAsTemplate(Long work_id) {
        Work work = this.getById(work_id);
        work.setId(null);
        work.setIsTemplate(1);
        work.setIsPublish(0);
        work.setUpdateTime(LocalDateTime.now());
        this.save(work);
        return WorkConvert.toVO(work);
    }

    @Override
    public WorkVO useTemplate(Long work_id) {
        Work work = this.getById(work_id);
        work.setId(null);
        work.setIsTemplate(0);
        work.setIsTemplate(1);
        this.save(work);
        return WorkConvert.toVO(work);
    }
}
