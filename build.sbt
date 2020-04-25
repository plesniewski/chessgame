

lazy val root  = (project in file(".")).settings(
  name := "chessgame",
    version := "0.1",
  scalaVersion := "2.13.1"
)

mainClass in (Compile, run):= Some("com.whitehatgaming.chessgame.ChessGame")
mainClass in (Compile, assembly):= Some("com.whitehatgaming.chessgame.ChessGame")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.1" % "test"
)