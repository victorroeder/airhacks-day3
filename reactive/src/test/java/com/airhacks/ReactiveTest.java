package com.airhacks;

import java.util.OptionalDouble;
import java.util.stream.Stream;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class ReactiveTest {

    @Test
    public void reactive() {
        SimpleIntegerProperty a = new SimpleIntegerProperty();
        SimpleIntegerProperty b = new SimpleIntegerProperty(2);
        SimpleIntegerProperty c = new SimpleIntegerProperty(10);
        a.bind(b.multiply(c));
        a.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            System.out.println("newValue = " + newValue);
            System.out.println("oldValue = " + oldValue);
        });
        c.set(21);
    }

    @Test
    public void inputValidation() {
        SimpleStringProperty name = new SimpleStringProperty();
        SimpleStringProperty description = new SimpleStringProperty();
        SimpleBooleanProperty result = new SimpleBooleanProperty();
        result.bind(name.isNotEmpty().or(description.isNotEmpty()));

        result.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            System.out.println("newValue = " + newValue);
        });

        name.set("hugo");
    }

    @Test
    public void stream() {
        Stream<String> endless = Stream.generate(this::next);
        OptionalDouble average = endless.filter(s -> s.startsWith("1")).
                mapToLong(s -> Long.parseLong(s)).
                limit(100).average();
        average.ifPresent(System.out::print);
    }

    public String next() {
        return String.valueOf(System.currentTimeMillis());
    }

}
