# mvnsearch

CLI tool to find Scala libraries

## Install

It requires Cousier

```
$ cs bootstrap com.github.tototoshi::mvnsearch:latest.stable -o /path/to/mvnsearch
```

## Usage

```
Usage: mvnsearch [options] <word>

  -v, --version   Print the version
  -h, --help      Print a help
  --rows <value>  Specify the number of rows
  <word>          Specify search words
```

## Example

```
$ mvnsearch scala-csv
"com.nrinaudo" %% "scala-csv" % "0.1.3" (for Scala 2.11)
"com.github.tototoshi" %% "scala-csv" % "1.3.7" (for Scala 3.0.0-RC1)
"com.github.tototoshi" %% "scala-csv" % "1.3.7" (for Scala 2.13)
"com.github.tototoshi" %% "scala-csv" % "1.3.7" (for Scala 2.12)
"com.github.tototoshi" %% "scala-csv" % "1.3.7" (for Scala 2.11)
"com.github.tototoshi" %% "scala-csv" % "1.3.7" (for Scala 2.10)
"fi.pelam" %% "pelam-scala-csv" % "1.3.0" (for Scala 2.12)
"fi.pelam" %% "pelam-scala-csv" % "1.3.0" (for Scala 2.11)
"fi.pelam" % "pelam-scala-csv" % "1.1.0"
"com.github.tototoshi" %% "scala-csv" % "1.0.0" (for Scala 2.9.3)
"com.github.tototoshi" %% "scala-csv" % "1.0.0" (for Scala 2.9.2)
"com.github.tototoshi" %% "scala-csv" % "1.0.0" (for Scala 2.9.1)
```
