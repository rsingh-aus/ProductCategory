import com.zuora.product.Product;
import com.zuora.product.ProductCategory;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public List<Product> listAllProductsInCategory(ProductCategory productCategory) {

        List<Product> result = new ArrayList<>();

        addProductsFromAllLevels(productCategory, result);
        return result;
    }

    public String categoryPathForProduct(Product product) {
        return getPath(product.getCategory());
    }

    private String getPath(ProductCategory category) {
        if(category.getParentCategory() == null) {
            return category.getCategoryName();
        }
        return getPath(category.getParentCategory()) + "/" + category.getCategoryName();
    }

    private void addProductsFromAllLevels(ProductCategory productCategory, List<Product> result) {
        addProductsForCategory(productCategory, result);
        addProductsFromSubcategories(productCategory, result);
    }

    private void addProductsFromSubcategories(ProductCategory productCategory, List<Product> result) {
        if(productCategory.getSubCategories() != null) {
            productCategory.getSubCategories().
                    stream().
                    forEach(category -> addProductsFromAllLevels(category, result));
        }
    }

    private void addProductsForCategory(ProductCategory category, List<Product> result) {
        if(category.getProducts() != null) {
            category.getProducts().stream().forEach(product -> result.add(product));
        }
    }


}
