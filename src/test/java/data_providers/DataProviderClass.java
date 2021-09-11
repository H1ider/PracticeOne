package data_providers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderClass {
    @DataProvider(name = "SingleValue")
    public Object[][] storeSingleValue() {
        return new Object[][] {
                {"Rifat"},
                {"Mohammad"},
                {"Ashraf"}
        };
    }

    @DataProvider(name = "MultipleValues")
    public Object[][] storeMultipleValues(){
        return new Object[][] {
                {"Rifat", "FL", 33021},
                {"Farid", "NY", 23415}
        };
    }

    @DataProvider(name = "RealAprRates")
    public Object[][] storeRealAprRates() {
        return new Object[][]{
                {"200000", "15000", "3", "3.130%"}
        };
    }

    @Test(dataProvider = "SingleValue")
    public void run(String name) {
        System.out.println("[Single Value] name is " + name);
    }

    @Test(dataProvider = "MultipleValues")
    public void run(String name, String state, int zipcode) {
        System.out.println("[multiple Value] name is " + name);
        System.out.println("[multiple Value] state is " + state);
        System.out.println("[multiple Value] zip code is " + zipcode);
    }
}
