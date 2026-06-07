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
    common,
    Compile / sourceGenerators += task {
      (1 to 1000).map { n =>
        val f = (Compile / sourceManaged).value / s"X${n}.scala"
        IO.write(f, s"class X${n} { def x = ${n} } ")
        f
      }
    }
  )

val a2 = project
  .settings(
    common
  )
  .dependsOn(a1)
