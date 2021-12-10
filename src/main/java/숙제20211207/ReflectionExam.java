package 숙제20211207;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

public class ReflectionExam {

	static final int personNum = 10_000_000;
	static long fieldTotalTime = 0L;
	static long generalMethodTotalTime = 0L;
	static long reflectionMethodTotalTime = 0L;

	public static void main(String[] args) throws
		NoSuchMethodException,
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException {

		// Person 클래스 생성자 만들기
		Class personClass = Person.class;
		Constructor personConstructor = personClass.getConstructor(new Class[]{String.class, int.class});

		// 객체 생성
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";

		Person[] people = new Person[personNum];
		for (int i = 0; i < personNum; i++){
			int age = getRandomAge();
			String name = getRandomName(10);
			Person person = (Person) personConstructor.newInstance(name, age);
			people[i] = person;
		}

		int repeat = 10;
		for (int i = 0; i < repeat; i ++){
			measureFieldAccessTime(people);
			measureGeneralMethodTime(people);
			measureReflectionMethodTime(people);
		}

		System.out.println("필드 접근 시간 평균:" + fieldTotalTime/10 + ", 일반 메서드 호출 시간 평균:" +
			generalMethodTotalTime/10 + ", 리플렉션 메서드 호출 시간 평균:" + reflectionMethodTotalTime/10);
	}

	public static int getRandomAge() {
		return ThreadLocalRandom.current().nextInt(1, 99);
	}

	public static String getRandomName(int len) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int randIdx = ThreadLocalRandom.current().nextInt(alphabet.length());
			char randChar = alphabet.charAt(randIdx);
			b.append(randChar);
		}

		return b.toString();
	}

	public static void measureFieldAccessTime(Person[] people){
		long before = System.currentTimeMillis(); //코드 실행 전 시간

		// 나이 조회
		int sum = 0;
		for (Person person : people){
			int age = person.getAge();
			sum += age;
		}

		long after = System.currentTimeMillis(); // 코드 실행 후 시간
		long diff = (after - before); //두 시간에 차이
		fieldTotalTime += diff;
		System.out.println("필드 직접 접근, 평균 나이:" + sum/personNum + ", 시간차이(밀리세컨즈) : "+diff);
	}

	public static void measureGeneralMethodTime(Person[] people){
		long before = System.currentTimeMillis(); //코드 실행 전 시간

		// 나이 조회
		int sum = 0;
		for (Person person : people){
			int age = person.getAge();
			sum += age;
		}

		long after = System.currentTimeMillis(); // 코드 실행 후 시간
		long diff = (after - before); //두 시간에 차이
		generalMethodTotalTime += diff;
		System.out.println("일반 메서드 호출, 평균 나이:" + sum/personNum + ", 시간차이(밀리세컨즈) : "+diff);
	}

	public static void measureReflectionMethodTime(Person[] people) throws
		InvocationTargetException,
		IllegalAccessException, NoSuchMethodException {
		long before = System.currentTimeMillis(); //코드 실행 전 시간

		// 이름 조회
		int sum = 0;
		Class personClass = Person.class;
		for (Person person : people){
			Method method = personClass.getMethod("getAge", null);
			int age = (int) method.invoke(person); // 나이 조회
			sum += age;
		}

		long after = System.currentTimeMillis(); // 코드 실행 후 시간
		long diff = (after - before); //두 시간에 차이
		reflectionMethodTotalTime += diff;
		System.out.println("리플렉션 메서드 호출, 평균 나이:" + sum/personNum + ", 시간차이(밀리세컨즈) : "+diff);
	}
}

