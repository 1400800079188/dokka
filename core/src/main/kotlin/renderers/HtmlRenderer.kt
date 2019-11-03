package org.jetbrains.dokka.renderers

import org.jetbrains.dokka.pages.ContentLink
import org.jetbrains.dokka.pages.ContentNode
import org.jetbrains.dokka.pages.PageNode
import org.jetbrains.dokka.resolvers.LocationProvider

open class HtmlRenderer(fileWriter: FileWriter, locationProvider: LocationProvider): DefaultRenderer(fileWriter, locationProvider) {

    override fun buildComment(parts: List<ContentNode>, pageContext: PageNode): String = "<p>${super.buildComment(parts, pageContext)}</p>"

    override fun buildSymbol(parts: List<ContentNode>, pageContext: PageNode): String = "<code>${super.buildSymbol(parts, pageContext)}</code>"

    override fun buildHeader(level: Int, text: String): String = "<h$level>$text</h$level>\n"

    override fun buildNewLine(): String = "<br/>"

    override fun buildLink(text: String, address: String): String = "<a href=\"$address\">$text</a>"

    override fun buildCode(code: String): String = "<code>$code</code>"

    override fun buildNavigation(): String = "" // TODO implement

    override fun buildGroup(children: List<ContentNode>, pageContext: PageNode): String =
             children.find { it is ContentLink }?.build(pageContext) + "</td>\n" +
            "<td>" + children.filterNot { it is ContentLink }.joinToString("\n") { it.build(pageContext) }

    override fun buildBlock(name: String, content: List<ContentNode>, pageContext: PageNode): String =
        buildHeader(3, name) + "<table>\n<tr>\n<td>\n" + content.joinToString("</td>\n</tr>\n<tr>\n<td>") { it.build(pageContext) } + "</td></tr>\n</table>"

    override fun renderPage(page: PageNode) {
        val pageText = buildStartHtml(page) + buildPageContent(page) + buildEndHtml()
        fileWriter.write(locationProvider.resolve(page), pageText, "")
    }

    protected open fun buildStartHtml(page: PageNode) = """<!DOCTYPE html>
        |<html>
        |<head>
        |<title>${page.name}</title>
        |</head>
        |<body>
        |""".trimMargin()

    protected open fun buildEndHtml() =
        """
        |
        |</body>
        |</html>
    """.trimMargin()
}