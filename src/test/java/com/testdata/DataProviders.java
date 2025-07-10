package com.testdata;

import org.testng.annotations.DataProvider;
import java.util.Random;

public class DataProviders {
    @DataProvider(name = "provideInputValues")
    public Object[][] provideInputValues() {
        return new Object[][]{
                {String.valueOf(new Random().nextInt(1000, 100000))},
                {String.valueOf(new Random().nextInt(1000, 100000))},
                {String.valueOf(new Random().nextInt(1000, 100000))}
        };
    }
}
