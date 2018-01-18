package com.gitee.shard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class ShardDataSourceSupport {

	
	private static Logger logger = LoggerFactory.getLogger( ShardDataSourceSupport.class );
	
	public static final ThreadLocal<String> currentDatasource = new ThreadLocal<String>();
	
	private static final String master = "master";
	private static Set<SlaveConfig> slaveDataSources ;
	//存放备库datasource name 和 权重
	private static Map<String, Integer> dataSources ;
	
	public static void setSlaveDataSources( Set<SlaveConfig> slaves ) {
		slaveDataSources = slaves;
		if( slaveDataSources != null ){
			dataSources = new HashMap<String, Integer>();
			//set -> map 方便后续加权随机取 备库数据源
			for( SlaveConfig config :  slaveDataSources ){
				dataSources.put(config.getName(), config.getWeight() );
			}
		}
	}
	
	public static void addSlaveDataSources( SlaveConfig config ){
		dataSources.put(config.getName(), config.getWeight() );
	}
	
	public static SlaveConfig removeCurrentExceptionDataSource(  ){
		String dataSourceKey = currentDatasource.get() ;
		Integer weight = dataSources.get(dataSourceKey);
		dataSources.remove( dataSourceKey ) ;
		return new SlaveConfig(dataSourceKey , weight ) ;
	}
	
	
	public static String getDatasource() {
		return currentDatasource.get( );
	} 
	
	public static void setDatasource( boolean isSlave  ){
		if( isSlave ){
			currentDatasource.set( randDataSourceByWeight() );
		}else{
			currentDatasource.set( master );
		}
	}
	
	public static String randDataSourceByWeight( ) {
        if ( dataSources == null || dataSources.size() == 0 ){
        	return master;
        }
        Integer sum = 0;
        for (Integer value : dataSources.values()) {
            sum += value;
        }
        Integer rand = new Random().nextInt(sum) + 1;
        for ( Map.Entry<String, Integer> entry : dataSources.entrySet() ) {
            rand = rand - entry.getValue();
            if (rand <= 0) {
                String item = entry.getKey();
                logger.debug( "select datasource ： {} excute query" , item );
                return item;
            }
        }
        return master ;
    }
	
	
}
