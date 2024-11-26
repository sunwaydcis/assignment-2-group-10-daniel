import java.time.LocalDate
import scala.io.Source
import scala.collection.mutable.ListBuffer
//Created by:
//  DANIELFARID FEARN HOLDEN 22047880
//  Current commit: 7
//
//Project Comments:
//  Finally figured out correct Date data type to use, and so we can test to see whether the data is being stored correctly into records_listBuffer
//
//  Updated To-Do:
//    --Create outline for how to solve the 3 problems--
//    1st step: Figure out how to read data from hospital.csv file
//      let us test using the resource provided in the assignment document:
//        https://alvinalexander.com/scala/how-to-open-read-text-files-in-scala-cookbook-examples/
//      which describes how to read data from a text/data file.
//
//    2nd Step: Access data to solve the 3 questions.
//
//Tasks: Use records_listBuffer collection to do calculations for the answer to 3 questions.
//       Organise code a bit more to make more readable.


case class Record(
             date: LocalDate,
             state: String,
             Beds: Int,
             beds_covid: Int,
             beds_nonCrit: Int,
             admitted_pui: Int,
             admitted_covid: Int,
             admitted_total: Int,
             discharged_pui: Int,
             discharged_covid: Int,
             discharged_total: Int,
             hosp_covid: Int,
             hosp_pui: Int,
             hosp_nonCovid: Int
            )

def initData(): ListBuffer[Record] = //function called in Main to parse and return the data.
  val dataFilePath = "./data/hospital.csv"
  val data = Source.fromFile(dataFilePath)
  val records_listBuffer = ListBuffer.empty[Record] // ListBuffer used to store all Record instances efficiently.
  var counter = 0
  for (line <- data.getLines().drop(1)) { //read each line from hospital.csv file excluding header
    val line_data = line.split(",")
    records_listBuffer.append(Record(
      LocalDate.parse(line_data(0)),
      line_data(1),
      line_data(2).toInt,
      line_data(3).toInt,
      line_data(4).toInt,
      line_data(5).toInt,
      line_data(6).toInt,
      line_data(7).toInt,
      line_data(8).toInt,
      line_data(9).toInt,
      line_data(10).toInt,
      line_data(11).toInt,
      line_data(12).toInt,
      line_data(13).toInt)
    )
  }
  data.close()
  records_listBuffer
end initData

def question1(): Unit =
  val something = "Something"

@main def myfirstscala(): Unit =
  val records: ListBuffer[Record] = initData() //initialize data.
  //run a quick check to make sure that previous typos, errors and adjustments were successful
  println(s"${records.head.state}") //outputs expected result, adjustments were successful.
