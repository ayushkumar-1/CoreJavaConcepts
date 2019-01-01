package concepts.practice.interfaces;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@FunctionalInterface
public interface Interface1 {

//default keyword
	void method1(String str);

	default void log(String str) {
		System.out.println("Interface 1");
	}
}

interface Interface2 {
	default void method2(String str) {};

	default void log(String str) {
		System.out.println("Interface 2");
	}
	static boolean isNull(String str) {
		System.out.println("Interface Null check");
		return str == null ? true: "".equals(str)?true:false;
	}
	/*
	 * This static method cannot hide the instance method of Object class
	static boolean equals(Object o) {
		
	}
	*/
	
	TestingConstructor test();
	
}

class Test implements Interface1, Interface2 {

	@Override
	public void method2(String str) {
		// TODO Auto-generated method stub

	}

	@Override
	public void method1(String str) {
		// TODO Auto-generated method stub

	}
	
	public boolean isNull(String str) {
		System.out.println("In Test isNull()");
		return true;
	}

	// Need to override the default methods as they have the same signature

	@Override
	public void log(String str) {
		// TODO Auto-generated method stub
		Interface1.super.log(str);
	}

	public static void main(String[] args) {
		
		Test t = new Test();
		if(false) {
		
		t.log("data");
		Interface2.isNull("hello");
		System.out.println(t.isNull("hello"));
		}
		
		Interface1 i1 = (str) -> System.out.println("Test "+str);
		i1.method1("hi");
		int num = 11;
		//To find if a given number is prime or not
		System.out.println(IntStream.range(2, num).noneMatch(index -> num % index == 0));
		
		//Passing Behaviours
		
		//summ all nums
		Integer[] arr = new Integer[]{23,33,12,1,-5,9,0,22};
		List<Integer> numbers = Arrays.asList(arr);
		int sum = sumWithCondition(numbers, n -> true);
		System.out.println("Sum: "+sum);
		
		int result = numbers.stream().filter(Test::isOdd).max(new MaxComparator())
		.map(i -> i*i)
		.get();
		System.out.println("Square of max odd number: "+result);
		
		Interface2 i2 = TestingConstructor :: new; //Target must be functional interface
		i2.test();
		t.test();
		
	}
	
	private static int sumWithCondition(List<Integer> numbers, Predicate<Integer> predicate) {
		//	   |<----------------IntStream data type-------------------->|	
		return numbers.parallelStream().filter(predicate).mapToInt(i -> i).sum();
	}
	
	public static boolean isOdd(int i){
		return i%2 != 0;
	}

	@Override
	public TestingConstructor test() {
		System.out.println("I am here as well");
		return null;
	}
	

}

class MaxComparator implements Comparator<Integer>{

	@Override
	public int compare(Integer o1, Integer o2) {
		return o2.compareTo(o1);
	}
	
}

class TestingConstructor {
	public TestingConstructor() {
		System.out.println("Testing Constructor");
	}
}
