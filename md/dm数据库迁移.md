# dm数据库迁移注意事项

### 两个jar包

DmDialect-for-hibernate3.1.jar

DmJdbcDriver18.jar



### 针对4.1配置

proxool.properties

```properties
jdbc-0.proxool.driver-class=dm.jdbc.driver.DmDriver
jdbc-0.proxool.driver-url=jdbc:dm://192.168.88.157:5236
jdbc-0.user=obpm2_testcase
jdbc-0.password=Teemlink2010
```

hibernate.propertie

```properties
hibernate.default_schema obpm2_testcase
hibernate.dialect org.hibernate.dialect.DmDialect
```



