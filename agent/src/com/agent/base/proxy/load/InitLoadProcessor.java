package com.agent.base.proxy.load;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ClassUtils;

import com.agent.base.proxy.adapter.IBaseAdapter;
import com.agent.base.proxy.annotation.MainService;
import com.agent.common.constants.Constants;

public class InitLoadProcessor{
	
	@PostConstruct
	public void initLoad(){
		ServiceBeanMap.init(scanFilter());
	}
	
	public static Map<String, IBaseAdapter> scanFilter(){
		Map<String, IBaseAdapter> filters = new HashMap<String, IBaseAdapter>();
        Set<Class<?>> classSet = scan(IBaseAdapter.class);
        if(classSet != null){
            for(Class<?> classObj : classSet){
            	MainService config = classObj.getAnnotation(MainService.class);
                if(config != null){
                    try {
                    	filters.put(config.id(), IBaseAdapter.class.cast(classObj.newInstance()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filters;
    }
	
	private static Set<Class<?>> scan(Class<?> classObj){
		String appRoot = Constants.SCAN_SERVICE_CLASS_PATH;
        String appPath = Constants.SCAN_SERVICE_CLASS_PATH.replace(".", "/");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
            URL appUrl = loader.getResource(appPath);
            return scanLoop(new File(appUrl.getFile()), appRoot, classObj);
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static Set<Class<?>> scanLoop(File file, String packageName, Class<?> classObj) throws ClassNotFoundException{
		if(!file.exists()){
			return new HashSet<Class<?>>();
		}
		
		Set<Class<?>> classes = new HashSet<Class<?>>();
		if(file.isDirectory()){
			File[] subFiles = file.listFiles(new FileFilter(){
				public boolean accept(File pathname) {
					return pathname.isDirectory() || pathname.getName().endsWith(".class");
				}			
			});
			for(File subFile : subFiles){
				String subPackageName = packageName;
				if(subFile.isDirectory()){
					subPackageName += "." + subFile.getName();
				}
				classes.addAll(scanLoop(subFile, subPackageName, classObj));
			}
		}else{
			String className = file.getName().replace(".class", "");
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Class<?> tempClass = loader.loadClass(packageName + "." + className);
			List<Class<?>> interfaces = ClassUtils.getAllInterfaces(tempClass);
			if(interfaces.contains(classObj)){
				classes.add(tempClass);
			}
		}
		return classes;
	}

}
