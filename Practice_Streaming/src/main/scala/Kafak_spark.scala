import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka._


object Kafak_spark {
  def main(args: Array[String]): Unit = {
    val spark= SparkSession.builder.master("local[3]").appName("Name").config("spark.driver.memory","2g").getOrCreate()

    val ssc = new StreamingContext(spark.sparkContext, Seconds(10))

    val kafka= KafkaUtils.createStream(ssc,"Localhost:2181","test-consumer-group",Map("sampleTopic"->1))

    kafka.print
    ssc.start()
    ssc.awaitTermination()

  }
}
