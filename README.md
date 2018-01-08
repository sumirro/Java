ButterKnife源码阅读过程中练习专用
# 注解（Annotation）
ButterKnife用到了注解，所以注解这个东西必须知道。之前经常用，也大概了解概念，今天动手写了几个，巩固一下基础。
##### @Target：标记此注解作用范围，包含（TYPE：类，接口），（METHOD：方法）（FIELD：变量）最常用的就这些
##### @Retention：注解保留时间，一般反射的就RetentionPolicy.RUNTIME(运行时)；ButterKnife这种生成代码的就
##### 用RetentionPolicy.CLASS(编译时)；还有一种是RetentionPolicy.SOURCE（源码），这种就指的@Overri
##### de，@SupperWarnning这种系统源码的注解，一般用不到。
##### @Inherited：子类是否可以继承这个注解。目前还没写到注解继承注解的程度，没研究
##### @Documented：注释是否保存到javac文档中

写了个简单的例子，用反射完成了类似ButterKnife的@BindView，@OnClick功能
