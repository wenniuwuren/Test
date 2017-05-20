package com.wenniuwuren.java.designpattern.state;

/**
 * Created by hzzhuyibin on 2017/5/2.
 */
public class Test {

    public static void main(String[] args) {

        VoteManager vm = new VoteManager();
        for (int i = 0; i < 10; i++) {
            vm.vote("u1", "A");
        }
    }
}
