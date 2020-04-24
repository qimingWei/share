package com.share.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.share.api.dto.WorkCreateDTO;
import com.share.api.dto.WorkQueryDTO;
import com.share.api.dto.WorkUpdateDTO;
import com.share.api.entity.Work;
import com.share.api.vo.WorkVO;

import java.util.List;

public interface WorkService extends IService<Work> {

    List<WorkVO> listAllWorks(WorkQueryDTO dto);

    List<WorkVO> listAllWorksBase(WorkQueryDTO dto);

    IPage<WorkVO> listWorks(WorkQueryDTO dto);

    WorkVO createWork(WorkCreateDTO dto);

    WorkVO updateWork(Long work_id, WorkUpdateDTO dto);

    WorkVO markWorkAsTemplate(Long work_id);

    WorkVO useTemplate(Long work_id);
}
