enablePlugins(BuildInfoPlugin)

name         := "riscvassembler"
organization := "com.github.push-and-pray"
version      := "0.0.1-SNAPSHOT"

scalaVersion       := "3.3.6"
crossScalaVersions := Seq("2.13.16", "3.3.6")

libraryDependencies ++= Seq(
  "com.lihaoyi"   %% "os-lib"    % "0.11.4",
  "org.scalatest" %% "scalatest" % "3.2.19" % "test",
)

// 2. Configure BuildInfo to generate the missing object
// We map the package to "com.carlosedp.riscvassembler" because that is what the code expects.
buildInfoPackage := "com.carlosedp.riscvassembler"
buildInfoKeys    := Seq[BuildInfoKey](
  "appName"    -> name.value,
  "appVersion" -> version.value,
  // Provide defaults for the custom fields the original Mill build provided
  "revision"    -> "unknown",
  "buildCommit" -> "unknown",
  "commitDate"  -> "unknown",
  "buildDate"   -> new java.util.Date().toString,
)

// --- CONFIGURATION FOR NON-STANDARD FOLDER STRUCTURE ---
Compile / unmanagedSourceDirectories += baseDirectory.value / "riscvassembler" / "src"

Test / unmanagedSourceDirectories ++= Seq(
  baseDirectory.value / "riscvassembler" / "test" / "src",
  baseDirectory.value / "riscvassembler" / "test" / "jvm",
)

Compile / unmanagedSourceDirectories := (Compile / unmanagedSourceDirectories).value.filter(_.exists)
Test / unmanagedSourceDirectories    := (Test / unmanagedSourceDirectories).value.filter(_.exists)
