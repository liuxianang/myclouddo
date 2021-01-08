package test;

import java.io.*;

public class MyClassLoader extends ClassLoader {
    private String baseDir;
    public MyClassLoader(String baseDir) {
        this.baseDir = baseDir;
    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fullClassFilePath = this.baseDir + name.replace("\\.","/") + ".class";
        File classFilePath = new File(fullClassFilePath);
        if (classFilePath.exists()) {
            FileInputStream fileInputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                fileInputStream = new FileInputStream(classFilePath);
                byte[] data = new byte[1024];
                int len = -1;
                byteArrayOutputStream = new ByteArrayOutputStream();
                while ((len = fileInputStream.read(data)) != -1) {
                    byteArrayOutputStream.write(data,0,len);
                }

                return defineClass(name,byteArrayOutputStream.toByteArray(),0,byteArrayOutputStream.size());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch ( IOException e) {
                e.printStackTrace();
            } finally {
                if (null != fileInputStream) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (null != byteArrayOutputStream) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return super.findClass(name);
    }
}