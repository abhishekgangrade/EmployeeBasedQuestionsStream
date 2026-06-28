import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {

    public static void main(String[] args) {

        EmployeeList employeeList = new EmployeeList();
        List<Employee> employees = employeeList.getEmpList();

        Main m = new Main();
        m.groupEmployeeByCity(employees);
        m.groupEmployeeByAge(employees);
        m.countMaleAndFemaleEmployeeInOrganization(employees);
        m.namesOfAllDepartments(employees);
        m.ageOfEmployee(employees);
        m.maxAgeOfEmployee(employees);
        m.averageAgeOfMaleAndFemaleEmployees(employees);
        m.numberOfEmployeesInEachDepartment(employees);
        m.findOldestEmployee(employees);
        m.youngestFemaleEmployee(employees);
        m.ageGreaterAndLessThanThirty(employees);
        m.departmentWithHighestEmployees(employees);
        m.cityWithMaximumEmployees(employees);
        m.anyEmployeeFromHRDept(employees);
        m.distinctDepartmentNameThatEmployeesWorkFor(employees);
        m.employeesLivesInBlore(employees);
        m.numberOfEmployees(employees);
        m.employeeCountInEveryDepartment(employees);
        m.highestNumberOfEmployees1(employees);
        m.sortByAgeAndName(employees);
        m.secondHighestPaidEmployee(employees);
        m.highestPaidEmployeeInEachDept(employees);




    }

    public void groupEmployeeByCity(List<Employee> li){

       Map<String, List<Employee>> result = li.stream()
                .collect(Collectors.groupingBy(Employee::getCity));
        System.out.println("Grouping employee by city: " + result);
    }

    public void groupEmployeeByAge(List<Employee> li){

        Map<Integer, List<Employee>> result = li.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println("Grouping Employees by age: " + result);
    }

    public void countMaleAndFemaleEmployeeInOrganization(List<Employee> li){
        
        Map<String, Long> result = li.stream()
                .collect(Collectors.groupingBy(Employee::getGender
                        , Collectors.counting()));
        System.out.println("Count male & female employee: " + result);
        
    }

    public void namesOfAllDepartments(List<Employee> li){
        Map<String, List<Employee>> result = li.stream().
                collect(Collectors.groupingBy(Employee::getDeptName));
        System.out.println("Name of all dept: " + result);

    }

    public void ageOfEmployee(List<Employee> li){

       List<Employee> list =  li.stream().filter(emp -> emp.getAge()>18)
                .collect(Collectors.toList());
        System.out.println("Employess age > 18: " + list);

    }

    public void maxAgeOfEmployee(List<Employee> li) {

       Optional<Employee> list = li.stream().max(Comparator.comparingInt(Employee::getAge));
        System.out.println("Max age of an employee: " + list);

    }

    public void averageAgeOfMaleAndFemaleEmployees(List<Employee> li) {

        Map<String, Double> averageAge = li.stream()
                .collect(Collectors.groupingBy(
                        Employee::getGender,
                        Collectors.averagingDouble(Employee::getAge)
                ));

        System.out.println("Average Age of male & female employees: " + averageAge);
    }

    public void numberOfEmployeesInEachDepartment(List<Employee> li){
        Map<String, Long> emp = li.stream().
                collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));

        System.out.println("Number of employees in each department: " + emp);
    }

    public void findOldestEmployee(List<Employee> li) {
        Optional<Employee> d = li.stream().min(Comparator.comparingInt(Employee::getYearOfJoining));
        System.out.println("Oldest Employee in organization " + d );

    }

    public void youngestFemaleEmployee(List<Employee> li){

        Optional<Employee> youngestEmployee = li.stream().filter(f -> f.getGender() == "F")
                .min(Comparator.comparingInt(Employee::getAge));
        System.out.println("Youngest female employee: " + youngestEmployee);
    }

    public void ageGreaterAndLessThanThirty(List<Employee> li) {

        Map<Boolean, List<Employee>> emp =  li.stream().collect(Collectors.partitioningBy(e -> e.getAge()>30));
        System.out.println("Age greater than 30: " + emp);
    }

    public void departmentWithHighestEmployees(List<Employee> li){

        Map<String, Long> dc = li.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));

        Map.Entry<String, Long> result = dc.entrySet().stream().max(Map.Entry.comparingByValue())
                .orElse(null);

        System.out.println(result);



    }

    public void cityWithMaximumEmployees(List<Employee> li) {
        Map<String, Long> cm = li.stream().collect(Collectors.groupingBy(Employee::getCity, Collectors.counting()));

        Optional<Map.Entry<String, Long>> result  = cm.entrySet().stream().max(Map.Entry.comparingByValue());
        System.out.println(result);

    }

    public void anyEmployeeFromHRDept(List<Employee> li) {

        Map<String, List<Employee>> fromhrdept = li.stream().filter(n->n.getDeptName()=="HR").collect(Collectors.groupingBy(Employee::getDeptName));
        System.out.println(fromhrdept);

    }

    public void distinctDepartmentNameThatEmployeesWorkFor(List<Employee> li){
        Set<String> departments = li.stream()
                .map(Employee::getDeptName)
                .collect(Collectors.toSet());
        System.out.println(departments);

    }

    //Find all employees who lives in ‘Blore’ city, sort them by their name and print the names of employees.
    public void employeesLivesInBlore(List<Employee> li) {

       List<Employee> emo = li.stream().filter(n -> n.getCity()=="Blore")
                .sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());
        System.out.println(emo);
    }

    //No of employees in the organisation.

    public void numberOfEmployees(List<Employee> list){

        List<Long> number = Collections.singletonList(list.stream().count());
        System.out.println(number);
    }

    //Find employee count in every department
    public void employeeCountInEveryDepartment(List<Employee> li) {

        Map<String, Long> empCount = li.stream().collect(Collectors.groupingBy(Employee::getDeptName,Collectors.counting()));
        System.out.println(empCount);
    }

    //Find the department which has the highest number of employees.
    public void highestNumberOfEmployees1(List<Employee> li){

        Map<String, Long> findCountFirst = li.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        Optional<Map.Entry<String, Long>> fc = findCountFirst.entrySet().stream().max(Map.Entry.comparingByValue());
        System.out.println(fc);



    }

    //Sorting a Stream by age and name fields.
    public void sortByAgeAndName(List<Employee> li) {

        List<Employee> employees = li.stream()
                .sorted(Comparator.comparing(Employee::getAge)
                        .thenComparing(Employee::getName))
                .collect(Collectors.toList());

        System.out.println(employees);
    }


    //Few ChatGPT questions for practice again:

    //Find the second highest-paid employee.

    public void secondHighestPaidEmployee(List<Employee> emp){

       Optional<Employee> emp1 = emp.stream()
               .sorted(Comparator.comparingLong(Employee::getSalary).reversed())
               .limit(2)
                .skip(1)
                .findFirst();

        System.out.println("Highest 2nd paid employee is :" + emp1);


    }

    //Find the highest-paid employee in each department using Java 8 Streams.
    public void highestPaidEmployeeInEachDept(List<Employee> li) {
        Map<String, Optional<Employee>> hp= li.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(hp);

    }








}