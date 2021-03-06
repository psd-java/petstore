package test.system.com.pyxis.petstore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.support.com.pyxis.petstore.web.DatabaseDriver;
import test.support.com.pyxis.petstore.web.PetStoreDriver;
import test.system.com.pyxis.petstore.page.HomePage;
import test.system.com.pyxis.petstore.page.ProductsPage;

import static test.support.com.pyxis.petstore.builders.ProductBuilder.aProduct;

public class SearchFeature {

    PetStoreDriver petstore = new PetStoreDriver();
    DatabaseDriver database = new DatabaseDriver();
    HomePage home;

    @Before public void
    startApplication() throws Exception {
        database.start();
        home = petstore.start();
    }

    @Test public void
    searchDoesNotMatchAnyProductInCatalog() throws Exception {
        database.given(aProduct().withName("Labrador Retriever"));
        
        ProductsPage productsPage = home.searchFor("Dalmatian");
        productsPage.showsNoResult();
    }

    @Test public void
    findsProductsInCatalog() throws Exception {
        database.given(
                aProduct().withNumber("LAB-1234").withName("Labrador Retriever"),
                aProduct().withNumber("CHE-5678").withName("Chesapeake").describedAs("Chesapeake bay retriever"),
                aProduct().withName("Dalmatian"));

        ProductsPage productsPage = home.searchFor("retriever");
        productsPage.displaysNumberOfResults(2);
        productsPage.displaysProduct("Labrador Retriever");
        productsPage.displaysProduct("Chesapeake");
    }

    @After public void
    stopApplication() {
    	database.stop();
        petstore.stop();
    }

}
