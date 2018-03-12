package com.wenniuwuren.java.designpattern.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuyibin
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        List<AbstractExpression> abstractExpressionList = new ArrayList<>();
        abstractExpressionList.add(new TerminalExpression());
        abstractExpressionList.add(new NonterminalInterpreter());
        abstractExpressionList.add(new TerminalExpression());

        for (AbstractExpression a : abstractExpressionList) {
            a.interpre(context);

        }
    }
}