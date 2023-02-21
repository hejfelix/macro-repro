object Main:
  def main(args: Array[String]):Unit = println(SourceMacro.getContent[Foo[?]])
end Main