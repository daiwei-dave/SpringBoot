package com.didispace.service.iml;

import com.didispace.dao.SaleTaskDao;
import com.didispace.entity.SaleTask;
import com.didispace.entity.Stores;
import com.didispace.service.SaleTaskService;
import com.gitee.common.service.BaseServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 销售任务
 * @Description
 * @Author daiwei
 * @Date 2017/11/15
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Service("saleTaskService")
public class SaleTaskServiceImpl extends BaseServiceSupport<SaleTask> implements SaleTaskService {

	private final static Logger logger = LoggerFactory.getLogger(SaleTaskServiceImpl.class);
	
	@Autowired
    private SaleTaskDao saleTaskDao;
    
    @Override
	public SaleTaskDao getDao() {
		return saleTaskDao;
	}

	@Override
	public void batchAdd(SaleTask saleTask, List<Stores> storeList) {
		saleTaskDao.batchAdd(saleTask.getTaskId(),saleTask.getTaskName(),saleTask.getBranchTotal(),storeList);
	}
}
