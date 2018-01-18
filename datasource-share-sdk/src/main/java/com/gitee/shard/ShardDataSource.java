package com.gitee.shard;

import com.gitee.shard.exception.ShardDataSourceException;
import com.gitee.shard.thread.CheckConnectionDataSourceThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 实现数据库读写分离，备库异常剔除恢复加入，支持备库全部不能用后切换到主库；暂不支持主-备切换
 *@author fukui
 *@date 2016-08-22
 */
public class ShardDataSource extends AbstractShardRoutingDataSource{
	
	private Logger logger = LoggerFactory.getLogger(ShardDataSource.class);
	
	private Map<String,SlaveConfig> slaveDataSourcesConfig = new HashMap<String, SlaveConfig>();

	public ShardDataSource(){}
	
	public ShardDataSource(Object master , Set<SlaveConfig> slaveDataSources ){
		new ShardDataSource( null ,master , slaveDataSources );
	}
	
	public ShardDataSource(Object defaultTargetDataSource , Object master , Set<SlaveConfig> slaveDataSources ){
		if( master == null ){
			throw new ShardDataSourceException("master datasource is null ");
		}
		Map<Object, Object> dataSources = new HashMap<Object, Object>();
		dataSources.put( "master", master );
		logger.info( "Master info ： [ name : master , datasource : "+master+"] " );
		if( slaveDataSources != null ){
			setSlaveDataSource( slaveDataSources );
			for (SlaveConfig config : slaveDataSources) {
				String name = config.getName();
				dataSources.put(name, config.getDataSources());
				//记录slaveconfig 到 map，后边检查无效数据源时使用
				slaveDataSourcesConfig.put(name, config);
				logger.info("Slave info ： {} " , config.toString()  );
			}
		}
		//设置默认数据源 
		setDefaultTargetDataSource( defaultTargetDataSource==null ? master : defaultTargetDataSource );
		
		//设置数据源（master + slave ）
		setDataSources(dataSources);
		
		//设置 备库数据源，读写分离是从备库根据加权参数随机获取数据源
		setSlaveDataSource( slaveDataSources );
	}
	
	@Override
	protected Object determineCurrentLookupKey() {
		return ShardDataSourceSupport.getDatasource();
	}

	public void setDataSources(Map<Object, Object> dataSources) {
		setTargetDataSources(dataSources);
	}

	public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
		super.setDefaultTargetDataSource( defaultTargetDataSource );
	}

	public void setSlaveDataSource( Set<SlaveConfig> slaveConfig ) {
		ShardDataSourceSupport.setSlaveDataSources( slaveConfig );
	}

	@Override
	public Connection getConnection() throws SQLException {
		try {
			Connection conn = super.getConnection();
			return conn;
		} catch (SQLException e) {
			resetDataSource();
			return super.getConnection();
		}
	}

	private void resetDataSource() {
		//删除不可用的数据源
		SlaveConfig slaveConfig = ShardDataSourceSupport.removeCurrentExceptionDataSource();
		logger.error("数据源：{} 连接异常，从slaves 中移除" ,slaveConfig.getName());
		//从新设置数据源
		ShardDataSourceSupport.setDatasource( Boolean.TRUE );
		//开启线程检查异常数据源是否可用
		new Thread(new CheckConnectionDataSourceThread( this , slaveDataSourcesConfig.get(slaveConfig.getName()) ) ).start();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		try {
			return super.getConnection(username, password);
		} catch (SQLException e) {
			resetDataSource();
			return super.getConnection(username, password);
		}
	}

	
}
