resolvers += "github repo" at "http://mpeltonen.github.com/maven"

resolvers += Resolver.url("scalasbt releases", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns)

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"


addSbtPlugin("org.scala-sbt" % "sbt-android-plugin" % "0.6.3-20130429-SNAPSHOT" withSources())

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.0-SNAPSHOT")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.3")
