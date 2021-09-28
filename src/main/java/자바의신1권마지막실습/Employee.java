package 자바의신1권마지막실습;

import java.math.BigDecimal;

class Employee{
    private String name;
    private int type;
    private BigDecimal salary;

    Employee(String name, int type, BigDecimal salary) {
        this.name = name;
        this.type = type;
        this.salary = salary;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getType() {
        return type;
    }

    void setType(int type) {
        this.type = type;
    }

    BigDecimal getSalary() {
        return salary;
    }

    void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}