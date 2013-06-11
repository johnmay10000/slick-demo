import sbt._

import Keys._
import org.scalasbt.androidplugin._
import org.scalasbt.androidplugin.AndroidKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq (
    scalaVersion := "2.10.1",
    name := "slick-demo",
    version := "0.1",
    versionCode := 0,
    platformName in Android := "android-17",
    buildToolsVersion in Android := "17.0.0",
    scalacOptions ++= Seq("-deprecation", "-unchecked"),
    javacOptions ++= Seq("-encoding", "UTF-8", "-source", "1.6", "-target", "1.6")
  )

  val proguardSettings = Seq (
    useProguard in Android := true,
    proguardOption in Android :=
      """
        |
        |
        |-keep public class scala.Function1
        |-keep public class scala.reflect.ScalaSignature
        |
        |
        |-keepclassmembers enum * {
        | public static **[] values();
        | public static ** valueOf(java.lang.String);
        |}
        |
        |-keepclassmembers class scala.collection.** {
        |    *;
        |}
      """.stripMargin
  )

  val sharedResolvers = Seq(
    Resolver.sonatypeRepo("snapshots"),
    Resolver.sonatypeRepo("releases")
  )

  lazy val fullAndroidSettings =
    settings ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++
    proguardSettings ++
    AndroidManifestGenerator.settings ++
    AndroidMarketPublish.settings ++
    Seq(
      keyalias in Android := "change-me",
      resolvers ++= sharedResolvers,
      libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "1.9.1" % "test",
                                  "com.typesafe.slick" %% "slick" % "1.0.1",
                                  "org.slf4j" % "slf4j-nop" % "1.6.4",
                                  "com.h2database" % "h2" % "1.3.166")
    )
}

object AndroidBuild extends Build {
  lazy val main = Project (
    "slick-demo",
    file("."),
    settings = General.fullAndroidSettings
  )

  lazy val tests = Project (
    "tests",
    file("tests"),
    settings = General.settings ++
               AndroidTest.androidSettings ++
               General.proguardSettings ++ Seq (
      name := "slick-demoTests"
    )
  ) dependsOn main
}
