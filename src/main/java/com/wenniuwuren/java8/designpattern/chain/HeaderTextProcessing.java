
package com.wenniuwuren.java8.designpattern.chain;

/**
 * Created by hzzhuyibin on 2017/5/16.
 */
public class HeaderTextProcessing extends ProcessingObject<String>{
    @Override
    protected String handleWork(String input) {

        return "add header" + input;
    }
}

