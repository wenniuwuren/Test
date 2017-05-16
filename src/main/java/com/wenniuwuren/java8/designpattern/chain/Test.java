
package com.wenniuwuren.java8.designpattern.chain;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by hzzhuyibin on 2017/5/16.
 */
public class Test {

    public static void main(String[] args) {

        ProcessingObject<String> processingObject1 = new HeaderTextProcessing();
        ProcessingObject<String> processingObject2 = new SpellCheckProcessing();

        processingObject1.setSuccessor(processingObject2); // 将对象链起来

        System.out.println(processingObject1.handle(" labda expression"));

        // Lambda 化
        UnaryOperator<String> headerProcessing = (text) -> "add header" + text;

        UnaryOperator<String> spellProcessing = (text) -> text.replace("labda", "lambda");

        Function<String, String> pipeline = headerProcessing.andThen(spellProcessing);
        System.out.println(pipeline.apply(" labda expression"));
    }
}

