import com.zuora.product.Product;
import com.zuora.product.ProductCategory;
import java.util.Arrays;
import java.util.List;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@Test
public class ApplicationTest {


    private ProductCategory category;

    private ProductCategory subCategory_1;
    private ProductCategory subCategory_2;
    private ProductCategory subCategory_4;

    private Application application;

    @Mock
    private Product product1;
    @Mock
    private Product product2;

    @Mock
    private Product product3;
    @Mock
    private Product product4;

    @Mock
    private Product product5;
    @Mock
    private Product product6;

    @Mock
    private Product product7;

    private List<Product> products;
    private List<Product> allProductsForCartegory;
    private List<Product> allProductsFromAllLevelsOfCartegory;
    private List<Product> productsInSubcategory_1;
    private List<Product> productsInSubcategory_2;
    private List<Product> productsInSubcategory_4;

    private List<ProductCategory> subCategories;
    private List<ProductCategory> nestedSubCategories;


    @BeforeMethod
    public void setUp() {
        initMocks(this);

        products = Arrays.asList(product1, product2);
        productsInSubcategory_1 = Arrays.asList(product3, product4);
        productsInSubcategory_2 = Arrays.asList(product5, product6);
        productsInSubcategory_4 = Arrays.asList(product7);

        allProductsForCartegory = Arrays.asList(product1, product2, product3, product4, product5, product6);
        allProductsFromAllLevelsOfCartegory = Arrays.asList(product1, product2, product3, product4, product7, product5, product6);

        category = new ProductCategory();
        category.setParentCategory(null);
        category.setProducts(products);
        category.setCategoryName("Electronics");

        subCategory_1 = new ProductCategory();
        subCategory_1.setProducts(productsInSubcategory_1);
        subCategory_1.setCategoryName("Computer");


        subCategory_2 = new ProductCategory();
        subCategory_2.setProducts(productsInSubcategory_2);
        subCategory_2.setCategoryName("TV");

        subCategory_4 = new ProductCategory();
        subCategory_4.setProducts(productsInSubcategory_4);
        subCategory_4.setCategoryName("Laptop");

        subCategories = Arrays.asList(subCategory_1, subCategory_2);
        nestedSubCategories = Arrays.asList(subCategory_4);

        application = new Application();
    }

    public void shouldListAllProductsInCategory() {
        category.setSubCategories(null);
        assertThat(application.listAllProductsInCategory(category), is(products));
    }

    public void shouldListAllProductsFromSubcategory() {
        category.setSubCategories(subCategories);
        assertThat(application.listAllProductsInCategory(category), is(allProductsForCartegory));
    }

    public void shouldListAllProductsFromAllLevelsOfSubCategories() {
        category.setSubCategories(subCategories);
        subCategory_1.setSubCategories(nestedSubCategories);

        assertThat(application.listAllProductsInCategory(category), is(allProductsFromAllLevelsOfCartegory));
    }


    public void shouldReturnFullCategoryPathOfAProduct() {
        category.setSubCategories(subCategories);
        subCategory_1.setParentCategory(category);
        subCategory_1.setSubCategories(nestedSubCategories);
        subCategory_4.setParentCategory(subCategory_1);
        when(product7.getCategory()).thenReturn(subCategory_4);
        assertThat(application.categoryPathForProduct(product7), is("Electronics/Computer/Laptop"));
    }


}