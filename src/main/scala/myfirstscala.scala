import java.time.LocalDate
import scala.io.Source
import scala.collection.mutable.ListBuffer
//Created by:
//  DANIELFARID FEARN HOLDEN 22047880
//  Current commit: 5
//
//Project Comments:
//  Forgot to update comments for previous commit (4)
//  Still facing issues with Date format, however, new Date type can be tested from java.time.LocalDate API
//  Found using https://louiseforbes101.medium.com/dates-and-time-in-scala-bb7590276dfe
//
//  Updated To-Do:
//    --Create outline for how to solve the 3 problems--
//    1st step: Figure out how to read data from hospital.csv file
//      let us test using the resource provided in the assignment document:
//        https://alvinalexander.com/scala/how-to-open-read-text-files-in-scala-cookbook-examples/
//      which describes how to read data from a text/data file.
//
//     1st step almost done, need to store Date. Time to test LocalDate API.
//
//Task: Test how to read data from hospital.csv
//  Since we are just testing to see how the data can be parsed, no emphasis will be placed on efficiency.
//  As described from assignment paper and in the .csv file, the data is in following format, so create a class with related properties.
//    1. Date - Date <-- Figure out how to store this one.
//    2. State - String
//    3. Beds - Int
//    ... the rest of which are Int

class Record(val date: LocalDate,
             val state: String,
             val Beds: Int,
             val beds_covid: Int,
             val beds_nonCrit: Int,
             val admitted_pui: Int,
             val admitted_covid: Int,
             val admitted_total: Int,
             val discharged_pui: Int,
             val discharged_covid: Int,
             val discharged_total: Int,
             val hosp_covid: Int,
             val hosp_pui: Int,
             val hosp_nonCovid: Int
            )
end Record

@main def myfirstscala(): Unit =
  val dateString = "2020-04-24"
  println(LocalDate.parse(dateString))

  val datesArr : Array[LocalDate] = Array(LocalDate.parse("2020-06-24"), LocalDate.parse("2020-04-25"), LocalDate.parse("2020-05-24"))
  println(datesArr.mkString)
  println(datesArr.sorted.mkString) //not only is data in correct format, but we can sort chronogically should we want to, thus, java.time.LocalDate shall be API used.

//  val hospitalData = "./data/hospital.csv"
//  val records_listBuffer = ListBuffer.empty[Record] // listBuffer used to store all Record instances efficiently.
//
//  for (line <- Source.fromFile(hospitalData).getLines.drop(1)) { //read each line from hospital.csv file
//    val line_data = line.split(",")
//    val date_format = "yyyy-MM-dd"
//    records_listBuffer.append(Record(
//      Date(), // too much code to simply parse the string into a Date format with the same information. Must find a different Date type/API to use.
//      line_data(1),
//      line_data(2).toInt,
//      line_data(3).toInt,
//      line_data(4).toInt,
//      line_data(5).toInt,
//      line_data(6).toInt,
//      line_data(7).toInt,
//      line_data(8).toInt,
//      line_data(9).toInt,
//      line_data(10).toInt,
//      line_data(11).toInt,
//      line_data(12).toInt,
//      line_data(13).toInt)
//    )
//  }