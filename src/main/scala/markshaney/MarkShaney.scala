package markshaney

import scala.io.Source
import scala.util.Random


object MarkShaney {

  def main(args: Array[String]): Unit = {
    val text1 = readInput("mark-shaney/input-string.txt") // Sherlock Holmes
    val text2 = readInput("mark-shaney/input-string-test.txt") // Wikipedia Physics topics
    val text3 = readInput("mark-shaney/pride-and-prejudice.txt") // Pride and Prejudice
    val text = text2 ++ text3
    val cleaned = cleanInput(text)
    //    println(cleaned)

    val wordMap = wordsToWordMap(cleaned)
    println(wordMap)
    println()
    var i = 0
    val randomWord = cleaned(Random.nextInt(cleaned.length))
    var word = generateNextWord(randomWord, wordMap)
    while (i < 150) {
      word = generateNextWord(word, wordMap)
      print(word + " ")
      i += 1
    }
  }

  def readInput(filePath: String): Seq[String] = {
    val lines = Source.fromResource(filePath).getLines().filter(_.nonEmpty)
    lines.toList.flatMap(_.split(" ").toList)
  }

  def cleanInput(input: Seq[String]): Seq[String] = {
    input.map(word => {
      if (word.startsWith("“")) {
        word.substring(1, word.length)
      }
      else if (word.endsWith("”")) {
        // || word.endsWith(".")
        // Note: "!?.,".contains(word.substring(word.length - 1, word.length)) - Remove end of sentence punctuation
        word.substring(0, word.length - 1)
      }
      else if (word.startsWith("_") && word.endsWith("_")) {
        word.substring(1, word.length - 1)
      }
      else {
        word
      }
    })
  }

  def wordsToWordMap(words: Seq[String]): Map[String, List[String]] = {
    val pairs = words.sliding(2, 1).map(x => x(0) -> x(1)).toList
    pairs.groupBy(_._1).map { case (k, v) => (k, v.map(_._2)) }
  }


  def generateNextWord(inputWord: String, wordMap: Map[String, List[String]]): String = {
    val possibleWords = wordMap(inputWord)
    val randomIndex = Random.nextInt(possibleWords.length)
    possibleWords(randomIndex)
  }

}
