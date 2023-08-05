package com.atlihao.springboot.schedule.controller.service;

import com.atlihao.springboot.schedule.domain.DataCollect;
import com.atlihao.springboot.schedule.domain.DcsScheduleInfo;
import com.atlihao.springboot.schedule.domain.DcsServerNode;
import com.atlihao.springboot.schedule.domain.Instruct;

import java.util.List;


public interface DcsScheduleService {

    List<String> queryPathRootServerList() throws Exception;

    List<DcsScheduleInfo> queryDcsScheduleInfoList(String schedulerServerId) throws Exception;

    void pushInstruct(Instruct instruct) throws Exception;

    DataCollect queryDataCollect() throws Exception;

    List<DcsServerNode> queryDcsServerNodeList() throws Exception;

}
