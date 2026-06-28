import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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



}