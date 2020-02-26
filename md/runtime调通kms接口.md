## runtime调通kms接口

主要通过accessToken可访问kms接口,现在工具里面拿取accessToken,以下为例子
在documentUtil添加方法，根据request获取accessToken

```java
//获取accessToken
public String getAccessToken(HttpServletRequest request){
    String accessToken="";
    request.getCookies();
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        String name = cookie.getName();
        if ("accessToken".equals(name)) {
            accessToken =  cookie.getValue();
            break;
        }
    }
    return accessToken;
}
```

请求的接口：

```java
	public String testOne(String accessToken) throws Exception{
		String response = null;
		String url="http://192.168.10.136:8888/kms/kms/teams/serialNumber";

		System.out.println("url: " + url);
		try {
			CloseableHttpClient httpclient = null;
			CloseableHttpResponse httpresponse = null;
			try {
				httpclient = HttpClients.createDefault();
				HttpGet httpGet = new HttpGet(url);
				httpGet.addHeader("accessToken", accessToken);

				httpresponse = httpclient.execute(httpGet);
				response = EntityUtils.toString(httpresponse.getEntity());
				JSONObject jsonObject = JSONObject.fromObject(response.toString());
				System.out.println(jsonObject.toString());
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

脚本调用

```js
(function(){  
 var request = $WEB.getParamsTable().getHttpRequest(); 
     var util = new Packages.cn.myapps.runtime.dynaform.document.DocumentUtil(); 
     var accessToken = util.getAccessToken(request); 
     var number = util.testOne(accessToken);
     return number; 
  })()
```

