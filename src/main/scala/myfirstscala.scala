import java.time.LocalDate
import scala.io.Source
import scala.collection.mutable.ListBuffer
//Created by:
//  DANIELFARID FEARN HOLDEN 22047880
//  Current commit: 6
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
//    As described above, Date can now be stored so let's run tests to see whether .csv is being parsed into records_listBuffer correctly
//
//Task: Store .csv data into records_listBuffer. Check that it is done properly with a simple test of a counter and property assessor.
//Also, update Record to become case class as all properties are values and thus Records will be stored as distinct values.


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

@main def myfirstscala(): Unit =
  val dataFilePath = "./data/hospital.csv"
  val data = Source.fromFile(dataFilePath)
  val records_listBuffer = ListBuffer.empty[Record] // listBuffer used to store all Record instances efficiently.
  var counter = 0
  for (line <- data.drop(1)) { //read each line from hospital.csv file
    val line_data = line.split(",")
    val date_format = "yyyy-MM-dd"
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
    counter += 1
  }
  data.close()


//by manually opening and scrolling to the bottom of the hospital.csv file, we can see that there are 26031 rows of record data.
//so that means if we print the counter, we should see 26031
  println(counter) //output is 26031

//let us also check that the data is in the correct format by checking the first few data of the first record in records_listBuffer
  println(s"${records_listBuffer.head.date}\n${records_listBuffer.head.state}\n${records_listBuffer.head.Beds}\n${records_listBuffer.head.beds_covid}\n${records_listBuffer.head.beds_nonCrit}")
  // output is correct with what is expected, thus we can begin accessing these data to manipulate them to get the answers.