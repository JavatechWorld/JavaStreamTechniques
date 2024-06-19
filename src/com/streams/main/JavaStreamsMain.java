package com.streams.main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Collector;
import java.util.Collection;

public class JavaStreamsMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		usingParallelStreamsForCocurrency();
		usingCollectorsForComplexAggregation();
		customCollectorsForSpecializedReductions();
		usingFlatMapForFlatteningNestedStructure();

		combineReduceForCustomReduction();
	}

	public static void usingParallelStreamsForCocurrency() {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		int sum = numbers.parallelStream().filter(n -> n % 2 == 0).mapToInt(Integer::intValue).sum();
		System.out.println("Sum of all even numbers:" + sum);
	}

	public static void usingCollectorsForComplexAggregation() {
		List<Employee> employees = Arrays.asList(new Employee("Alice", "HR"), new Employee("Bob", "IT"),
				new Employee("Charlie", "HR"), new Employee("David", "IT"), new Employee("Eve", "Finance"));

		// Group employees by department
		Map<String, List<Employee>> employeesByDepartment = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment));

		// Print the result
		employeesByDepartment.forEach((department, employeeList) -> {
			System.out.println(department + ": " + employeeList);
		});
	}

	public static void customCollectorsForSpecializedReductions() {
		List<Employee> employees = Arrays.asList(new Employee("Alice", "HR"), new Employee("Bob", "IT"),
				new Employee("Charlie", "HR"), new Employee("David", "IT"), new Employee("Eve", "Finance"));

		Collector<Employee, ?, String> customCollector = Collector.of(StringBuilder::new,
				(builder, emp) -> builder.append(emp.getName()).append(", "), StringBuilder::append,
				StringBuilder::toString);
		String names = employees.stream().collect(customCollector);
		System.out.println("All the name :::" + names);

	}

	public static void usingFlatMapForFlatteningNestedStructure() {
		List<List<String>> nestedList = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d"));
		List<String> flattenedList = nestedList.stream().flatMap(Collection::stream).collect(Collectors.toList());
		System.out.println("Falttened List:::" + flattenedList);
	}

	public static void combineReduceForCustomReduction() {
		List<Integer> numbers = Arrays.asList(1, 2, 3);
		int product = numbers.stream().reduce(1, (a, b) -> a * b);// 1*1 =1,1*2 =2,2*3=6,
		System.out.println("Product of all ements :" + product);
	}
}
