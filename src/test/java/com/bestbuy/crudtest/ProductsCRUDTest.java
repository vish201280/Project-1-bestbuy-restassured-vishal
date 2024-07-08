package com.bestbuy.crudtest;

import com.bestbuy.model.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ProductsCRUDTest extends TestBase {
    static String name = "Battery Portable" + TestUtils.getRandomValue();
    static String type = "Chargable" + TestUtils.getRandomValue();
    static double price = 5.50;
    static String upc = "123456789";
    static double shipping = 5;
    static String description = "Long Life Guarantee";
    static String manufacturer = "Duracell";
    static String model = "AK 47";
    static String url = "http://www.bestbuy.com";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

    static int productId;

    @Test
    public void test001() {
        ProductPojo productpojo = new ProductPojo();
        productpojo.setName(name);
        productpojo.setType(type);
        productpojo.setPrice(price);
        productpojo.setUpc(upc);
        productpojo.setShipping(shipping);
        productpojo.setDescription(description);
        productpojo.setManufacturer(manufacturer);
        productpojo.setModel(model);
        productpojo.setImage(image);
        productpojo.setUrl(url);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productpojo)
                .post("/products");
        productId = response.then().contentType(ContentType.JSON).extract().path("id");
        response.then().statusCode(201);
        System.out.println("Id no is :" + productId);
        response.prettyPrint();
    }
    @Test
    public void test002() {
        Response response = given()
                .when()
                .get("/products" + "/" + productId);
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void test003(){
        ProductPojo productpojo = new ProductPojo();
        productpojo.setName(name);
        productpojo.setType(type);
        productpojo.setPrice(10.00);
        productpojo.setUpc(upc);
        productpojo.setShipping(shipping);
        productpojo.setDescription(description);
        productpojo.setManufacturer(manufacturer);
        productpojo.setModel(model);
        productpojo.setImage(image);
        productpojo.setUrl(url);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productpojo)
                .patch("/products" + "/" + productId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }
    @Test
    public void test004() {
        Response response = given()
                .when()
                .delete("/products" + "/" + productId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }



}
