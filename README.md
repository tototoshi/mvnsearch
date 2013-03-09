# mvnsearch

Command line utility for searching Java/Scala library with [Sonatype's API](http://repository.sonatype.org/service/local/data_index?q=maven)

## Install

via [conscript](https://github.com/n8han/conscript/)

```
 $ cs tototoshi/mvnsearch
```

## Usage

```
Usage: mvnsearch <word>

  <word>
        search word
```

## Example

```
$ mvnsearch nscala-time
"com.github.nscala-time" % "nscala-time_2.10" % "0.2.0"
"com.github.nscala-time" % "nscala-time_2.9.2" % "0.2.0"
"com.github.nscala-time" % "nscala-time_2.9.1" % "0.2.0"
"com.github.nscala-time" % "nscala-time_2.10.0" % "0.2.0"
```

