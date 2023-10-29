# Mongo-DB-Employee-Service-Example

# Steps
1. create a class Employee in model package and annotate 
```java
@Document("employee-document")

@Id
private String employeeId;
```

Note : after the api call only this collection will get generated.

2. create a class EmployeeRepository and extend with MongoRepository
```java
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    
}
```

3. application.properties file 
```properties
spring.data.mongodb.uri=mongodb://localhost:27017
spring.data.mongodb.database=employeedb
```

4. All Methods of CRUD are same of JPA.
5. in repository also if we want to write any custom method like findByEmployeeName()
```java
EmployeeDto findByEmployeeName(String employeeName);
```
