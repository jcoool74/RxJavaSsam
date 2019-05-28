import io.reactivex.*;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class test_01 {
    public static void main(String args[]) {
        if (false) {
            hello("boy", "girl");
        } else {
//            test_01();
            test_02();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    public static void hello(String... args) {
        Flowable.fromArray(args).subscribe(s -> System.out.println("Hello " + s + "!"));
    }

    private static void test_01() {
        Single.just("111").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("accept: " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("accept - throwable: " + throwable.getMessage());
            }
        });
    }

    private static void test_02() {
        Single<String> single = Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                Thread thread = new Thread(() -> {
                    try {
                        emitter.onSuccess("hello world");
                    } catch (Exception e) {
                        emitter.onError(e);
                    }
                });
                thread.start();
            }
        });
        single.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("accept: " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("accept - throwable: " + throwable.getMessage());
            }
        });


    }
}
