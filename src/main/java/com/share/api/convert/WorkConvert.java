package com.share.api.convert;

import com.alibaba.fastjson.JSON;
import com.share.api.entity.Work;
import com.share.api.vo.WorkVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class WorkConvert {


    public static WorkVO toVO(Work entity) {
        WorkVO vo = new WorkVO();
        BeanUtils.copyProperties(entity, vo);
        if (!StringUtils.isEmpty(entity.getPages())) {
            vo.setPages(JSON.parseArray(entity.getPages()));
        }
        return vo;
    }

    public static Work toEntity(WorkVO vo) {
        Work work = new Work();
        BeanUtils.copyProperties(vo, work);
        if (!CollectionUtils.isEmpty(vo.getPages())) {
            work.setPages(JSON.toJSONString(vo.getPages()));
        }
        return work;
    }


}
