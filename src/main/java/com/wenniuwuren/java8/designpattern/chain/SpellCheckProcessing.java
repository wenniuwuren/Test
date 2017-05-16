
package com.wenniuwuren.java8.designpattern.chain;

/**
 * Created by hzzhuyibin on 2017/5/16.
 */
public class SpellCheckProcessing extends ProcessingObject<String>{


    @Override
    protected String handleWork(String input) {
        return input.replace("labda", "lambda");
    }
}

