package nature.sales_website.repositories.queryValue;

public class UserQueryValue {
   public static final String getUserById = """
           SELECT * FROM sales_website.users u where u.id= :userId
            """;
   public static final String getUserByEmail = """
           SELECT * FROM sales_website.users u where u.email= :userEmail
           """;
   public static final String getUserByPhoneNumber = """
           SELECT * FROM sales_website.users u where u.phone_number= :phoneNumber
           """;
}
