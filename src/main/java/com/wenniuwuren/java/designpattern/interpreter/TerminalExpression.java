
package com.wenniuwuren.java.designpattern.interpreter;

/**
 * @author zhuyibin
 */
public class TerminalExpression implements AbstractExpression{
    @Override
    public void interpre(Context context) {
        System.out.println("终端解释器");
    }
}