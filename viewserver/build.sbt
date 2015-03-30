import android.Keys._
import collection.JavaConversions._

crossPaths := false

autoScalaLibrary := false

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

name := "viewserver"

organization := "com.hanhuy.android"

version := "1.0.2"

dependencyClasspath in (Compile,doc) ++= (builder in Android).value.getBootClasspath map Attributed.blank

javacOptions in (Compile,doc) := Seq("-Xdoclint:none")

platformTarget in Android := "android-19"

debugIncludesTests in Android := false

publishArtifact in (Compile,packageBin) := true

publishArtifact in (Compile,packageSrc) := true

// sonatype publishing options follow
publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomIncludeRepository := { _ => false }

pomExtra :=
  <scm>
    <url>git@github.com:pfn/ViewServer.git</url>
    <connection>scm:git:git@github.com:pfn/ViewServer.git</connection>
  </scm>
  <developers>
    <developer>
      <id>pfnguyen</id>
      <name>Perry Nguyen</name>
      <url>https://github.com/pfn</url>
    </developer>
  </developers>

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

homepage := Some(url("https://github.com/pfn/ViewServer"))
