package org.test.task;

import org.test.task.logic.TestInterface;
import org.test.task.logic.TestInterfaceImpl;
import org.test.task.model.MyCollection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        prepareData().forEach(new TestInterfaceImpl()::print);
    }

    private static List<Object> prepareData() {

        List<Object> elements = new ArrayList<>();
        elements.add(Boolean.TRUE);
        elements.add(123);
        elements.add("AnyLine");

        MyCollection myCollection = new MyCollection();
        myCollection.add("Data");
        myCollection.add(12);
        myCollection.add(new Object());

        elements.add(myCollection);

        elements.add(null);
        elements.add(LocalDateTime.now());
        return elements;
    }
}
