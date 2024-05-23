# Music_Streaming-Service-API
This Application Based On MySQL Database

##### :purple_square: Its an API Based Web Application
## :one: Frameworks and Languages Used -
    1. SpringBoot
    2. JAVA
    3. Postman
    4. MySQL
    
## :two: Dependency Used
    1. Spring Web
    2. Spring Boot Dev Tools
    3. Lombok
    4. Spring Data JPA
    5. MySQL Online DB
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
## :three: Dataflow (Functions Used In)
### :purple_square: 1. Model - Model is used to Iniitialize the required attributes and create the accessable constructors and methods
#### :o: SignIn.java
```java
public class SignIn {

    private String email;
    private String password;
}
```

#### :o: Admin.java
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin{

    @Id
    private String name;
    private String email;
    private String password;
}
}
```

#### :o: Customer.java
```java
public class Customer {
    @Id
    private String name;
    private String address;
    private String email;
    private String contact;
    private String password;
    private Boolean subscription;
    private String subsDate;
    private String subsType;
}
```

#### :o: Customer.java
```java
public class Subscription {

    private Boolean isInterested;
    private String subscribeDate;
    private SubsType subscriptionType;
}
```

#### :o: Restaurent.java
```java
public class Restaurant {

    @Id
    private String name;
    private String location;
    private String rating;
    private String menu;
    private String type;
    private Category category;
}
```

#### :o: FoodMenu.java
```java
public class FoodMenu {

    @Id
    private String name;
}
```

#### :o: Orders.java
```java
public class Orders{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer orderId;
    private String orderName; //name of customer
    private String restaurantName;
    private String foodMenu;
    private String paymentMode;
    private ServiceMode serviceMode;
    private String customerDetails;
    private String paymentReceipt;
    private String revenueReport;
    private Boolean isDelivered;
    private Boolean isPaymentDone;
}
```

#### :o: Notification.java
```java
public class Notification{

    @Id
    private String notificationName;
    private Integer orderId;
    private Boolean status;
    private Boolean refund;
}
```

#### :o: Delivery.java
```java
public class Delivery{

    private String deliveryStatus;
    private String feedback;
}
```
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

### :purple_square: 2. Service - This Layer is used to write the logic of our CRUD operaions.
#### :o: AdminService.java
```java
@Service
public class AdminService{
    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    ICustomerRepo customerRepo;

    //CreateAdmin
    public Admin signUp(Admin adminData){
        adminRepo.save(adminData);
        return adminData;
    }
}
```

#### :o: CustomerService.java
```java
@Service
public class CustomerService {
    @Autowired
    ICustomerRepo customerRepo;

    //Create Customer Service
    public Customer createCustomer(String user) {
        JSONObject obj = new JSONObject(user);
        Customer oldUser = customerRepo.findByName(obj.getString("name"));
        ---------
        --------
    }
}
```

#### :o: RestroService.java
```java
@Service
public class RestroService {
    @Autowired
    RestroRepo restroRepo;

    //Create Restaurent
    public Restaurant createRestro(Restaurant res) {

        Restaurant oldRestro = restroRepo.findByName(res.getName());
        if(oldRestro != null){
            throw new IllegalStateException("Restaurent Already Registered!!");
        }
        else{
            restroRepo.save(res);
            return oldRestro;
        }
    }
}
```

#### :o: MenuService.java
```java
@Service
public class MenuService {
    @Autowired
    IMenuRepo menuRepo;

    //Create Menu Service
    public String createMenu(FoodMenu menus) {
        FoodMenu oldMenu = menuRepo.findByName(menus.getName());

        if(oldMenu == null){
            menuRepo.save(menus);
            return menus.getName() + " is Sucessfully created!!";
        }
        throw new IllegalStateException("Menu is Already Present!!");

    }
}
```

#### :o: OrderService.java
```java
@Service
public class OrderService{
    @Autowired
    IOrdersRepo orderRepo;

    @Autowired
    ICustomerRepo customerRepo;

    //CreateOrderService
    public Orders createOrder(String email, String orderData){
        JSONObject obj = new JSONObject(orderData);

        Customer oldCustomer = customerRepo.findByEmail(email);
        if(oldCustomer == null){
            throw new IllegalStateException("Customer not found!!");
        }

        //If not null
        --------------------------
        return newOrder;
    }

}
```

#### :o: NotifyService.java
```java
@Service
public class NotifyService{
    @Autowired
    INotifyRepo notifyRepo;

    @Autowired
    IOrdersRepo orderRepo;

