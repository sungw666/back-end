package top.ptcc9.common;


public class Tuple2<E1,E2> {

    private E1 e1;
    private E2 e2;

    public static <E1,E2> Tuple2<E1,E2> build(E1 e1, E2 e2) {
        return new Tuple2<>(e1,e2);
    }

    public static <E1,E2> Tuple2<E1,E2> buildEmpty() {
        return new Tuple2<>();
    }

    private Tuple2(E1 e1, E2 e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    private Tuple2() {
    }

    public E1 getE1() {
        return e1;
    }

    public void setE1(E1 e1) {
        this.e1 = e1;
    }

    public E2 getE2() {
        return e2;
    }

    public void setE2(E2 e2) {
        this.e2 = e2;
    }
}
