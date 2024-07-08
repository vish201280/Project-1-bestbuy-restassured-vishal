package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import static io.restassured.RestAssured.given;


public class ProductsExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void start() {
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //21. Extract the limit
    @Test
    public void test021() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //22. Extract the total
    @Test
    public void test022() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //23. Extract the name of 5th product
    @Test
    public void test023() {
        String nameOf5thProduct = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + nameOf5thProduct);
        System.out.println("------------------End of Test---------------------------");
    }

    //24. Extract the names of all the products
    @Test
    public void test024() {
        List<String> listOfTheProducts = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all store is : " + listOfTheProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void test025() {
        List<Integer> listOfProductId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The productId of all the products : " + listOfProductId);
        System.out.println("------------------End of Test---------------------------");
    }

    //26. Print the size of the data list
    @Test
    public void test026() {
        List<Integer> sizeOfDataList = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list : " + sizeOfDataList.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test027() {
        List<HashMap<String,?>> values = response.extract().path("data.findAll{it.name =='Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name product  : " + values.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test028() {
        List<String>  modeOfTheProduct = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Energizer - N Cell E90 Batteries (2-Pack) : " + modeOfTheProduct);
        System.out.println("------------------End of Test---------------------------");
    }

    //29. Get all the categories of 8th products
    @Test
    public void test029() {
        List<HashMap<String,?>> product8th = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of 8th store : " + product8th);
        System.out.println("------------------End of Test---------------------------");
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test030() {
        List<String> categories = response.extract().path("data.findAll{it.id='150115'}.find{it.categories}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store where product id is 150115 : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //31. Get all the descriptions of all the products
    @Test
    public void test031() {
        List<Integer> allTheDescriptions = response.extract().path("data.descriptions");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all the descriptions are  : " + allTheDescriptions);
        System.out.println("------------------End of Test---------------------------");
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test032() {
        List<Integer> listOfAllCategories = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Id for all categories are : " + listOfAllCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test033() {
        List<String> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store product Where type is HardGood : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test034() {
        List<Hashtable<String,?>> totalNumberOfCategories = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.find{it.categories}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of  categories for product name is  : " + totalNumberOfCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test035() {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Products whose price < 5.49  : " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test036() {
        List<String> nameOfAllCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.find{it.categories.name}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all categories Where product name : " + nameOfAllCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //37. Find the manufacturer of all the products
    @Test
    public void test037() {
        List<Integer> manufactureOfAllTheproducts = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The manufacturer of all the products : " + manufactureOfAllTheproducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test038() {
        List<String> imageOfProducts = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The image of products whose manufacturer is Energizer : " + imageOfProducts);
        System.out.println("------------------End of Test---------------------------");
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test039() {
        List<HashMap<String, ?>> price = response.extract().path("data.findAll{it.price>5.99}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store services details is  : " + price);
        System.out.println("------------------End of Test---------------------------");
    }

    //40. Find the uri of all the products
    @Test
    public void test040() {
        List<String> uriProducts = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores : " + uriProducts);
        System.out.println("------------------End of Test---------------------------");
    }
}

