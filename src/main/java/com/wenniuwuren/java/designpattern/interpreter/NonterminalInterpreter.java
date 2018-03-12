
package com.wenniuwuren.java.designpattern.interpreter;

/**
 * @author zhuyibin
 */
public class NonterminalInterpreter implements AbstractExpression{
    @Override
    public void interpre(Context context) {
        System.out.println("非终端解释器");
    }
}