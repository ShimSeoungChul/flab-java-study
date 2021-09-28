package 자바의신1권마지막실습;

import java.math.BigDecimal;

public class CalculateSalary {
    public static void main(String args[]){
        CalculateSalary calculateSalary = new CalculateSalary();
        calculateSalary.calculateSalaries();
    }

    public BigDecimal getSalaryIncrease(Employee employee){
        int increaseRate = SalaryIncreaseRateType.getIncreaseRate(employee.getType());
        BigDecimal increasedSalary = employee.getSalary().multiply(new BigDecimal(Double.toString(increaseRate/100)));
        return employee.getSalary().add(increasedSalary);
    }

    public void calculateSalaries(){
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("LeeDaeRi",1,BigDecimal.valueOf(1_000_000_000));
        employees[1] = new Employee("KimManager",2,BigDecimal.valueOf(100_000_000));
        employees[2] = new Employee("WhangDesign",3,BigDecimal.valueOf(70_000_000));
        employees[3] = new Employee("ParkArchi",4,BigDecimal.valueOf(80_000_000));
        employees[4] = new Employee("LeeDevelop",5,BigDecimal.valueOf(60_000_000));

        for(Employee employee: employees){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(employee.getName());
            stringBuilder.append("=");
            stringBuilder.append(getSalaryIncrease(employee));
            System.out.println(stringBuilder.toString());
        }
    }
}