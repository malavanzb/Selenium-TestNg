package com.hm.sample;

import org.aeonbits.owner.ConfigFactory;

import com.hm.config.*;

public class MultipleEnvRunner {

	public static void main(String[] args) {

		MultipleEnvConfig mec = ConfigFactory.create(MultipleEnvConfig.class);
		
		System.out.println(mec.browser());
		System.out.println(mec.environment());
		System.out.println(mec.url());
		//System.out.println(System.getProperty("user.dir"));

	}

}
