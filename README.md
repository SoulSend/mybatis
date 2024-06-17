# mybatis

学习课件：[三、MyBatis实践：提高持久层数据处理效率 (wolai.com)](https://www.wolai.com/oLP8DUTzo2JRX1DfZNZn6c)

github源码：[SoulSend/mybatis: 学习mybatis框架编写的示例源码仓库 (github.com)](https://github.com/SoulSend/mybatis)

学习mybatis框架编写的示例源码仓库

## mybatis介绍

MyBatis最初是Apache的一个开源项目iBatis, 2010年6月这个项目由Apache Software Foundation迁移到了Google Code。随着开发团队转投Google Code旗下， iBatis3.x正式更名为MyBatis。代码于2013年11月迁移到Github。

MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

**个人理解：**mybatis其实和我们使用过的dbutils和jdbctemplete的区别就是，mybatis把sql语句和java的类分割开了，它将两者使用一个mapper映射关系，关联起来。比如：定义一个mapper接口，在接口上定义方法。然后在maven的resource目录下开辟一个mappers包，然后再包内定义mapper的xml文件，这个文件规定了标签的命名空间。在mapper标签上标记属性namespace值为mapper接口的全类名，然后申明类似select、delete这样的sql标签，属性id就是mapper接口中的方法，这样我们调用接口的方法，mybatis就可以自动执行对应的sql。除了要写sql语句的映射xml文件，还要写一个nybatis的xml配置文件，一般命名为mybatis-config.xml，这个配置文件里要写你的数据库连接的基本信息，**还要写接口映射文件的位置（相对路径），这个老是忘，一忘就报错TT**，还可以设置一些开关，比如日志输出之类的

快速入门就不写了，直接看课件就行

## mybatis基本使用

### sql语句传参

#### 配置日志

```
<settings>
  <!-- SLF4J 选择slf4j输出！ -->
  <setting name="logImpl" value="SLF4J"/>
</settings>
```

#### sql语句获取参数

```
#{参数} 使用这个符号会预先将sql语句预编译，将这个符号换成一个 ？，然后将参数赋值给这个 ？ 号，但是不能赋给一个参数，举例：
select emp_id from t_emp where emp_name=#{id}

${参数} 使用这个符号是直接将输入的参数拼接，会存在sql注入的风险
```

##### 获取一个基本类型参数

```
直接在#{}里面随意写一个名字，可以不和接口方法参数名相同，但是建议最好还是一样
```

##### 获取多个基本类型参数

```
在方法接口的参数声明里，要在参数前面加上注解@Param("sql语句里面你要用的名字")这样在sql语句里才能唯一的确定并获取一个参数，举例:
Employee queryByNameAndId(@Param("empid") Integer id, @Param("empname") String name);
select emp_id empId,emp_name empName, emp_salary empSalary from t_emp where emp_id = #{empid} and emp_name=#{empname}
```

##### 获取一个实体类

```
在接口方法里声明参数,在sql语句里直接写这个实体类的属性名就行
```

##### 获取一个Map键值对

```
直接在sql里面写map对应的键的名字和值的名字
```

#### sql语句返回查询的结果

##### 增删改就不用说了 返回结果默认就是int的，不用设置

##### 查操作

```
1、直接使用返回类型的全类名
2、在typeAliases标签下写上： <typeAlias alias="Author" type="domain.blog.Author"/> 这样就可以使用别名了type是全类名
3、这是对2的批操作在mybatis配置文件里使用typeAlias标签里的packge标签设置对应的包名，这样该包下的所有类都会自动使用类名了
```

**select标签，通过resultType指定查询返回值类型！**
**resultType = "全限定符 或者 别名 或者如果是返回集合类型，写范型类型即可"**

