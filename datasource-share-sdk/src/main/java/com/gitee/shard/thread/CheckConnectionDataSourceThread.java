package com.gitee.shard.thread;



import com.gitee.shard.ShardDataSource;
import com.gitee.shard.ShardDataSourceSupport;
import com.gitee.shard.SlaveConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;


public class CheckConnectionDataSourceThread implements Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ShardDataSource shardDataSource ;
	private SlaveConfig slaveConfig ;
	
	private Boolean isExit = false;
	private Long interval = 5000L;
	
	public CheckConnectionDataSourceThread( ShardDataSource shardDataSource , SlaveConfig slaveConfig ){
		this.shardDataSource = shardDataSource ;
		this.slaveConfig = slaveConfig ;
	}
	
	


	public void run() {
		while( ! isExit ) {
			try {
				Connection conn = shardDataSource.determineTargetDataSource( slaveConfig.getName() ).getConnection() ;
				if( conn != null ){
					//将当前数据源加入
					ShardDataSourceSupport.addSlaveDataSources( slaveConfig );
					isExit = true ;//退出
				}
			} catch (Exception e) {
				try {
					if(slaveConfig.getInterval() !=null ){
						if( interval <= 1000 ){
							interval = 1000L ;
						}else if( slaveConfig.getInterval() >=60000 ){
							interval = 60000L;
						}else{
							interval = slaveConfig.getInterval();
						}
					}
					Thread.sleep( interval );
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		logger.info("数据源：{} 恢复正常 " ,slaveConfig.getName() );
	}
	
	
}
