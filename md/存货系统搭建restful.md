#  一 .存货系统搭建restFul坏境文档



### 1.在pom.xml添加spring-mvc依赖

```x&#39;m
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-webmvc</artifactId>
	<version>3.0.5.RELEASE</version>
</dependency>
```
### 2.在对应的resource中添加spring-mvc的配置，命名：spring-mvc.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
    http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.2.xsd
    http://www.springframework.org/schema/mvc
    http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd">
    
    <mvc:default-servlet-handler/>

    <mvc:annotation-driven/>

    <context:component-scan base-package="com.restful"/>

</beans>
```
### 3.在web.xml添加spring-mvc配置

由struts2

```xml
	<filter-mapping>
        <filter-name>struts2</filter-name>
        <url-pattern>/*</url-pattern>
        <dispatcher>REQUEST</dispatcher>  
    	<dispatcher>FORWARD</dispatcher> 
    </filter-mapping>
```

改成

```xml
	<filter-mapping>
		<filter-name>struts2</filter-name>
		<url-pattern>*.action</url-pattern>
		<dispatcher>REQUEST</dispatcher>
		<dispatcher>FORWARD</dispatcher>
	</filter-mapping>
	<filter-mapping>
		<filter-name>struts2</filter-name>
		<url-pattern>*.jsp</url-pattern>
		<dispatcher>REQUEST</dispatcher>
		<dispatcher>FORWARD</dispatcher>
	</filter-mapping>
```

添加：

```xml
	<servlet-mapping>  
	  <servlet-name>default</servlet-name>  
	  <url-pattern>*.css</url-pattern>  
	  <url-pattern>*.js</url-pattern>  
	  <url-pattern>*.jpg</url-pattern>  
	  <url-pattern>*.gif</url-pattern>  
	  <url-pattern>*.png</url-pattern>  
	  <url-pattern>*.ico</url-pattern>  
	
	</servlet-mapping>

  <!-- springMVC核心配置 -->
  <servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <!-- spingMVC的配置路径  -->
      <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>

 <!--  拦截设置 -->
  <servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
```
### 4.因兼容存货1.44版本，所以选的spring版本较低，启动可能报错，改StartupListener.java
> org.springframework.beans.factory.BeanDefinitionStoreException: Parser configuration exception parsing XML from class path resource [spring-mvc.xml]; nested exception is javax.xml.parsers.ParserConfigurationException: Unable to validate using XSD: Your JAXP provider [org.apache.crimson.jaxp.DocumentBuilderFactoryImpl@9e1b54] does not support XML Schema. Are you running on Java 1.4 with Apache Crimson? Upgrade to Apache Xerces (or Java 1.5) for full XSD support.

```java
# contextInitialized方法里
System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
System.setProperty("javax.xml.parsers.SAXParserFactory", "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
```
### 5.在com下创建restful文件夹及文件如下：
restful文件夹里有controller,service,model
文件
```java
com.restful.controller.ImaController
com.restful.model.ChildTable
com.restful.model.PrimaryTable
```

### 6.更改下及创建：
更改
```
/obpm/pom.xml
/obpm/src/main/webapp/WEB-INF/web.xml
/obpm/src/main/java/cn/myapps/base/web/listener/StartupListener.java
```
添加
```
/obpm/src/main/resources/spring-mvc.xml
com.restful.controller
com.restful.service
com.restful.service
/obpm/src/main/java/com/restful/controller/ImaController.java
/obpm/src/main/java/com/restful/model/ChildTable.java
/obpm/src/main/java/com/restful/model/PrimaryTable.java

```

注意：放入生产的时候要注意spring-mvc的jar包是否齐全(lib包)，参考图

![](https://i.loli.net/2020/03/03/2Odv6LIojgMbBpJ.png)

# 二.用java请求对方put请求例子

以下demo的请求接口模仿了文档PTD20-POSIMAOrderResultAPI-111119-0235-6.pdf的接口写
![](https://i.loli.net/2020/03/03/CawKdBuU2rsNy6x.png)

java代码如下：

```java
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import net.sf.json.JSONObject;


public class ConnectRestfulPutTest {
	@Test
	public void testConnectRestFulPut(){
		String response = null;
		String url="http://localhost:6088/Z1IDS2L1IEM/interface/orders/34345534545/inventories/status";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", 0); 
		jsonObject.put("message", "成功");
		
		String data=jsonObject.toString();
		System.out.println("url: " + url);
		System.out.println("request: " + data);
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpPut httppost = new HttpPut(url);
                StringEntity stringentity = new StringEntity(data,
                        ContentType.create("application/json", "UTF-8"));
                httppost.setEntity(stringentity);
                httpresponse = httpclient.execute(httppost);
                response = EntityUtils
                        .toString(httpresponse.getEntity());
                System.out.println("response: " + response.toString());
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

```

到时换一下url跑一下

# 三.建议

为了方便维护和升级和交流，建议拓展组有可以用的svn的账号，将更改过最新代码统一上传到分支

```
https://www.teemlink.com:8443/svn/platform/projects/infinitus_ima
```

此版本分支之前问过wander,是属于无极限存货1.4版本，但是下载来之后跑不起来，因此建议将shaw能跑的最新存货代码上传到此分支，统一svn管理，也为了以后其他人接触存货系统可以方便点

# 四.请求pos接口测试用例

获取pos针对的accessToken的测试用例

```java
//获取accesstoken
@Test
public void testGetPosAccessToken(){
    String response = null;
    String accessToken = "";
    String url="https://dsis-uat.infinitus-int.com/interface/restful/dapp/auth/getToken";

    System.out.println("url: " + url);
    try {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse httpresponse = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("appCode", "imas_47e97127263fdbcd");
            httpGet.addHeader("appSecret", "31bd328b0addc40eaed3d769ae9a1311");

            httpresponse = httpclient.execute(httpGet);
            response = EntityUtils
                .toString(httpresponse.getEntity());
            JSONObject jsonObject = JSONObject.fromObject(response.toString());
            if(jsonObject.getInt("code")==0){
                accessToken = jsonObject.getString("token");
            }
            System.out.println("response: " + response.toString());
        } finally {
            if (httpclient != null) {
                httpclient.close();
            }
            if (httpresponse != null) {
                httpresponse.close();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

刷新accessToken(两小时需刷新一次)

```java
//更新asscesstoken
@Test
public void updateAccessToken(){
    String response = null;
    String accessToken = "";
    String url="https://dsis-uat.infinitus-int.com/interface/restful/dapp/auth/refreshToken";
    System.out.println("url: " + url);
    try {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse httpresponse = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("token", "78071b93f2314129ab9527efeb5179ac");
            httpGet.addHeader("appCode", "imas_47e97127263fdbcd");
            httpresponse = httpclient.execute(httpGet);
            response = EntityUtils
                .toString(httpresponse.getEntity());
            JSONObject jsonObject = JSONObject.fromObject(response.toString());
            if(jsonObject.getInt("code")==0){
                accessToken =  jsonObject.getString("token");
            }
            System.out.println("response: " + response.toString());
        } finally {
            if (httpclient != null) {
                httpclient.close();
            }
            if (httpresponse != null) {
                httpresponse.close();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

获取sign标志测试代码

```java
//获取sign标志
public static String getSign(String uri, String appSecret, String timestamp,  String postBody) {
    StringBuilder content = new StringBuilder();
    content.append(uri);
    content.append(appSecret);
    content.append(timestamp);
    if (postBody != null) {
        content.append(postBody);
    }
    System.out.println("url-->"+uri);
    System.out.println("appSecret-->"+appSecret);
    System.out.println("postBody-->"+postBody);
    System.out.println("content->"+content.toString());
    return DigestUtils.sha256Hex(content.toString());
}

@Test
public void getSignString(){
    String sign = getSign("/interface/orders/SOGMKR1911250005/inventories/status", "31bd328b0addc40eaed3d769ae9a1311", String.valueOf(new Date().getTime()), "{\"result\":0,\"message\":\"成功\"}");
    System.out.println("sign-->"+sign);
}
```

推送订单状态到pos上测试代码

```java
//测试连接存货推送订单数据
@Test
public void testConnectRestFulPut(){
    String response = null;
    String url="https://dsis-uat.infinitus-int.com/interface/orders/SOGMKR1911250005/inventories/status";
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("result", 0);
    jsonObject.put("message", "成功");

    String data=jsonObject.toString();
    System.out.println("url: " + url);
    System.out.println("request: " + data);
    //获取getSign
    String accessToken = "78071b93f2314129ab9527efeb5179ac";
    String appSecret="31bd328b0addc40eaed3d769ae9a1311";
    String timestamp = String.valueOf(new Date().getTime());
    String postBody = jsonObject.toString();
    String sign = getSign("/interface/orders/SOGMKR1911250005/inventories/status", appSecret, timestamp, postBody);
    try {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse httpresponse = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPut httppost = new HttpPut(url);
            httppost.addHeader("token", accessToken);
            httppost.addHeader("timestamp", timestamp);
            httppost.addHeader("sign", sign);
            StringEntity stringentity = new StringEntity(data,ContentType.create("application/json", "UTF-8"));
            httppost.setEntity(stringentity);
            httpresponse = httpclient.execute(httppost);
            response = EntityUtils
                .toString(httpresponse.getEntity());
            System.out.println("response: " + response.toString());
        } finally {
            if (httpclient != null) {
                httpclient.close();
            }
            if (httpresponse != null) {
                httpresponse.close();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

# 五.ima根pos接口整合

存货系统两个小时内跟新accessToken整合

在com.restful.model里建类PosConstant.java，用来放pos的常用变量

```java
package com.restful.model;

/**
 * pos的参数
 *
 */
