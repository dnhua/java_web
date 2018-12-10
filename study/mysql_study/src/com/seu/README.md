# test

test文件夹中为一些测试文件。
test_CRUD.java 为一个基本的mysql CRUD测试文件，主要是为了测试数据库和java开发环境能够正常连接，需要jdbc的jar包。

# util 

这里主要是实现了一些工具类型的类。

**JDCBUtil.java：** 管理数据库的连接释放等，其配置文件为jdbc.properties文件。

**student.java:** 学生对象，方便数据库测试使用。

**studentDao.java:** 操作学生数据库的接口。

**studentDaoImpl.java:** 实现了上述接口。

##### 下面是我的数据表，有id name以及age三个字段。
![这是我的数据库图片](https://github.com/dnhua/java_web/blob/master/study/mysql_study/src/com/seu/data/1.PNG)
