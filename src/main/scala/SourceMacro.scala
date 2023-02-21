object SourceMacro {
  import scala.quoted.*

  inline def getContent[A]: String = ${getContentImpl[A]}

  def getContentImpl[A: Type](using Quotes): Expr[String] =
    import quotes.reflect.*
      // for the source specifically of A
    // val str = TypeRepr.of[A].typeSymbol.tree.pos.sourceCode.getOrElse(
    //   report.errorAndAbort("no source code")
    // )

      // for the whole source file containing the definition of A
    val str = TypeRepr.of[A].typeSymbol.pos.getOrElse(
      report.errorAndAbort("no symbol position")
    ).sourceFile.content.getOrElse(
      report.errorAndAbort("no source-file content")
    )

    Expr(str)
}
