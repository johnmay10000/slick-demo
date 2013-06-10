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
        |-verbose
        |-dontusemixedcaseclassnames
        |-dontskipnonpubliclibraryclasses
        |-dontoptimize
        |-dontpreverify
        |-keep public class scala.Function1
        |-keep public class scala.reflect.ScalaSignature
        |-keep public class scala.collection.Seq
        |-keep public class scala.collection.Iterable
        |-keep public class scala.slick.ast.TableNode
        |-keep public class scala.runtime.VolatileObjectRef
        |-keep public class scala.slick.ast.Node
        |-keep public class scala.collection.IndexedSeq
        |-keep public class scala.Function2
        |-keep public class scala.collection.immutable.List
        |-keep public class scala.slick.lifted.TypeMapper
        |-keep public class scala.slick.driver.BasicDriver
        |-keep public class scala.slick.lifted.Shape
        |-keep public class scala.Option
        |-keep public class scala.slick.lifted.ForeignKeyAction
        |-keep public class scala.slick.session.PositionedResult
        |-keep public class scala.slick.driver.BasicProfile
        |-keep public class scala.slick.session.PositionedParameters
        |-keep public class scala.collection.immutable.Nil$
        |-keep public class scala.concurrent.forkjoin.LinkedTransferQueue
        |-keep public class scala.collection.SeqLike {*;}
        |-keep public class scala.collection.mutable.SeqLike {*;}
        |-keep public class scala.collection.Seq { scala.Int hashCode; }
        |-keep public class scala.Function0
        |-keep public class scala.collection.GenSeq
        |-keep public class scala.concurrent.forkjoin.LinkedTransferQueue$Node { java.lang.Thread waiter; }
        |-keep public class scala.Function0
        |-keep public class scala.Function1
        |-keep public class scala.Function2
        |-keep public class scala.Function3
        |-keep public class scala.Function4
        |-keep public class scala.Function6
        |-keep public class scala.Tuple2
        |-keep public class scala.Tuple3
        |-keep public class scala.Tuple4
        |-keep public class scala.Tuple5
        |-keep public class scala.actors.AbstractActor
        |-keep public class scala.actors.MQueue
        |-keep public class scala.actors.OutputChannel
        |-keep public class scala.collection.immutable.List
        |-keep public class scala.Enumeration$Value
        |-keep public class scala.Option
        |-keep public class scala.PartialFunction
        |-keep public class scala.ref.WeakReference
        |-keep public class scala.collection.immutable.StringLike
        |-keep public class scala.actors.IScheduler
        |-keep public class scala.actors.Future
        |-keep public class org.slf4j.Marker
        |-keep public class scala.collection.generic.CanBuildFrom
        |-keep public class scala.collection.Iterable
        |-keep public class scala.collection.Map
        |-keep public class scala.collection.immutable.IndexedSeq
        |-keep public class scala.collection.immutable.Map
        |-keep public class scala.collection.immutable.HashSet
        |-keep public class scala.collection.immutable.HashMap
        |-keep public class scala.collection.immutable.LongMap
        |-keep public class scala.collection.mutable.Buffer
        |-keep public class scala.collection.mutable.HashEntry
        |-keep public class scala.collection.mutable.HashMap
        |-keep public class scala.collection.mutable.HashSet
        |-keep public class scala.collection.mutable.LinkedHashSet
        |-keep public class scala.collection.mutable.Publisher
        |-keep public class scala.collection.mutable.Set
        |-keep public class scala.collection.mutable.StringBuilder
        |-keep public class scala.collection.mutable.Subscriber
        |-keep public class scala.collection.script.Message
        |-keep public class scala.collection.Seq
        |-keep public class scala.collection.Set
        |-keep public class scala.collection.Traversable
        |-keep public class scala.collection.TraversableLike
        |-keep public class scala.collection.TraversableOnce
        |-keep public class scala.concurrent.Lock
        |-keep public class scala.math.Numeric
        |-keep public class scala.math.Ordering
        |-keep public class scala.Predef$$less$colon$less
        |-keep public class scala.reflect.ClassManifest
        |-keep public class scala.reflect.Manifest
        |-keep public class scala.ref.SoftReference
        |-keep public class scala.runtime.BooleanRef
        |-keep public class scala.runtime.BoxedUnit
        |-keep public class scala.runtime.ObjectRef
        |-keep public class scala.runtime.IntRef
        |-keep public class scala.runtime.LongRef
        |-keep public class scala.Some
        |-keep public class scala.Symbol
        |-keep public class scala.util.Random
        |-keep public class scala.xml.Elem
        |-keep public class scala.xml.Node
        |-keep public class scala.runtime.VolatileObjectRef
        |-keep public class scala.collection.mutable.ObservableSet
        |-keep public class * extends android.app.Activity
        |-keep public class * extends android.app.Application
        |-keep public class * extends android.app.Service
        |-keep public class * extends android.content.BroadcastReceiver
        |-keep public class * extends android.content.ContentProvider
        |-keep public class * extends android.app.backup.BackupAgentHelper
        |-keep public class * extends android.preference.Preference
        |-keep public class com.android.vending.licensing.ILicensingService
        |-keep public class scala.slick.**
        |-keep public class org.h2.**
        |-keep public class scala.collection.script.**
        |
        |-dontnote android.app.backup.BackupAgentHelper
        |-dontnote com.android.vending.licensing.ILicensingService
        |
        |-keepclassmembers class * {
        |    static android.os.Parcelable$Creator *;
        |}
        |
        |-keepclassmembers class * {
        |    void clear();
        |}
        |
        |-keepclasseswithmembernames class * {
        |    native <methods>;
        |}
        |
        |-keepclasseswithmembers class * {
        |    public <init>(android.content.Context, android.util.AttributeSet);
        |}
        |
        |-keepclasseswithmembers class * {
        |    public <init>(android.content.Context, android.util.AttributeSet, int);
        |}
        |
        |-keepclassmembers class * extends android.app.Activity {
        |   public void *(android.view.View);
        |}
        |
        |-keepclassmembers enum * {
        |    public static **[] values();
        |    public static ** valueOf(java.lang.String);
        |}
        |
        |-keepclassmembers class * {
        |    ** MODULE$;
        |}
        |
        |-keep class scala.runtime.*MethodCache
        |
        |-keepclassmembernames class scala.runtime.*MethodCache {
        |    <methods>;
        |}
        |
        |-keepclassmembers class scala.collection.** {
        |    *;
        |}
        |
        |-keepattributes SourceFile,LineNumberTable
        |
        |-keepclassmembers class **.R$* {
        |    public static <fields>;
        |}
        |-keepclassmembers class * extends android.app.Activity {
        |    public void *_*(android.view.View);
        |}
        |-keep public class * extends android.view.View {
        |    public <init>(android.content.Context);
        |    public <init>(android.content.Context, android.util.AttributeSet);
        |    public <init>(android.content.Context, android.util.AttributeSet, int);
        |    public void set*(...);
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
                                  "com.typesafe.slick" %% "slick" % "1.0.1" withSources(),
                                  "com.h2database" % "h2" % "1.3.166" withSources())
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
