
package inventory.repository;

import inventory.model.Part;
import inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryRepositoryInMemory {

    // Declare fields
    private ObservableList<Product> allProducts;
    private ObservableList<Part> allParts;
    private int autoPartId;
    private int autoProductId;

    public InventoryRepositoryInMemory(){
        this.allProducts = FXCollections.observableArrayList();
        this.allParts= FXCollections.observableArrayList();
        this.autoProductId=0;
        this.autoPartId=0;
    }

    // Declare methods
    /**
     * Add new product to observable list products
     * @param product
     */
    public void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     * Remove product from observable list products
     * @param product
     */
    public void removeProduct(Product product) {
        allProducts.remove(product);
    }
    /**
     * Accepts search parameter and if an ID or name matches input, that product is returned
     * @param searchItem
     * @return
     */
    public Product lookupProduct(String searchItem) {
        if(searchItem.equals("")){
            return null;
        }
        boolean isFound = false;
        for(Product p: allProducts) {
            if(p.getName().contains(searchItem) || (p.getProductId()+"").equals(searchItem))
                return p;
            isFound = true;
        }
        if(!isFound) {
            return new Product(0, null, 0.0, 0, 0, 0, null);
        }
        return null;
    }

    /**
     * Update product at given index
     * @param index
     * @param product
     */
    public void updateProduct(int index, Product product) {
        allProducts.set(index, product);
    }

    /**
     * Getter for Product Observable List
     * @return
     */
    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(ObservableList<Product> list) {
        allProducts =list;
    }

    /**
     * Add new part to observable list allParts
     * @param part
     */
    public Part addPart(Part part) {
        allParts.add(part);
        return allParts.get(allParts.size()-1);
    }

    /**
     * Removes part passed as parameter from allParts
     * @param part
     */
    public void deletePart(Part part) {
        allParts.remove(part);
    }

    /**
     * Accepts search parameter and if an ID or name matches input, that part is returned
     * @param searchNameOrId
     * @return
     */
    public Part lookupPart(String searchNameOrId) {
        for(Part p:allParts) {
            if(p.getName().contains(searchNameOrId) || (p.getPartId()+"").equals(searchNameOrId)) return p;
        }
        return null;
    }

    /**
     * Update part at given index
     * @param index
     * @param part
     */
    public void updatePart(int index, Part part) {
        allParts.set(index, part);
    }

    /**
     * Getter for allParts Observable List
     * @return
     */
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * @param list
     */
    public void setAllParts(ObservableList<Part> list) {
        allParts=list;
    }

    /**
     * Method for incrementing part ID to be used to automatically
     * assign ID numbers to parts
     * @return
     */
    public int getAutoPartId() {
        autoPartId++;
        return autoPartId;
    }

    /**
     * Method for incrementing product ID to be used to automatically
     * assign ID numbers to products
     * @return
     */
    public int getAutoProductId() {
        autoProductId++;
        return autoProductId;
    }


    public void setAutoPartId(int id){
        autoPartId=id;
    }

    public void setAutoProductId(int id){
        autoProductId=id;
    }

}