    //True Some Conditions


    //Create Notification For Restaurant
    public Notification createNotify(Integer orderId){
        Orders newOrder = orderRepo.findById(orderId).get();

        if(newOrder == null){
            throw new IllegalStateException("Order Not found!!");
        }else{
            if(newOrder.getIsPaymentDone()){
                Notification notify = new Notification();
                notify.setNotificationName(newOrder.getRestaurantName());
                notify.setOrderId(newOrder.getOrderId());
                notifyRepo.save(notify);
                return notify;
            }
            throw new IllegalStateException("Payment is not done!!");
        }
    }
}
```
----------------------------------------------------------------------------------------------------------------------------------------------------

### :purple_square: 3. Controller - This Controller is used to like UI between Model and Service and also for CRUD Mappings.
#### :o: AdminController.java
```java
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    //Create Admin
    @PostMapping(value = "/createAdmin")
    public Admin signUp(@RequestBody Admin adminData){
        return adminService.signUp(adminData);
    }
}
```

#### :o: CustomerController.java
```java
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    //Create Customer
    @PostMapping(value = "/createCustomer")
    public Customer createCustomer(@RequestBody String user){
        return customerService.createCustomer(user);
    }
}
```

#### :o: RestroController.java
```java
@RestController
public class RestroController {
    @Autowired
    RestroService restroService;

    @Autowired
    RestroRepo restroRepo;

    //Create Restaurent
    @PostMapping(value = "/createRestro")
    public Restaurant createRestro(@RequestBody Restaurant res){
        return restroService.createRestro(res);
    }
}
```

#### :o: MenuController.java
```java
@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    //Create Food Menu
    @PostMapping(value = "/createMenu")
    public String createMenu(@RequestBody FoodMenu menus){
        return menuService.createMenu(menus);
    }
}
```

#### :o: MenuController.java
```java
@RestController
public class OrdersController {

    @Autowired
    OrderService orderService;

    @Autowired
    IOrdersRepo orderRepo;

    //CreateOrder
    @PostMapping(value="/createOrder/{name}")
    public Orders createOrder(@PathVariable String name, @RequestBody String orderData){
        return orderService.createOrder(name,orderData);
    }
}
```

#### :o: MenuController.java
```java
@RestController
public class OrdersController {

    @Autowired
    OrderService orderService;

    @Autowired
    IOrdersRepo orderRepo;

    //CreateOrder
    @PostMapping(value="/createOrder/{name}")
    public Orders createOrder(@PathVariable String name, @RequestBody String orderData){
        return orderService.createOrder(name,orderData);
    }
}
```

#### :o: NotifyController.java
```java
@RestController
public class NotifyController{

    @Autowired
    NotifyService notifyService;

    //Notification Done
    @PostMapping(value = "/notificationDone/{orderId}")
    public Orders notificationDone(@PathVariable Integer orderId){
        return notifyService.notificationDone(orderId);
    }
}
```
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
### :purple_square: 3. Repository : data access object (DAO) is an object that provides an abstract interface to some type of database or other persistence mechanisms.
#### :o: IAdminRepo.java
```java
@Repository
public interface IAdminRepo extends JpaRepository<Admin,String> {
    Admin findByEmail(String adminMail);
}
```
#### :o: ICustomerRepo.java
```java
@Repository
public interface ICustomerRepo extends JpaRepository<Customer, String> {
    Customer findByName(String name);
    Customer findByEmail(String email);
}
```

#### :o: IRestroRepo.java
```java
@Repository
public interface RestroRepo extends JpaRepository<Restaurant, String> {
    Restaurant findByName(String name);
    Restaurant findByLocation(String location);
    Restaurant findByRating(String rating);
    Restaurant findByType(String type);
}
```

#### :o: IMenuRepo.java
```java
@Repository
public interface IMenuRepo extends JpaRepository<FoodMenu, String> {
    FoodMenu findByName(String name);
}
```

#### :o: IOrdersRepo.java
```java
@Repository
public interface IOrdersRepo extends JpaRepository<Orders, Integer> {
    Orders findByOrderName(String orderName);
}
```

#### :o: INoifyRepo.java
```java
@Repository
public interface INotifyRepo extends JpaRepository<Notification,String> {
    Notification findByNotificationName(String notifyName);
}
```
-------------------------------------------------------------------------------------------------------------------------------------------------------
## :four: DataStructures Used in Project
    1. List
    2. JsonObject
    3. JsonArray
-------------------------------------------------------------------------------------------------------------------------------------------------------



