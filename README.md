# mvnsearch

Searching Java/Scala with [Sonatype's API](http://repository.sonatype.org/service/local/data_index?q=maven)

# Install

via [conscript](https://github.com/n8han/conscript/)

```
 $ cs tototoshi/mvnsearch
```

## Usage

```
Usage: mvnsearch [options] <word>

  -s <value> | --scala-version <value>
        scala version
  -v <value> | --version <value>
        version
  <word>
        search word
```

## Example

```
 $ mvnsearch -s 2.9.1 scalaz-core
"org.specs2" % "specs2-scalaz-core_2.9.1" % "6.0.1"
"org.scalaz" % "scalaz-core_2.9.1" % "6.0.4-SNAPSHOT"
"com.stackmob" % "scalamachine-scalaz-core_2.9.1" % "7.0-SNAPSHOT"
"org.scalaz" % "scalaz-core_2.9.1" % "6.0.3"
"com.github.jrwest" % "scalamachine-scalaz-core_2.9.1" % "7.0-SNAPSHOT"
"org.scalaz" % "scalaz-core_2.9.1" % "7.0-SNAPSHOT"
"org.scalaz" % "scalaz-core_2.9.1" % "6.0.3-SNAPSHOT"
"org.specs2" % "scalaz-core_2.9.1" % "6.0.1"
"org.scalaz" % "scalaz-core_2.9.1" % "6.0.4"
"org.scalaz" % "scalaz-core_2.9.1" % "6.0.2"
```

