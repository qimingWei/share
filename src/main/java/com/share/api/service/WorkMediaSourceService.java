package com.share.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.share.api.dto.WorkMediaSourceCreateDTO;
import com.share.api.dto.WorkMediaSourceQueryDTO;
import com.share.api.dto.WorkMediaSourceUpdateDTO;
import com.share.api.entity.WorkMediaSource;
import com.share.api.vo.WorkMediaSourceVO;

public interface WorkMediaSourceService extends IService<WorkMediaSource> {

    IPage<WorkMediaSourceVO> listWorkMediaSources(WorkMediaSourceQueryDTO dto);

    WorkMediaSourceVO createWorkMediaSource(WorkMediaSourceCreateDTO dto);

    WorkMediaSourceVO updateWorkMediaSource(String objectId, WorkMediaSourceUpdateDTO dto);

}
