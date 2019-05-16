import io.reactivex.Flowable;

public class test_01 {
    public static void main(String args[]) {
        hello("boy", "girl");
    }
    public static void hello(String... args) {
        Flowable.fromArray(args).subscribe(s -> System.out.println("Hello " + s + "!"));
    }
}
