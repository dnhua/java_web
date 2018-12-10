# XML 文件的简单解析

## 一些有用的链接
### [dom4j](https://dom4j.github.io/)
### [jaxen](https://sourceforge.net/projects/jaxen/)

## 直接解析

```xml
<?xml version="1.0" encoding="UTF-8"?>
<stus>
	<stu>
		<name>李华</name>
		<age>24</age>
		<address>上海</address>
	</stu>
	<stu>
		<name>亚清</name>
		<age>22</age>
		<address>上海</address>
	</stu>
</stus>
```

### XML 解析

> 其实就是获取元素里面的字符数据或者属性数据。

### XML解析方式(面试常问)

> 有很多种，但是常用的有两种。

* DOM

* SAX

### 针对这两种解析方式的API

> 一些组织或者公司， 针对以上两种解析方式， 给出的解决方案有哪些？

		jaxp  sun公司。 比较繁琐

		jdom
		dom4j  使用比较广泛


### Dom4j 基本用法 -- java8 之后要用2.1.1版本

		element.element("stu") : 返回该元素下的第一个stu元素
		element.elements(); 返回该元素下的所有子元素。 

1. 创建SaxReader对象

2. 指定解析的xml

3. 获取根元素。

4. 根据根元素获取子元素或者下面的子孙元素

```java
try {
	//1. 创建sax读取对象
	SAXReader reader = new SAXReader(); //jdbc -- classloader
	//2. 指定解析的xml源
	Document  document  = reader.read(new File("src/xml/stus.xml"));
	
	//3. 得到元素、
	//得到根元素
	Element rootElement= document.getRootElement();
	
	//获取根元素下面的子元素 age
	//rootElement.element("age") 
	//System.out.println(rootElement.element("stu").element("age").getText());


	//获取根元素下面的所有子元素 。 stu元素
	List<Element> elements = rootElement.elements();
	//遍历所有的stu元素
	for (Element element : elements) {
		//获取stu元素下面的name元素
		String name = element.element("name").getText();
		String age = element.element("age").getText();
		String address = element.element("address").getText();
		System.out.println("name="+name+"==age+"+age+"==address="+address);
	}
	
} catch (Exception e) {
	e.printStackTrace();
}
```


SaxReader 创建好对象 。  

Document
Element

1. 看文档

2. 记住关键字 。

3. 有对象先点一下。

4. 看一下方法的返回值。 

5. 根据平时的积累。  getXXX setXXX 


## xpath解析

### Dom4j 的 Xpath使用

>  dom4j里面支持Xpath的写法。 xpath其实是xml的路径语言，支持我们在解析xml的时候，能够快速的定位到具体的某一个元素。

1. **添加jar包依赖**

   jaxen-1.1-beta-6.jar

2. 在查找指定节点的时候，根据XPath语法规则来查找

3. 后续的代码与以前的解析代码一样。

```java
//要想使用Xpath， 还得添加支持的jar 获取的是第一个 只返回一个。 
Element nameElement = (Element) rootElement.selectSingleNode("//name");
System.out.println(nameElement.getText());


System.out.println("----------------");

//获取文档里面的所有name元素 
List<Element> list = rootElement.selectNodes("//name");
for (Element element : list) {
	System.out.println(element.getText());
}
```