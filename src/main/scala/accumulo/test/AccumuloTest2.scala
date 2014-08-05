package accumulo.test

import org.apache.accumulo.core.client.ZooKeeperInstance
import org.apache.accumulo.core.client.security.tokens.{AuthenticationToken, PasswordToken}
import org.apache.spark._

import org.apache.accumulo.core.client.mapreduce.AccumuloInputFormat
import org.apache.accumulo.core.client.mapreduce.lib.util.{InputConfigurator, ConfiguratorBase => CB}
import org.apache.accumulo.core.data.{Value, Key, Range => ARange}
import scala.collection.JavaConversions._

object AccumuloTest2 {
  def main(args: Array[String]) {

    val sparkConf =
      new SparkConf()
        .setMaster("local[4]")
        .setAppName("SparkAccumulo")

    val sc = new SparkContext(sparkConf)

    val conf = sc.hadoopConfiguration
    val user = "root"
    val authToken = new PasswordToken("secret")
    val instance = new ZooKeeperInstance("gis", "localhost")
    val table = "users"
    CB.setConnectorInfo(classOf[AccumuloInputFormat], conf, user, authToken)
    CB.setZooKeeperInstance(classOf[AccumuloInputFormat], conf, instance.getInstanceName, instance.getZooKeepers)


    InputConfigurator.setInputTableName(classOf[AccumuloInputFormat], conf, table)
    InputConfigurator.setRanges(classOf[AccumuloInputFormat], conf, List(new ARange()))

    val rdd = sc.newAPIHadoopRDD(conf, classOf[AccumuloInputFormat], classOf[Key], classOf[Value])

    println(s"RECORD COUNT IS: ${rdd.count()}")
  }
}
