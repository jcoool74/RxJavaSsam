import io.reactivex.Flowable

fun main() {
    val arrayOf = arrayOf("girl", "boy")
    hello(arrayOf);
}

fun hello(args: Array<String>) {
    Flowable.fromArray(*args).subscribe { s -> println("Hello $s!") }
}