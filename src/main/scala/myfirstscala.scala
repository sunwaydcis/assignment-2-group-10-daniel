import java.time.LocalDate
import scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.collection.mutable


//Created by:
//  DANIELFARID FEARN HOLDEN 22047880
//  Current commit: 11
//
//Project Comments:
//  All questions finished, final clean-up and referencing.
//
//  Completed To-Do:
//    --Create outline for how to solve the 3 problems--
//    1st step: Figure out how to read data from hospital.csv file --DONE
//      let us test using the resource provided in the assignment document:
//        https://alvinalexander.com/scala/how-to-open-read-text-files-in-scala-cookbook-examples/
//      which describes how to read data from a text/data file.
//    2nd Step: Access data to solve the 3 questions.

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

def initData(): ListBuffer[Record] = //function called in Main to parse and return the data for storage.
  val dataFilePath = "./data/hospital.csv"
  val data = Source.fromFile(dataFilePath)
  val records_listBuffer = ListBuffer.empty[Record] // ListBuffer used to store all Record instances efficiently as listBuffer appends with constant time. Reference: https://docs.scala-lang.org/overviews/collections-2.13/performance-characteristics.html
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
  val allTimeMax_Record = data.maxBy(_.beds) //finds the max beds value for ALL records

  //now need to find max bed value for latest record only.
  val latestMax_Record = data.maxBy(record => (record.date, record.beds)) //find record first with max date, and then with max beds

  //final print
  println(s"Question 1: ${latestMax_Record.state} had highest no. of beds (${latestMax_Record.beds}) on ${latestMax_Record.date}. ${allTimeMax_Record.state} had highest (${allTimeMax_Record.beds}) bed no. of all time")
end question1

def question2(data: ListBuffer[Record]): Unit = //task: Find ratio between total number of covid beds to total number of beds for entire dataset
  var totalCovidBeds = 0 //init a totalVar for beds_covid
  var totalBeds = 0 //init a totalVar for beds

  for (record <- data) { //for loop iterates over each record in dataset to allow for totalVars to update.
    totalCovidBeds += record.beds_covid
    totalBeds += record.beds
  }

  val ratio: Double = totalCovidBeds.toDouble / totalBeds //final ratio value calculated as double.
  println(f"Question 2: Ratio of covid beds to total beds for dataset is $totalCovidBeds to $totalBeds or ~ $ratio%.2f") // final print. Formatting of ratio value to be rounded to 2dp is from chatGPT.
end question2

def question3(data:ListBuffer[Record]): Unit = //Task: Find the average number of total patients admitted per day per state for each category.
                                               //      This implies that both covid and pui admittance count indistinctly as one admittance.
                                               //      Reference: https://www.scala-lang.org/api/3.5.2/scala/collection/mutable.html#

  val stateTotals: mutable.Map[String, Int] = mutable.Map.empty// Use mutable Map to create a collection with locations as keys.
  val dates: mutable.TreeSet[LocalDate] = mutable.TreeSet.empty// Use mutable TreeSet to collect dates as it is the most efficient for all operations.

  for (record <- data) {// Init for loop to iterate over records data.
    stateTotals(record.state) = stateTotals.getOrElse(record.state, 0) + record.admitted_total
    //above statement uses method .getOrElse as Map inherits from MapOps
    //.getOrElse works by taking record.state as first param and looks through Map to check if it's already associated with existing key-value pair.
    //the other param in .getOrElse gets returned as the value should there be no existing key-value pair
    //otherwise, .getOrElse returns value from Map(record.state), and so we can + record.admitted_total to value to update the Map(record.state)

    dates.add(record.date) //add the date of the record to the dates set if not present already.
  }

  //convert totals in key-value pairs in stateTotals to become averages
  //to do this, we divide by the number of days
  for (key <- stateTotals.keys) {
    stateTotals(key) = stateTotals.apply(key) / dates.count(x => x == x) //lambda function designed to always return true since we simply want the count.
  } //loop accesses each key (state) and updates its association to the average admitted per day, calculated by taking total value of admitted divided by num of days.

  //final prints
  println(s"Question 3: Over ${dates.count(x => x ==x)} days average admittance_total for each state is listed as follows:")
  for (key <- stateTotals.keys) {
    println(s"    $key: ${stateTotals.apply(key)}")
  }

end question3

@main def myfirstscala(): Unit =
  val records: ListBuffer[Record] = initData() //initialize data.
  question1(records) // question 1 working.
  question2(records) // question 2 working.
  question3(records) // question 3 working.
