package mockLab4Tests;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepositoryFile;
import inventory.service.InventoryService;
import inventory.validator.PartValidator;
import inventory.validator.ProductValidator;
import inventory.validator.ValidationException;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static inventory.validator.PartValidator.minErrMsg;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationEntityTest {
    InventoryService service;

    @BeforeEach
    void setUp() {
        PartValidator partValidator = new PartValidator();
        ProductValidator productValidator = new ProductValidator();
        InventoryRepositoryFile repo = new InventoryRepositoryFile(partValidator, productValidator);
        service = new InventoryService(repo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addInhousePart_validData_OK() {
        try {
            //setup
            Part part=new InhousePart(1,"Piulita", 0.5, 200, 5, 1000, 1);

            //act
            Part part_service=service.addInhousePart("Piulita", 0.5, 200, 5, 1000, 1);
            part.setPartId(part_service.getPartId());

            //assert
            assert(part_service.equals(part));
        } catch (ValidationException err) {
            assert (false);
        }
    }

    @Test
    void addInhousePart_InvalidMin_Exception() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            service.addInhousePart("Surub", 1, 200, -5, 1000, 34);
        });

        String expectedMessage = minErrMsg;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void lookupProduct_validData() {
        try {
            //setup

            Part part1 = new Part(1, "a", 20, 10, 3, 20);

            List<Part> partslist = new ArrayList<>();

            service.addInhousePart("a", 20, 10, 3, 20, 5);


            Product product=new Product(0,"foarfeca", 50000, 50, 2, 10, service.getAllParts());

            //act

           service.addProduct("foarfeca", 50000, 50, 2, 100, service.getAllParts());

//            //assert
           Product product1=  service.lookupProduct("foarfeca");
           assert(product1.getName().equals(product.getName()));
        } catch (ValidationException err) {
            System.out.println(err.getMessage());
            assert (false);
        }
    }
}