# Suppliers Union
Suppliers Union is a Desktop application for suppliers to manage their products and distributors.
## Features
- Suppliers can add new products
- Suppliers can add new distributors, and those can also add new users
- Users can search for products

## Design Patterns Used

A total of 7 Design patterns have been utilized for the project. Below follows a brief description. 

### 1. Factory Method

The Factory pattern has been used to create a parametrized RepoFactory to access Repository objects without explicitly creating
them. It is used throughout the application where access to the database is required. 

### 2. Singleton 

Singleton pattern is utilized in the AppContext class. Because one AppContext instance is required for the application,
we have made this particular class a singleton to achieve that requirement.

###  3. Composite

The Supplier object holds other suppliers as its distributors. This makes it a good candidate for the composite pattern.
This pattern lets us compose objects into tree structures and then work with these structures as if they were individual 
objects.

### 4. Bridge 

Searching products can be implemented in different ways and the client only needs to have an abstraction and should not
depend on a specific implementation. To achieve this we use the Bridge pattern. Bridge is a structural design pattern that 
lets us split a large into two separate abstraction and implementation and can  be developed independently of each other.

### 5. Proxy

We have created a UserAuthProxy to protect access UserAuth Object, which is used for logging in and out of the application. 
Through this proxy we can make sure a user exists before logging in and show error message if it doesn't exist. 

### 6. Chain of Responsibility

Chain of responsibility pattern is used to process a Search request. There are different handlers like ProductNameSearch, 
ProductPriceSearch and ProductOwnerSearch. Each handler processes the request and if it can handle it, will send it to the
next handler.

### 7. State

The State pattern is used to change the state of user authentication. It holds two states, LoggedInState and LoggedOutState.
The Application changes between two states when the user is logs in and out. 
 
