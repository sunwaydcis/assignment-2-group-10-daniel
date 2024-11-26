import java.time.LocalDate
import scala.io.Source
import scala.collection.mutable.ListBuffer
//Created by:
//  DANIELFARID FEARN HOLDEN 22047880
//  Current commit: 9
//
//Project Comments:
//  Question 1 finished. Now time for question 2.
//
//  Updated To-Do:
//    --Create outline for how to solve the 3 problems--
//    1st step: Figure out how to read data from hospital.csv file --DONE
//      let us test using the resource provided in the assignment document:
//        https://alvinalexander.com/scala/how-to-open-read-text-files-in-scala-cookbook-examples/
//      which describes how to read data from a text/data file.
//
//    2nd Step: Access data to solve the 3 questions.
//
//Tasks: Complete question 2


case class Record(
                   date: LocalDate,
                   state: String,
                   beds: Int,
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
end Record

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

def question1(data: ListBuffer[Record]): Unit = //task: Find the hospital with highest all-time bed count and the highest count for most recent date.
  //first need to find max bed value for all records and the state and date, thus use maxBy method on collection.
  //  reference: https://www.scala-lang.org/api/3.5.2/scala/collection/IterableOnceOps.html
  val allTimeMax_Record = data.maxBy(_.beds) //find the max beds value for ALL records

  //now need to find max bed value for latest record only.
  val latestMax_Record = data.maxBy(record => (record.date, record.beds)) //find record first with max date, and then with max beds

  //final print
  println(s"Question 1: ${latestMax_Record.state} had highest no. of beds (${latestMax_Record.beds}) on ${latestMax_Record.date}. ${allTimeMax_Record.state} had highest (${allTimeMax_Record.beds}) bed no. of all time")
end question1

def question2(data: ListBuffer[Record]): Unit = //task: Find ratio between total number of covid beds to total number of beds for entire dataset
  //calculate ratio
  var totalCovidBeds = 0 //init a totalVar for beds_covid
  var totalBeds = 0 //init a totalVar for beds
  for (record <- data) { //for loop iterates over each record in dataset to allow for totalVars to update.
    totalCovidBeds += record.beds_covid
    totalBeds += record.beds
  }
  val ratio: Double = totalCovidBeds.toDouble / totalBeds //final ratio value calculated as double
  println(f"Question 2: Ratio of covid beds to total beds for dataset is $totalCovidBeds to $totalBeds or ~ $ratio%.2f") // final print. Formatting of ratio value to be rounded to 2dp is from chatGPT.
end question2

def question3(data:ListBuffer[Record]): Unit =
  println("Question 3: ")
end question3

@main def myfirstscala(): Unit =
  val records: ListBuffer[Record] = initData() //initialize data.
  question1(records) // question 1 working.
  question2(records) // question 2 working.
