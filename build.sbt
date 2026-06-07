val common = Def.settings(
  scalaVersion := "3.8.4",
)

ThisBuild / usePipelining := {
  val value = sys.props.get("pipelining") == Some("true")
  println(s"pipelining = ${value}")
  value
}

common

val a1 = project
  .settings(
    common
  )

val a2 = project
  .settings(
    common
  )
  .dependsOn(a1)
