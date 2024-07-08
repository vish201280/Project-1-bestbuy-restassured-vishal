package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import static io.restassured.RestAssured.given;


public class StoresExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void start() {
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String nameOfStore = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + nameOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> listOfAllStore = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all store is : " + listOfAllStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store@Test
    @Test
    public void test005() {
        List<Integer> listOfStoreId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store@Test : " + listOfStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> sizeOfDataList = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list : " + sizeOfDataList.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String,?>> stCloud = response.extract().path("data.findAll{it.name =='St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the store name  St Cloud : " + stCloud.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<String> rochester = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of the store name Rochester : " + rochester);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<HashMap<String,?>> listOfAllServices = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services of 8th store : " + listOfAllServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store services of the store where service name  is Windows Store : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<Integer> allStoreId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of storeId is  : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<Integer> listOfAllStoreId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get id of all the store : " + listOfAllStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where state is ND : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<Hashtable<String,?>> totalNumberOfServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services for store name is Rochester : " + totalNumberOfServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<HashMap<String, ?>> createdAtOfServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all services whose name is Windows Store  : " + createdAtOfServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<String> nameOfAllServicesFargo = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services Where store name is Fargo : " + nameOfAllServicesFargo);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void test017() {
        List<Integer> zipOfAllTheStore = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store : " + zipOfAllTheStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<String> zipOfRoseville = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The s zip of store name is Roseville : " + zipOfRoseville);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store services details is  : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<HashMap<String,?>> latOfAllTheStores = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores : " + latOfAllTheStores);
        System.out.println("------------------End of Test---------------------------");
    }
}

