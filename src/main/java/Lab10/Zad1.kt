import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.selects.select

fun main() {
    val N = 10

    val producerChannels = ArrayList<Channel<Int>>()
    val consumerChannels = ArrayList<Channel<Int>>()
    for (i in 1..N) {
        producerChannels.add(Channel())
        consumerChannels.add(Channel())
    }

    runBlocking {
        launch { producer(producerChannels) }
        for (i in 0 until N) {
            launch { broker(producerChannels[i], consumerChannels[i]) }
        }
        launch { consumer(consumerChannels) }
    }
}

suspend fun producer(brokers: ArrayList<Channel<Int>>) {
    while (true) {
        select<Unit> {
            brokers.forEach { channel -> channel.onSend(0){}}
        }
        delay(100)
    }
}

suspend fun broker(producer: Channel<Int>, consumer: Channel<Int>) {
    while (true) {
        consumer.send(producer.receive())
    }
}

suspend fun consumer(brokers: ArrayList<Channel<Int>>) {
    while (true) {
        select<Unit> {
            brokers.forEach { channel -> channel.onReceive { value -> println("$value consumed") } }
        }
        delay(100)
    }
}
