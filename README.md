This reproduces a bug in Scala 3 macros where the macro works once, but then it fails to find a position and file of a type.

to reproduce:

```
sbt> run
```

it prints the following

```
class Foo[F[_]] {

}
```

now add a newline anywhere in `src/main/scala/SourceMacro.scala`

and run the same thing:

```bash
sbt> run
```

now it prints empty string. To get back the expected behaviour, you can clean and run again:

```bash
sbt> clean
sbt> run
```

which, again, prints:

```
class Foo[F[_]] {

}
```