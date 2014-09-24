import com.mongodb.casbah.Imports._

/*
	More: 
		http://mongodb.github.io/casbah/tutorial.html
		http://www.scala-sbt.org/0.13/tutorial/Running.html
*/

object ScalaMongoSampleApp {
  def main(args: Array[String]): Unit = {

    val mongoConn = MongoConnection()
		val sampleColl = mongoConn("test")("temp-people")
		sampleColl.drop()

		sampleColl.insert(MongoDBObject("name" -> "Michal", "age" -> 128))
		sampleColl.insert(MongoDBObject("name" -> "Rafal"))
		
		val samplePerson = sampleColl.findOne("age" $exists true)

		println(samplePerson)
  }
}
