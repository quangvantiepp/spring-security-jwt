package nature.sales_website.repositories.queryValue;

public class UserQueryValue {
   public static final String GET_USER_BY_ID = """
           SELECT * FROM sales_website.users u where u.id= :userId
            """;
   public static final String GET_USER_BY_EMAIL = """
           SELECT * FROM sales_website.users u where u.email= :userEmail
           """;
   public static final String GET_USER_BY_PHONE_NUMBER = """
           SELECT * FROM sales_website.users u where u.phone_number= :phoneNumber
           """;

}
