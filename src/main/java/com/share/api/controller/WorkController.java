package com.share.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.share.api.convert.WorkConvert;
import com.share.api.dto.WorkCreateDTO;
import com.share.api.dto.WorkQueryDTO;
import com.share.api.dto.WorkUpdateDTO;
import com.share.api.service.WorkService;
import com.share.api.vo.WorkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        return workService.listAllWorks(dto);
    }

    @ApiOperation(value = "查询基础数据", httpMethod = "POST", notes = "查询基础数据")
    @PostMapping("/has-forms")
    public List<WorkVO> listAllWorksBase(@Valid WorkQueryDTO dto) {
        return workService.listAllWorksBase(dto);
    }

    @ApiOperation(value = "分页查询作品列表", httpMethod = "POST", notes = "分页查询作品列表")
    @PostMapping("/pageable")
    public IPage<WorkVO> listWorks(@Valid WorkQueryDTO dto) {
        return workService.listWorks(dto);
    }

    @ApiOperation(value = "创建作品", httpMethod = "POST", notes = "创建作品")
    @PostMapping("/save")
    public WorkVO createWork(@Valid @RequestBody WorkCreateDTO dto) {
        return workService.createWork(dto);
    }

    @ApiOperation(value = "更新作品", httpMethod = "POST", notes = "更新作品")
    @PostMapping("/update")
    public WorkVO updateWork(@RequestParam("work_id") Long work_id, @Valid @RequestBody WorkUpdateDTO dto) {
        return workService.updateWork(work_id, dto);
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
        return workService.markWorkAsTemplate(work_id);
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
        return workService.useTemplate(work_id);
    }
}
