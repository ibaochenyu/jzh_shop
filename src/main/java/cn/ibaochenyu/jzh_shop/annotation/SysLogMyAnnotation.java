package cn.ibaochenyu.jzh_shop.annotation;


import javax.swing.text.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//1.需要定义annotaion：也就是定义注释：在这个文件
//2.需要定义aspect：定义切口

//@interface 用于定义注解，这是 Java 中一种特殊的接口，通常用于提供元数据。注解可以用于标记类、方法、变量、参数等

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)//RUNTIME：注解不仅被保留在类文件中，当运行 Java 程序时，VM 也会把注解保留在内存中。这使得我们可以通过反射机制读取类、方法或字段上的注解信息。
//Retention [ri'tenʃәn][计] 保贸, 保持

//错误写法：
//public interface SysLogMyAnnotation {
public @interface SysLogMyAnnotation {
    //错误写法：
    //string value;

    String mvalue() default "";
}
