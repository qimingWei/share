package com.share.api.convert;

import com.alibaba.fastjson.JSON;
import com.share.api.entity.Work;
import com.share.api.entity.WorkMediaSource;
import com.share.api.vo.WorkMediaSourceVO;
import com.share.api.vo.WorkVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class WorkMediaSourceConvert {


    public static WorkMediaSourceVO toVO(WorkMediaSource entity) {
        WorkMediaSourceVO vo = new WorkMediaSourceVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    public static WorkMediaSource toEntity(WorkMediaSourceVO vo) {
        WorkMediaSource workMediaSource = new WorkMediaSource();
        BeanUtils.copyProperties(vo, workMediaSource);
        return workMediaSource;
    }


}
