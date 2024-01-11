import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

fun main() {
    val N = 10
    val channels = ArrayList<Channel<Int>>()
    for (i in 0..N) {
        channels.add(Channel())
    }

    runBlocking {
        launch { producer2(channels[0]) }
        for (i in 1 .. N) {
            launch { broker2(channels[i-1], channels[i]) }
        }
        launch { consumer2(channels[N]) }
    }
}

suspend fun producer2(broker: Channel<Int>) {
    while (true) {
        broker.send(0)
        delay(100)
    }
}

suspend fun broker2(producer: Channel<Int>, consumer: Channel<Int>) {
    while (true) {
        consumer.send(producer.receive()+1)
        delay(100)
    }
}

suspend fun consumer2(broker: Channel<Int>) {
    while (true) {
        val received = broker.receive()
        println("$received consumed")
        delay(100)
    }
}
