package com.atlihao.springboot.schedule.controller.service.impl;

import com.atlihao.springboot.schedule.controller.service.DcsScheduleService;
import com.atlihao.springboot.schedule.domain.DataCollect;
import com.atlihao.springboot.schedule.domain.DcsScheduleInfo;
import com.atlihao.springboot.schedule.domain.DcsServerNode;
import com.atlihao.springboot.schedule.domain.Instruct;
import com.atlihao.springboot.schedule.export.DcsScheduleResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dcsScheduleService")
public class DcsScheduleServiceImpl implements DcsScheduleService {

    @Value("${schedule.zkAddress}")
    private String zkAddress;

    private DcsScheduleResource dcsScheduleResource = null;

    @Override
    public List<String> queryPathRootServerList() throws Exception {
        return getDcsScheduleResource().queryPathRootServerList();
    }

    @Override
    public List<DcsScheduleInfo> queryDcsScheduleInfoList(String schedulerServerId) throws Exception {
        return getDcsScheduleResource().queryDcsScheduleInfoList(schedulerServerId);
    }

    @Override
    public void pushInstruct(Instruct instruct) throws Exception {
        getDcsScheduleResource().pushInstruct(instruct);
    }

    @Override
    public DataCollect queryDataCollect() throws Exception {
        return getDcsScheduleResource().queryDataCollect();
    }

    @Override
    public List<DcsServerNode> queryDcsServerNodeList() throws Exception {
        return getDcsScheduleResource().queryDcsServerNodeList();
    }

    private DcsScheduleResource getDcsScheduleResource() {
        if (null == dcsScheduleResource) {
            dcsScheduleResource = new DcsScheduleResource(zkAddress);
        }
        return dcsScheduleResource;
    }

}