public class PosConstant {
	//pos的accessToken
	public static String POS_ACCESSTOKEN;
	//pos的appCode
	public static String APP_CODE = "imas_47e97127263fdbcd";
	//pos的appSecret
	public static String APP_SECRET = "31bd328b0addc40eaed3d769ae9a1311";
	//拿去accessToken的url
	public static String GET_ACCESSTOKEN_URL= "https://dsis-uat.infinitus-int.com/interface/restful/dapp/auth/getToken";
	//跟新accesstoken的kurl
	public static String UPDATE_ACCESSTOKEN_URL = "https://dsis-uat.infinitus-int.com/interface/restful/dapp/auth/refreshToken";
	//跟新accesstoken的时间
	public final static int ACCESSTOKEN_PEIROD = 60 * 60 * 1000; //1小时
	
	
}
```

在com.restful下面建立util文件夹，新建类：PostUtil.java 存放工具方法

```java
package com.restful.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.restful.model.PosConstant;

import net.sf.json.JSONObject;

/**
 * 工具类
 *
 */
public class PosUtil {
	
	/**
	 * 创建accesstoken
	 * @param appCode 
	 * @param appSecret
	 * @return
	 */
	public static String getPosAccessToken(){
		
		String posAccessToken="";
	    String response = null;
	    String accessToken = "";
	    String url=PosConstant.GET_ACCESSTOKEN_URL;

//	    System.out.println("url: " + url);
	    try {
	        CloseableHttpClient httpclient = null;
	        CloseableHttpResponse httpresponse = null;
	        try {
	            httpclient = HttpClients.createDefault();
	            HttpGet httpGet = new HttpGet(url);
	            httpGet.addHeader("appCode", PosConstant.APP_CODE);
	            httpGet.addHeader("appSecret",PosConstant.APP_SECRET);

	            httpresponse = httpclient.execute(httpGet);
	            response = EntityUtils.toString(httpresponse.getEntity());
	            JSONObject jsonObject = JSONObject.fromObject(response.toString());
	            if(jsonObject.getInt("code")==0){
	                accessToken = jsonObject.getString("token");
	            }
	            System.out.println("response: " + response.toString());
	            posAccessToken = accessToken;
	        } finally {
	            if (httpclient != null) {
	                httpclient.close();
	            }
	            if (httpresponse != null) {
	                httpresponse.close();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return posAccessToken;
	}
	/**
	 * 更新accessToken
	 * @param token
	 * @return
	 */
	public static String updatePosAccessToken(String token){
		String posAccessToken = "";
		String response = null;
	    String accessToken = "";
	    String url=PosConstant.UPDATE_ACCESSTOKEN_URL;
	    System.out.println("url: " + url);
	    try {
	        CloseableHttpClient httpclient = null;
	        CloseableHttpResponse httpresponse = null;
	        try {
	            httpclient = HttpClients.createDefault();
	            HttpGet httpGet = new HttpGet(url);
	            httpGet.addHeader("token", token);
	            httpGet.addHeader("appCode", PosConstant.APP_CODE);
	            httpresponse = httpclient.execute(httpGet);
	            response = EntityUtils
	                .toString(httpresponse.getEntity());
	            JSONObject jsonObject = JSONObject.fromObject(response.toString());
	            if(jsonObject.getInt("code")==0){
	                accessToken =  jsonObject.getString("token");
	            }
	            posAccessToken = accessToken;
	            System.out.println("response: " + response.toString());
	        } finally {
	            if (httpclient != null) {
	                httpclient.close();
	            }
	            if (httpresponse != null) {
	                httpresponse.close();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return posAccessToken;
	}
	
}

```

新建类：PosAccessTokenJob.java 用来轮询执行更新accessToken

```java
package com.restful.util;
import org.apache.log4j.Logger;
import com.restful.model.PosConstant;
import cn.myapps.util.StringUtil;
import cn.myapps.util.timer.Job;

public class PosAccessTokenJob extends Job {
	
	
	public final static Logger LOG = Logger.getLogger(PosAccessTokenJob.class);

	public void run() {
		try {
			//更新accessToken
			String posAccessToken = PosConstant.POS_ACCESSTOKEN;
			if(StringUtil.isBlank(posAccessToken)){
				//为空，则请求创建accessToken
				PosConstant.POS_ACCESSTOKEN = PosUtil.getPosAccessToken();
			}else{
				//不为空，则更新accessToken
				PosUtil.updatePosAccessToken(PosConstant.POS_ACCESSTOKEN);
			}
			LOG.debug("********************* PosAccessTokenJob Job End ********************");
		} catch (Exception e) {
			LOG.error("PosAccessTokenJob Job Error: ", e);
		}
	}
}

```

将PosAccessTokenJob的放进开机启动StartupListener.java

```
//PosAccessTokenJob任务执行
Schedule.registerJob(new PosAccessTokenJob(),
					10*1000,
					PosConstant.ACCESSTOKEN_PEIROD);
```

请求接口https://dsis-uat.infinitus-int.com/interface/orders/SOGMKR1911250005/inventories/status
拿去accessToken统一改成拿取：

```java
PosConstant.POS_ACCESSTOKEN
```

