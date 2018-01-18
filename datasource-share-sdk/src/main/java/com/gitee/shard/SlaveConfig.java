package com.gitee.shard;




public class SlaveConfig {

	/*** 数据源名称*/
	private String name;
	
	/*** 权重*/
	private Integer weight=1;
	
	/*** 数据源*/
	private Object dataSources;

	/**slave 数据检查数据源是否可用时间间隔**/
	private Long interval ;
	
	public SlaveConfig(){}
	
	public SlaveConfig(String name, Integer weight){
		super();
		this.name = name;
		this.weight = weight;
	}
	
	public SlaveConfig(String name, Integer weight, Object dataSources) {
		super();
		this.name = name;
		this.weight = weight;
		this.dataSources = dataSources;
	}

	public SlaveConfig(String name, Integer weight, Object dataSources , Long interval ) {
		super();
		this.name = name;
		this.weight = weight;
		this.dataSources = dataSources;
		this.interval = interval;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Object getDataSources() {
		return dataSources;
	}

	public void setDataSources(Object dataSources) {
		this.dataSources = dataSources;
	}

	
	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "SlaveConfig [name=" + name + ", weight=" + weight + ", dataSources=" + dataSources + ", interval=" + interval + "]";
	}
	

}
