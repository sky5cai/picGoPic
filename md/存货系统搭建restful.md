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

![](https://raw.githubusercontent.com/sky5cai/picGoPic/master/img/20191124145526.png)

# 二.用java请求对方put请求

以下demo的请求接口模仿了文档PTD20-POSIMAOrderResultAPI-111119-0235-6.pdf的接口写
![](https://raw.githubusercontent.com/sky5cai/picGoPic/master/img/20191124145552.png)

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