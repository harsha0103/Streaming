
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}



object Stream {
  def main(args: Array[String]): Unit = {


        val conf = new SparkConf().setMaster("local[*]").setAppName("NetworkWordCount")




        val ssc = new StreamingContext(conf, Seconds(10))
        ssc.checkpoint("file:///tmp/spark")
        val lines = ssc.socketTextStream("localhost", 9999)
        val words = lines.flatMap(_.split(" "))
        val pairs = words.map(word => (word, 1))
        val wordCounts = pairs.reduceByKey(_ + _)

        // Print the first ten elements of each RDD generated in this DStream to the console
        wordCounts.print()
        ssc.start()             // Start the computation
        ssc.awaitTermination()  // Wait for the computation to terminate


  }


}






