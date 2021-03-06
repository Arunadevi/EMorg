package models

import scala.collection.JavaConversions._
import StaticResources._

case class MethodSection(id: String, title: String, summary: String) extends Navigable

object MethodSection {
  private val methodSections = LazyCache {
    scan("method/__Sections.txt", scanner => {
      scanner.useDelimiter(sys.props("line.separator").concat(sys.props("line.separator"))).toList.map(line => {
        val split: Array[String] = line.split('\n')
        new MethodSection(split(0).trim(), split(1), split(2))
      })
    })
  }

  def all(): List[MethodSection] = {
    methodSections
  }

  def get(id: String): MethodSection = {
    methodSections.filter(_.id == id).head
  }
}
