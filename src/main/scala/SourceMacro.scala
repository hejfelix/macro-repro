object SourceMacro {
  import scala.quoted.*


  inline def getContent[A]: String = ${getContentImpl[A]}

  def getContentImpl[A: Type](using Quotes): Expr[String] =
    import quotes.reflect.*
   
    val position = TypeRepr.of[A].typeSymbol.pos.getOrElse(
      report.errorAndAbort("no symbol position")
    )
    val str = position.sourceFile.content.getOrElse(
      report.errorAndAbort("no source-file content")
    )
    report.info(s"TypeRepr.of[A].typeSymbol.pos: ${position}, sourceFile: ${position.sourceFile}")

    Expr(str)
}
