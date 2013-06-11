package com.oomphhq.slick.demo

import _root_.android.app.Activity
import _root_.android.os.Bundle

//import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.meta.MTable

import scala.slick.driver.SQLiteDriver.simple._
import android.util.Log

// Use the implicit threadLocalSession
import Database.threadLocalSession


class MainActivity extends Activity with TypedActivity {
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)

    findView(TR.textview).setText("hello, world!")
//    Class.forName("org.h2.Driver")

    Class.forName("org.sqldroid.SQLDroidDriver")
    deleteDatabase("/data/data/com.oomphhq.slick.demo/mock.sqlite")

    //    val url = "jdbc:sqldroid:" + "/data/data/com.oomphhq.slick.demo" + "/issues.sqlite"
//    val con = new org.sqldroid.SQLDroidDriver().connect(url , new Properties())

//    object Issues extends Table[(String, String, String)]("ISSUES") {
//      def name = column[String]("COF_NAME", O.PrimaryKey)
//      def supID = column[Int]("SUP_ID")
//      def price = column[Double]("PRICE")
//      def * = name ~ supID ~ price
//    }


    // Definition of the SUPPLIERS table
    object Suppliers extends Table[(Int, String, String, String, String, String)]("SUPPLIERS") {
      def id = column[Int]("SUP_ID", O.PrimaryKey) // This is the primary key column
      def name = column[String]("SUP_NAME")
      def street = column[String]("STREET")
      def city = column[String]("CITY")
      def state = column[String]("STATE")
      def zip = column[String]("ZIP")
      // Every table needs a * projection with the same type as the table's type parameter
      def * = id ~ name ~ street ~ city ~ state ~ zip
    }


    object Coffees extends Table[(String, Int, Double, Int, Int)]("COFFEES") {
      def name = column[String]("COF_NAME", O.PrimaryKey)
      def supID = column[Int]("SUP_ID")
      def price = column[Double]("PRICE")
      def sales = column[Int]("SALES")
      def total = column[Int]("TOTAL")
      def * = name ~ supID ~ price ~ sales ~ total
      // A reified foreign key relation that can be navigated to create a join
      def supplier = foreignKey("SUP_FK", supID, Suppliers)(_.id)
    }

//    + "/data/data/com.oomphhq.slick.demo" + "/coffee.sqlite",

//    Database.forURL("jdbc:h2:/data/data/com.oomphhq.slick.demo/coffee.sqlite",  driver = "org.h2.Driver") withSession {
      Database.forURL("jdbc:sqldroid:/data/data/com.oomphhq.slick.demo/mock.sqlite",  driver = "org.sqldroid.SQLDroidDriver") withSession {
//    Database.forURL("jdbc:h2:mem:test1",  driver = "org.h2.Driver") withSession {
      // The session is never named explicitly. It is bound to the current
      // thread as the threadLocalSession that we imported

    // Create the tables, including primary and foreign keys


      val tableSupps = MTable.getTables("SUPPLIERS").list()
      println(tableSupps)
      if (!tableSupps.exists(_.name.name == Suppliers.tableName)) {
        Suppliers.ddl.create
      }

      val tableCoff = MTable.getTables("COFFEES").list()
      if (!tableCoff.exists(_.name.name == Coffees.tableName)) {
        Coffees.ddl.create
      }

//      (Suppliers.ddl ++ Coffees.ddl).create

    // Insert some suppliers
    Suppliers.insert(101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199")
    Suppliers.insert( 49, "Superior Coffee", "1 Party Place",    "Mendocino",    "CA", "95460")
    Suppliers.insert(150, "The High Ground", "100 Coffee Lane",  "Meadows",      "CA", "93966")
//
//    // Insert some coffees (using JDBC's batch insert feature, if supported by the DB)
    Coffees.insertAll(
      ("Colombian",         101, 7.99, 0, 0),
      ("French_Roast",       49, 8.99, 0, 0),
      ("Espresso",          150, 9.99, 0, 0),
      ("Colombian_Decaf",   101, 8.99, 0, 0),
      ("French_Roast_Decaf", 49, 9.99, 0, 0)
    )

    // Iterate through all coffees and output them
//    println("Coffees:")
      Log.i("SLICK!","Coffees:")
      Query(Coffees) foreach {
        case (name, supID, price, sales, total) => {
          Log.i("SLICK!", " " + name + "\t" + supID + "\t" + price + "\t" + sales + "\t" + total)
        }
      }

      val q2 = for {
        c <- Coffees if c.price < 9.0
        s <- c.supplier
      } yield (c.name, s.name)

      Log.i("SLICK!", q2.list.toString())

      val q3 = (Coffees).map {
        case c => c.name.count
      }

      println(q3.firstOption.getOrElse(0))


      val q = Query(Coffees)
      val q4 = q.filter(_.price > 8.0).map(_.name)
      println(q4.list)
    }
  }
}
