package test;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyIDE {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        // 定义java代码，并保存到文件（Test.java）
        StringBuilder sb = new StringBuilder();
        sb.append("package com.tommy.core.test.reflect;\n");
        sb.append("public class Test {\n");
        sb.append("    private String name;\n");
        sb.append("    public Test(String name){\n");
        sb.append("        this.name = name;\n");
        sb.append("        System.out.println(\"hello,my name is \" + name);\n");
        sb.append("    }\n");
        sb.append("    public String sayHello(String name) {\n");
        sb.append("        return \"hello,\" + name;\n");
        sb.append("    }\n");
        sb.append("}\n");

        System.out.println(sb.toString());

        String baseOutputDir = "F:\\output\\classes\\";
        String baseDir = baseOutputDir + "com\\tommy\\core\\test\\reflect\\";
        String targetJavaOutputPath = baseDir + "Test.java";
        // 保存为java文件
        FileWriter fileWriter = new FileWriter(targetJavaOutputPath);
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();

        // 编译为class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
        List<File> files = new ArrayList<>();
        files.add(new File(targetJavaOutputPath));
        Iterable compilationUnits = manager.getJavaFileObjectsFromFiles(files);

        // 编译
        // 设置编译选项，配置class文件输出路径
        Iterable<String> options = Arrays.asList("-d",baseOutputDir);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, options, null, compilationUnits);
        // 执行编译任务
        task.call();


        // 通过反射得到对象
//        Class clazz = Class.forName("com.tommy.core.test.reflect.Test");
        // 使用自定义的类加载器加载class
        Class clazz = new MyClassLoader(baseOutputDir).loadClass("com.tommy.core.test.reflect.Test");
        // 得到构造器
        Constructor constructor = clazz.getConstructor(String.class);
        // 通过构造器new一个对象
        Object test = constructor.newInstance("jack.tsing");
        // 得到sayHello方法
        Method method = clazz.getMethod("sayHello", String.class);
        // 调用sayHello方法
        String result = (String) method.invoke(test, "jack.ma");
        System.out.println(result);
    }
}