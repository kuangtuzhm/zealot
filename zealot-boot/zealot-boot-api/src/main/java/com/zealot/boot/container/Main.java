/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zealot.boot.container;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zealot.common.CommonConsts;
import com.zealot.util.ConfigUtils;
import com.zealot.util.StringUtil;


/**
 * Main. (API, Static, ThreadSafe)
 * 
 * @author william.liangf
 */
public class Main {

    

    public static final String SHUTDOWN_HOOK_KEY = "dubbo.shutdown.hook";
    
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static volatile boolean running = true;

    public static void main(String[] args) {
        try {
            if (args == null || args.length == 0) {
                String config = ConfigUtils.getProperty(CommonConsts.CONTAINER_KEY, CommonConsts.CONTAINER_DEFAULT);
                args = CommonConsts.COMMA_SPLIT_PATTERN.split(config);
            }
            
            final List<Container> containers = new ArrayList<Container>();
            ServiceLoader<Container> operations = ServiceLoader.load(Container.class);
            for (int i = 0; i < args.length; i ++) {
      		  	Iterator<Container> operationIterator = operations.iterator();
      		  	while(operationIterator.hasNext())
      		  	{
      		  		Container container = operationIterator.next();
      		  		if(StringUtil.isNotEmpty(container.getName()))
      		  		{
	      		  		if(container.getName().equals(args[i]))
	      		  		{
	      		  			containers.add(container);
	      		  		}
      		  		}
      		  	}
            }
            logger.info("Use container type(" + Arrays.toString(args) + ") to run dubbo serivce.");
            
            if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY))) {
	            Runtime.getRuntime().addShutdownHook(new Thread() {
	                public void run() {
	                    for (Container container : containers) {
	                        try {
	                            container.stop();
	                            logger.info("Dubbo " + container.getClass().getSimpleName() + " stopped!");
	                        } catch (Throwable t) {
	                            logger.error(t.getMessage(), t);
	                        }
	                        synchronized (Main.class) {
	                            running = false;
	                            Main.class.notify();
	                        }
	                    }
	                }
	            });
            }
            
            for (Container container : containers) {
                container.start();
                logger.info("Dubbo " + container.getClass().getSimpleName() + " started!");
            }
            System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " Dubbo service server started!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            System.exit(1);
        }
        synchronized (Main.class) {
            while (running) {
                try {
                    Main.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }
    
}