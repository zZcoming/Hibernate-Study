# Hibernate-Study
本项目是复习Hibernate时的源码

### 项目说明
1. 4个实例，分别位于src的包下，对应的测试用例在test下，在测试用例中有实例说明
    1. quicklistart: 快速入门的实例
    2. onetoone: 一对一关联
    3. manytoone: 多对一关联
    4. manytomany: 多对多关联
2. cn.jxnu.base.BaseDemo定义了Demo基本的方法
3. hbm2ddl.auto = update，需要自己调用Demo下的main方法来生成数据表
4. hibernate.cfg.xml中<mapping class="xxx">被注释了，根据需要自己解开
5. hibernate.sql保存的是quicklystart例子中hibernate自动生成的建表语句

### 注意事项
* 视频地址：http://www.imooc.com/learn/524
* 项目所需的jar包在lib目录下
* 本项目使用UTF-8编码

