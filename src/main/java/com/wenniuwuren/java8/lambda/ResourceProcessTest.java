package com.wenniuwuren.java8.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 资源处理，例如处理文件或者数据库
 * Created by hzzhuyibin on 2017/4/26.
 */
public class ResourceProcessTest {

    public static void main(String[] args) {
        try {
            System.out.println(processFile());

            // 转为 Lambda 表达式
            // 1. 行为参数化
//            String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());

            // 4. 传递 lambda
//            String result = processFile((BufferedReader br) -> br.readLine());
            // 将 Lambda 改为方法引用
            String result = processFile(BufferedReader::readLine);
            System.out.println(result);
            result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 这个方法太局限，只能读取文件第一行
    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Windows\\System32\\drivers\\etc\\hosts"))) {
            return br.readLine();
        }

    }

    // 2. 使用函数式接口来传递行为。创建一个能匹配 BufferedReader -> String 还可以 throws IOException 的接口。
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    // 3. 执行一个行为(执行 Lambda 所代表的代码)
    // 任何一个 BufferedReader -> String 形式的 Lambda 都可以作为参数来传递，因为他们符合 BufferedReaderProcessor 接口
    // processFile() 的签名(参数，返回值)
    // Lambda 允许直接内联，为函数式接口的抽象方法提供实现,并且将整个表达式作为函数式接口的一个实例
    public static String processFile(BufferedReaderProcessor b) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Windows\\System32\\drivers\\etc\\hosts"))) {
            return b.process(br);
        }
    }
}

