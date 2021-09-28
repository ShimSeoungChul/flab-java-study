package Rational;

public class Test {
    void gcdtest() {
        var r1 = new Rational(2,3);
        assert(r1.getNumer()==2 && r1.getDenom()==3);

        var r2 = new Rational(2,10);
        assert(r2.getNumer()==1 && r2.getDenom()==5);
    }

    void plusTest(){
        var r1 = new Rational(1,5);
        var r2 = new Rational(2,10);
        var r3 = r1.plus(r2);
        assert(r3.getNumer()==2 && r3.getDenom()==5);
    }

    void plusLongTest(){
        var r1 = new Rational(1,5);
        var r2 = r1.plus(3);
        assert(r2.getNumer()==3 && r2.getDenom()==5);
    }

    void minusTest(){
        var r1 = new Rational(4,5);
        var r2 = new Rational(3,5);
        var r3 = r1.minus(r2);
        assert(r3.getNumer()==1 && r3.getDenom()==5);
    }

    void timesTest(){
        var r1 = new Rational(4,5);
        var r2 = new Rational(3,5);
        var r3 = r1.times(r2);
        assert(r3.getNumer()==12 && r3.getDenom()==25);
    }

    void divideTest(){
        var r1 = new Rational(4,125);
        var r2 = new Rational(2,5);
        var r3 = r1.divide(r2);
        assert(r3.getNumer()==2 && r3.getDenom()==25);
    }

    // 다른 테스트 메서드 정의
    public static void main(String[] args) {
        var testSuite = new Test();
        testSuite.gcdtest();
        // 다른 테스트 메서드 호출
        testSuite.plusTest();
        testSuite.plusLongTest();
        testSuite.minusTest();
        testSuite.timesTest();
        testSuite.divideTest();

    }
}
