README
===========================
本程序为web信息检索与处理课程实验一源代码。S_E_by_Jeeping是eclipse下的工程文件，可以直接打开。关于实验的具体要求参见课程实验ppt。程序的设计思路及使用说明如下。
****  

##目录
* [实验内容](#实验内容)
* [实验环境](#实验环境)
* [实验步骤及方法](#实验步骤及方法)

实验内容
------
###利用Lucene等工具建立简单的搜索引擎：
1.网页预处理  
2.创建索引（使用中科院中文分词工具ICTCLAS）  
3.查询（实现简单的查询界面）  


实验环境
------
编译环境：eclipse-jee-mars-2-win32-x86_64  
编程环境：jdk1.8.0_111  
编程语言：java、jsp  
机器内存：8G (4G+4G)  
时钟主频：2.6GHz  
运行环境：windows10操作系统、采用chrome浏览器访问查询  
使用工具：lucene5.3.1、IKAnalyzer、tomcat 7.0.42  


实验步骤及方法
------
###1.	网页预处理
a.	首先调用Txt2String.java里的readfile方法，把待处理的text文本数据转变为字符串返回。  
b.	接着，从已经读入内存的字符串中，进行数据分析，分离聚类。调用strings2Docs方法，将字符串中的一个个doc文本分离出来。  

###2.	创建索引
a.	写索引方法。由传入的doc文本元素组成的数组建立索引。  
b.	在a项的实现中，调用了写索引方法类indexer。该类在java文件Indexer.java中。  

###3.	实现查询功能及结果高亮
a.	实现QuerySearch类的search方法。根据从web前端传入的关键字（词），在先前已经建立好的索引中查找匹配目标项的TopK个文档。  
b.	在web前端，采用jsp实现。获取用户输入的关键字（词），调用后端的search方法，得到TopK文档的信息，并输出搜索结果。  

###4.	分页显示搜索结果
在jsp实现显示结果时，将所获得的K=100个文档结果分10页每页10个展示。（此处的K值是我指定的，也可以是其他的任意正整数。）  
在每次显示之前，先获取当前页码，显示对应排序的文档数据。  
同时，在显示页面的最下面，设计一个跳转前一页、后一页、最前页、最后页的快捷键。  
