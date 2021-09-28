package 자바의신1권마지막실습;

public enum SalaryIncreaseRateType {
    owner(1,-95), managr(2,10), designer(3,20), architect(4,30), developer(5,100);
    private int value;
    private int increaseRate;

    SalaryIncreaseRateType(int value, int increaseRate){
        this.value = value;
        this.increaseRate = increaseRate;
    }

    static int getIncreaseRate(int value){
        for(SalaryIncreaseRateType salaryIncreaseRateType: SalaryIncreaseRateType.values()){
            if(salaryIncreaseRateType.value == value)
                return salaryIncreaseRateType.increaseRate;
        }
        return 0;
    }

}