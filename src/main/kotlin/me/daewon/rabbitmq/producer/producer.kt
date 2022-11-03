import com.rabbitmq.client.ConnectionFactory
import java.nio.charset.StandardCharsets

fun main() {
    val factory = ConnectionFactory()
    factory
        .newConnection("amqp://guest:guest@localhost:5672")
        .use { connection ->
            connection
                .createChannel()
                .use { channel ->
                    channel
                        .queueDeclare(
                            "test_queue",
                            false,
                            false,
                            false,
                            null
                        )
                    val message = "Hello World!"
                    channel.basicPublish(
                        "",
                        "test_queue",
                        null,
                        message.toByteArray(StandardCharsets.UTF_8)
                    )
                    println(" [x] Sent '$message'")
                }
        }
}
