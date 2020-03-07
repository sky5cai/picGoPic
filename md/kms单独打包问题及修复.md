# kms单独打包问题及修复

>1. 新的数据源，相关表是否能生成
>   T_USER,
>
>   T_DEPARTMENT,
>   T_USER_ROLE_DEPARTMENT_SET
>
>   T_DOMAIN,
>
>   t_field_extends,
>
>   t_calendar,
>
>   t_specialday,
>
>   t_standardday
>
>2. 打成war包测试

#### 1.新的数据源，相关表是否能生成

t_user

| 类名的属性 | 数据库                 | 属性     |
| ---------- | ---------------------- | -------- |
|            | ID                     | varchar  |
|            | CALENDAR               | varchar  |
|            | LEVELS                 | int      |
|            | SUPERIOR               | varchar  |
|            | DEFAULTAPPLICATION     | varchar  |
|            | EMAIL                  | varchar  |
|            | LOGINNO                | varchar  |
|            | LOGINPWD               | varchar  |
|            | NAME                   | varchar  |
|            | TELEPHONE              | varchar  |
|            | STATUS                 | int      |
|            | DOMAINID               | varchar  |
|            | PROXYUSER              | varchar  |
|            | remarks                | varchar  |
|            | FIELD1                 | varchar  |
|            | FIELD2                 | varchar  |
|            | FIELD3                 | varchar  |
|            | FIELD4                 | varchar  |
|            | FIELD5                 | varchar  |
|            | FIELD6                 | varchar  |
|            | FIELD7                 | varchar  |
|            | FIELD8                 | varchar  |
|            | FIELD9                 | varchar  |
|            | FIELD10                | varchar  |
|            | DEFAULTDEPARTMENT      | varchar  |
|            | ISDOMAINUSER           | varchar  |
|            | STARTPROXYTIME         | datetime |
|            | ENDPROXYTIME           | datetime |
|            | USEIM                  | bit      |
|            | ORDERBYNO              | int      |
|            | LASTMODIFYPASSWORDTIME | datetime |
|            | PASSWORDARRAY          | varchar  |
|            | LOCKFLAG               | int      |
|            | LASTNOTICETIME         | datetime |
|            | PUBLICKEY              | varchar  |
|            | DIMISSION              | int      |
|            | AVATAR                 | text     |
|            | FAVORITE_CONTACTS      | varchar  |
|            | PERMISSION_TYPE        | varchar  |
|            | LIAISON_OFFICER        | bit      |
|            | TELEPHONE2             | varchar  |
|            | TELEPHONEPUBLIC        | bit      |
|            | TELEPHONEPUBLIC2       | bit      |
|            | EMAILPUBLIC            | bit      |
|            | USERINFOPUBLIC         | bit      |
|            | NAME_LETTER            | varchar  |
|            | SIGNS                  | text     |
|            | USEHOMEPAGE            | int      |
|            | USERSKIN               | varchar  |
|            | USERSTYLE              | varchar  |
|            | PENDINGSTYLE           | text     |
|            | GENERALPAGE            | text     |
|            | CONFIGURE              | text     |
|            | COMMON_OPINION         | text     |
|            | pwdErrorTimes          | int      |

t_user_department_role_set

| 类名的属性   | 数据库       |
| ------------ | ------------ |
| id           | ID           |
| userId       | USERID       |
| roleId       | ROLEID       |
| departmentId | DEPARTMENTID |

......

本来想从jpa的类来生成表，但发现有引用hibernate，因此看hibernate生成表及修改

hibernate.cfg.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
		"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
		"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
	<session-factory>
		<property name="hibernate.connection.SetBigStringTryClob">true</property>
		<property name="hibernate.hbm2ddl.auto">update</property>

		<mapping resource="cn/myapps/common/model/department/DepartmentVO.hbm.xml" />
		<mapping resource="cn/myapps/common/model/user/UserVO.hbm.xml" />
		<mapping resource="cn/myapps/common/model/user/UserDepartmentRoleSet.hbm.xml" />
		<mapping resource="cn/myapps/common/model/domain/DomainVO.hbm.xml" />

		<mapping resource="cn/myapps/common/model/fieldextends/FieldExtendsVO.hbm.xml" />
		<mapping resource="cn/myapps/common/model/workcalendar/calendar/CalendarVO.hbm.xml" />
		<mapping resource="cn/myapps/common/model/workcalendar/special/SpecialDayVO.hbm.xml" />
		<mapping resource="cn/myapps/common/model/workcalendar/standard/StandardDayVO.hbm.xml" />

		</session-factory>
</hibernate-configuration>

```

可正常生成表

![](https://i.loli.net/2020/03/06/3htXQ8sNB5DnkCc.png)

因为kms的过滤器拦截authtime的连接，因此加入允许

![](https://i.loli.net/2020/03/06/VdHWUIcAXwnErFT.png)

后台可正常登录

![](https://i.loli.net/2020/03/06/dZR9DYcSeJmgtl1.png)

看了新建用户没有新建 标准日历 24小时日历 夜班日历表

需要用代码生成



kms添加PersistenceFilter注册过滤器，导致连接有缓存

