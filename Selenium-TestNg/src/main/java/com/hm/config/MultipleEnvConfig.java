package com.hm.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/main/java/com/hm/config/config.properties"})
public interface MultipleEnvConfig extends Config {
	
	String browser();
	
	@DefaultValue("uat")
	String environment();
	
	@Key("dev.url")
	String url();
}
