package org.test.task.logic;


import org.test.task.model.LambdaContainer;

import java.util.function.Function;

public class TestInterfaceImpl implements TestInterface {

    @Override
    public void print(Object arg) {
        Function suitableLambda = LambdaContainer.getSuitableLambda(arg);
        System.out.println(suitableLambda.apply(arg));
    }
}

