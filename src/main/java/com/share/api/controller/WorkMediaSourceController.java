package com.share.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.share.api.convert.WorkMediaSourceConvert;
import com.share.api.dto.WorkMediaSourceCreateDTO;
import com.share.api.dto.WorkMediaSourceQueryDTO;
import com.share.api.dto.WorkMediaSourceUpdateDTO;
import com.share.api.service.WorkMediaSourceService;
import com.share.api.vo.WorkMediaSourceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author：weiqiming
 * @Description：媒体资源管理
 * @Date：2020/5/7 13:59
 **/
@Slf4j
@RestController
@RequestMapping("/workMediaSource")
@RequiredArgsConstructor
@Api(value = "媒体资源管理", tags = {"媒体资源管理"})
public class WorkMediaSourceController {

    private final WorkMediaSourceService workMediaSourceService;

    @ApiOperation(value = "根据ID媒体资源", httpMethod = "POST", notes = "根据ID查询媒体资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "work_id", value = "媒体资源ID", dataType = "Long", required = true, paramType = "query")
    })
    @PostMapping("/getMediaById")
    public WorkMediaSourceVO findWorkMediaSourceById(@RequestParam("id") Long id) {
        return WorkMediaSourceConvert.toVO(workMediaSourceService.getById(id));
    }

    @ApiOperation(value = "分页查询媒体资源列表", httpMethod = "POST", notes = "分页查询媒体资源列表")
    @PostMapping("/pageable")
    public IPage<WorkMediaSourceVO> listWorkMediaSources(@Valid WorkMediaSourceQueryDTO dto) {
        return workMediaSourceService.listWorkMediaSources(dto);
    }

    @ApiOperation(value = "创建媒体资源", httpMethod = "POST", notes = "创建媒体资源")
    @PostMapping("/save")
    public WorkMediaSourceVO createWorkMediaSource(@Valid @RequestBody WorkMediaSourceCreateDTO dto) {
        return workMediaSourceService.createWorkMediaSource(dto);
    }

    @ApiOperation(value = "更新媒体资源", httpMethod = "POST", notes = "更新媒体资源")
    @PostMapping("/update")
    public WorkMediaSourceVO updateWorkMediaSource(@RequestParam("objectId") String objectId, @Valid @RequestBody WorkMediaSourceUpdateDTO dto) {
        return workMediaSourceService.updateWorkMediaSource(objectId, dto);
    }


    @ApiOperation(value = "删除媒体资源", httpMethod = "POST", notes = "删除媒体资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "媒体资源ID", dataType = "Long", required = true, paramType = "query")
    })
    @PostMapping("/remove")
    public void deleteWorkMediaSourceById(@RequestParam("id") Long id) {
        workMediaSourceService.removeById(id);
    }


    @ApiOperation(value = "统计总媒体资源数", httpMethod = "POST", notes = "统计总媒体资源数")
    @PostMapping("/count")
    public int countWorkMediaSource() {
        return workMediaSourceService.count();
    }

}
