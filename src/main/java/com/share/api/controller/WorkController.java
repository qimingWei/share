package com.share.api.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.share.api.convert.WorkConvert;
import com.share.api.dto.WorkCreateDTO;
import com.share.api.dto.WorkQueryDTO;
import com.share.api.dto.WorkUpdateDTO;
import com.share.api.entity.Work;
import com.share.api.service.WorkService;
import com.share.api.vo.WorkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 * <p>
 *
 * @author weiqiming
 */
@RestController
@RequestMapping("/works")
@Api(value = "作品管理", tags = {"作品管理"})
@Slf4j
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @ApiOperation(value = "根据ID查询作品", httpMethod = "POST", notes = "根据ID查询作品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "work_id", value = "作品ID", dataType = "Long", required = true, paramType = "query")
    })
    @PostMapping("/getWorkById")
    public WorkVO findWorkById(@RequestParam("work_id") Long work_id) {
        return WorkConvert.toVO(workService.getById(work_id));
    }

    @ApiOperation(value = "查询作品列表", httpMethod = "POST", notes = "查询作品列表")
    @PostMapping
    public List<WorkVO> listAllWorks(@Valid WorkQueryDTO dto) {
        WorkVO vo = new WorkVO();
        BeanUtils.copyProperties(dto, vo);
        return workService.list(new QueryWrapper<>(WorkConvert.toEntity(vo))).stream().map(WorkConvert::toVO).collect(Collectors.toList());
    }

    @ApiOperation(value = "查询基础数据", httpMethod = "POST", notes = "查询基础数据")
    @PostMapping("/has-forms")
    public List<WorkVO> listAllWorksBase(@Valid WorkQueryDTO dto) {
        WorkVO vo = new WorkVO();
        BeanUtils.copyProperties(dto, vo);
        return workService.list(new QueryWrapper<>(WorkConvert.toEntity(vo))).stream().map(WorkConvert::toVO).collect(Collectors.toList());
    }

    @ApiOperation(value = "分页查询作品列表", httpMethod = "POST", notes = "分页查询作品列表")
    @PostMapping("/pageable")
    public IPage<WorkVO> listWorks(@Valid WorkQueryDTO dto) {
        WorkVO vo = new WorkVO();
        BeanUtils.copyProperties(dto, vo);
        IPage<Work> page = workService.page(new Page<>(dto.getCurrent(), dto.getSize()), new LambdaQueryWrapper<>(WorkConvert.toEntity(vo)).orderByDesc(Work::getCreateTime));
        List<WorkVO> voList = page.getRecords().stream().map(WorkConvert::toVO).collect(Collectors.toList());
        return new Page<WorkVO>(page.getCurrent(), page.getSize(), page.getTotal()).setPages(page.getPages()).setRecords(voList);
    }

    @ApiOperation(value = "创建作品", httpMethod = "POST", notes = "创建作品")
    @PostMapping("/save")
    public WorkVO createWork(@Valid @RequestBody WorkCreateDTO dto) {
        WorkVO vo = new WorkVO();
        BeanUtils.copyProperties(dto, vo);
        Work work = WorkConvert.toEntity(vo);
        workService.save(work);
        return WorkConvert.toVO(work);
    }

    @ApiOperation(value = "更新作品", httpMethod = "POST", notes = "更新作品")
    @PostMapping("/update")
    public WorkVO updateWork(@RequestParam("work_id") Long work_id, @Valid @RequestBody WorkUpdateDTO dto) {
        Work work = workService.getById(work_id);
        if (!StringUtils.isEmpty(dto.getTitle())) {
            work.setTitle(dto.getTitle());
        }
        if (!StringUtils.isEmpty(dto.getDescription())) {
            work.setDescription(dto.getDescription());
        }
        if (!StringUtils.isEmpty(dto.getCover_image_url())) {
            work.setCover_image_url(dto.getCover_image_url());
        }
        if (!StringUtils.isEmpty(dto.getQrCodeGenerator())){
            work.setQrCodeGenerator(dto.getQrCodeGenerator());
        }
        if (!CollectionUtils.isEmpty(dto.getPages())) {
            work.setPages(JSON.toJSONString(dto.getPages()));
        }
        if(null != dto.getIs_publish() && work.getIs_publish().intValue() != dto.getIs_publish().intValue()){
            work.setIs_publish(dto.getIs_publish());
        }
        if(null != dto.getIs_template() && work.getIs_template().intValue() != dto.getIs_template().intValue()){
            work.setIs_template(dto.getIs_template());
        }
        if(null != dto.getIs_download() && work.getIs_download().intValue() != dto.getIs_download().intValue()){
            work.setIs_download(dto.getIs_download());
        }
        work.setUpdateTime(LocalDateTime.now());
        workService.saveOrUpdate(work);
        return WorkConvert.toVO(work);
    }


    @ApiOperation(value = "删除作品", httpMethod = "POST", notes = "删除作品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "work_id", value = "作品ID", dataType = "Long", required = true, paramType = "query")
    })
    @PostMapping("/remove")
    public void deleteWorkById(@RequestParam("work_id") Long work_id) {
        workService.removeById(work_id);
    }

    @ApiOperation(value = "设置为模板", httpMethod = "POST", notes = "设置为模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "work_id", value = "作品ID", dataType = "Long", required = true, paramType = "query")
    })
    @PostMapping("/set-as-template")
    public WorkVO markWorkAsTemplate(@RequestParam("work_id") Long work_id) {
        Work work = workService.getById(work_id);
        work.setId(null);
        work.setIs_template(1);
        work.setIs_publish(0);
        work.setUpdateTime(LocalDateTime.now());
        workService.save(work);
        return WorkConvert.toVO(work);
    }

    @ApiOperation(value = "统计总作品数", httpMethod = "POST", notes = "统计总作品数")
    @PostMapping("/count")
    public int countWork() {
        return workService.count();
    }

    @ApiOperation(value = "使用模板", httpMethod = "POST", notes = "使用模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "work_id", value = "作品ID", dataType = "Long", required = true, paramType = "query")
    })
    @PostMapping("/use-template")
    public WorkVO useTemplate(@RequestParam("work_id") Long work_id) {
        Work work = workService.getById(work_id);
        work.setId(null);
        work.setIs_template(0);
        work.setIs_publish(1);
        workService.save(work);
        return WorkConvert.toVO(work);
    }
}
