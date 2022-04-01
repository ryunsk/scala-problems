import scala.util.matching.Regex

val index = "testname_20210713-1111"
val myPattern2: Regex = "^[0-9]{8}$".r

index.indexOf("_2021")
index.indexOf(myPattern2)

index.take(index.indexOf("_2021"))


val pattern = "(.*)_([0-9]{8})-(.*)".r
val pattern(name, number, four) = "hell_Test_number_hello_12345678-1234"

val myString = "anything_12345678-anythingTwo"
val myPattern = "(.*)_([0-9]{8})-(.*)".r
myString match {
  case myPattern(firstPart, secondPart, thirdPart) => println("There is a match!" + firstPart, secondPart, thirdPart)
  case _ => println("Not in the correct format")
}