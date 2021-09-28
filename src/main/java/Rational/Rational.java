package Rational;

import java.util.Objects;

final class Rational {
    private final long numer; // 분자
    private final long denom; // 분모

    /* 위 numer, denom을 private로 하고 게터/세터를 만들 수도 있지만, 그렇게 하는게 좋은 설계일까? 어차피 numer,denum은 final인데?*/
    //-> 추후 수정할 때 getter, setter를 변경할 때 더 유리하다.
    // 예를 들어 수정 사항이 발생하여 Rational 클래스의 분자, 분모 값을 고객에 따라 다른 데이터를 주어여한다고 가정할때,
    // Getter를 이용하면    메서드 매개변수로 고객의 정보를 받아 다른 로직을 손쉽게 처리할 수 있다.

    long getNumer() {
        return numer;
    }

    long getDenom() {
        return denom;
    }


    Rational(final long numer, final long denom) throws IllegalArgumentException {
        if(denom==0L) throw new IllegalArgumentException("분모는 0이 아닌 long이어야 합니다.");
        long gcd = gcd(numer,denom);
        this.numer = numer/gcd;
        this.denom = denom/gcd;
    }

    Rational(final long wholeInteger) {
        this.numer = wholeInteger;
        this.denom = 1;
    }

    // 무식한 유클리드 호제법
    private long gcd(long a, long b) {
        while(b!=0) {
            var tmp = a; // 임시변수
            a = b;
            b = tmp % b;
        }
        return a;
    }

    // rational * rational
    public Rational plus(Rational other) {
        var commonDenom = denom * other.denom;
        var newNumer = numer*other.denom + denom*other.numer;
        return new Rational(newNumer, commonDenom);
    }
    // rational * long
    public Rational plus(long coefficient) {
        return new Rational(coefficient * numer, denom);
    }

    // **자바에서는 long.plus(rational)을 정의할 방법이 없음
    // minus, times, divide 등의 다른 메서드 정의 필요
    public Rational minus(Rational other){
        var commonDenom = denom * other.denom;
        var newNumer = numer*other.denom - other.numer*denom;
        return new Rational(newNumer, commonDenom);
    }

    public Rational times(Rational other){
        var newDenom = denom * other.denom;
        var newNumer = numer * other.numer;
        return new Rational(newNumer, newDenom);
    }

    public Rational divide(Rational other){
        var newDenom = denom * other.numer;
        var newNumer = numer * other.denom;
        return new Rational(newNumer, newDenom);
    }

    // toString을 정의해야 제대로 값을 볼 수 있음!
    @Override
    public String toString() {
        return "Rational{" +
                "numer=" + numer +
                ", denom=" + denom +
                '}';
    }

    // equals와 hashCode도 정의해야 함
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rational rational = (Rational) o;
        return numer == rational.numer && denom == rational.denom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numer, denom);
    }


}