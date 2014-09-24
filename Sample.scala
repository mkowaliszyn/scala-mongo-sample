import com.mongodb.{Mongo, WriteConcern}
import com.mongodb.casbah.Imports._

import com.github.jmkgreen.morphia.Morphia
import com.github.jmkgreen.morphia.annotations._

import org.bson.types.ObjectId
import java.util.{List => JList, ArrayList => JArrayList}
import scala.collection.JavaConversions._
import scala.collection.JavaConverters.seqAsJavaListConverter

/*
	More: 
		http://mongodb.github.io/casbah/tutorial.html
		http://www.scala-sbt.org/0.13/tutorial/Running.html
*/


@Entity("temp_people_morhpia")	
class Person {  

	@Id
  var id: ObjectId = _

	var name: String = _
	
	var age: Int = _

	var favNums: JList[Int] = _

	override def toString = s"id=$id, name=$name, age=$age, favNums=$favNums"
}

object MongoWithMorphiaApp {
  def main(args: Array[String]): Unit = {
		// usingMongo()
		usingMorphia()
	}

	def usingMongo(): Unit = {
		val mongoConn = MongoConnection()
		val sampleColl = mongoConn("test")("temp_people")
		sampleColl.drop()
		sampleColl.insert(MongoDBObject("name" -> "Michal", "age" -> 128))
		sampleColl.insert(MongoDBObject("name" -> "Rafal"))
		val samplePerson = sampleColl.findOne("age" $exists true)
		println(samplePerson)
	}

  def usingMorphia(): Unit = {
  	val mongo = new Mongo()

		val mongoDs = new Morphia().createDatastore(mongo, "test")

		val p1 = new Person
		p1.name = "name-1"
		p1.age = 128
		p1.favNums = List(1, 2, 4).asJava

		val p2 = new Person
		p2.name = "name-2"
		p2.age = 256
		p2.favNums = List(8, 16, 32).asJava

		mongoDs.save(p1, WriteConcern.SAFE)
		mongoDs.save(p2, WriteConcern.SAFE)

		val people = mongoDs.createQuery(classOf[Person]).asList

		for (person <- people) {
			println(person)
		}
		
  }
}
