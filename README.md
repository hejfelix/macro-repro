This reproduces a bug in Scala 3 macros where the macro works once, but then it fails to find a position and file of a type.

to reproduce:

```
sbt> run
```

it prints the following

```scala
[info] -- Info: /Users/dkFePaHa/repos/macro-repro/src/main/scala/Main.scala:2:69 ------
[info] 2 |  def main(args: Array[String]):Unit = println(SourceMacro.getContent[Foo[?]])
[info]   |                                               ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
[info]   |TypeRepr.of[A].typeSymbol.pos: /Users/dkFePaHa/repos/macro-repro/src/main/scala/Foo.scala:<6..6>, sourceFile: /Users/dkFePaHa/repos/macro-repro/src/main/scala/Foo.scala
[info] running Main
class Foo[F[_]] {

}
```

now add a newline anywhere in `src/main/scala/SourceMacro.scala`

and run the same thing:

```bash
sbt> run
```

now it prints

```scala
[info] compiling 1 Scala source to /Users/dkFePaHa/repos/macro-repro/target/scala-3.2.0/classes ...
[info] compiling 1 Scala source to /Users/dkFePaHa/repos/macro-repro/target/scala-3.2.0/classes ...
[info] -- Info: /Users/dkFePaHa/repos/macro-repro/src/main/scala/Main.scala:2:69 ------
[info] 2 |  def main(args: Array[String]):Unit = println(SourceMacro.getContent[Foo[?]])
[info]   |                                               ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
[info]   |                   TypeRepr.of[A].typeSymbol.pos: ?, sourceFile: <no file>
[info] running Main
```

To restore expected behaviour, I can clean and run again

```bash
sbt> clean
sbt> run
```

which prints the same thing as the original run.