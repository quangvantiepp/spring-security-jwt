package nature.sales_website.repositories.queryValue;

public class RoleQueryValue {
    public static final String GET_ROLE_BY_ID = "SELECT * FROM sales_website.roles u where u.id= :roleId";
}
