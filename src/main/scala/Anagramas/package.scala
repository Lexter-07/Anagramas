
/**
 * TALLER 4. Uso de colecciones y Expresiones For
 *
 * @author Jorge Luis Castro Scarpetta - 2517065
 * @author Andres Felipe Rodríguez García - 2539555
 */

package object Anagramas {

  type Palabra = String
  type Frase = List[Palabra]
  type Ocurrencias = List[(Char, Int)]

  // Diccionario con el que se tendran en cuenta los anagramas.
  val diccionario: List[Palabra] = List("cosas", "como", "yo", "y", "ocasos", "cayo", "mocosos", "roca", "moco", "sos", "amor",
                                        "carne", "moda", "saco", "cosa", "seco", "coser", "caso", "casa", "coma", "caos",
                                        "peso", "cama", "carro", "cara", "coro", "caro", "rocoso", "sara", "camaron", "palo")

  // genera y ordena las listas de ocurrencias de cada palabra dada
  def lOcPal(p: Palabra): Ocurrencias = {
    p.groupBy(identity)
      .map { case (c, str) => (c, str.length) }
      .toList
      .sortBy(_._1)
  }

  // Lo mismo pero con frases. Varias palabras
  def lOcFrase(f: Frase): Ocurrencias = {
    lOcPal(f.mkString)
  }

  // Las Palabras del diccionario asociadas entre sí
  lazy val diccionarioPorOcurrencias: Map[Ocurrencias, List[Palabra]] = {
    diccionario.groupBy(lOcPal)
  }


  def anagramasDePalabra(palabra: Palabra): List[Palabra] = {
    for {
      lista <- diccionarioPorOcurrencias.get(lOcPal(palabra)).toList
      p <- lista
    } yield p
  }


  def combinaciones(lOcurrencias: Ocurrencias): List[Ocurrencias] = lOcurrencias match {
    case Nil => List(Nil)
    case (char, maxCount) :: tail =>
      for {
        combTail <- combinaciones(tail)
        count <- 0 to maxCount
      } yield if (count == 0) combTail else (char, count) :: combTail
  }


  def complemento(lOc: Ocurrencias, slOc: Ocurrencias): Ocurrencias = {
    import scala.annotation.tailrec

    @tailrec
    def restar(restantes: Ocurrencias, aRestar: Map[Char, Int], acc: Ocurrencias): Ocurrencias = restantes match {
      case Nil => acc.reverse
      case (c, count) :: tail =>
        val cantidadARestar = aRestar.getOrElse(c, 0)
        val nuevoCount = count - cantidadARestar
        if (nuevoCount > 0) restar(tail, aRestar, (c, nuevoCount) :: acc)
        else restar(tail, aRestar, acc)
    }

    restar(lOc, slOc.toMap, Nil)
  }


  def anagramasDeFrase(frase: Frase): List[Frase] = {
    def anagramasAux(ocurrencias: Ocurrencias): List[Frase] = {
      if (ocurrencias.isEmpty) List(Nil)
      else {
        for {
          sublista <- combinaciones(ocurrencias)
          if sublista.nonEmpty // Evitar ciclos infinitos con la sublista vacía
          palabra <- diccionarioPorOcurrencias.getOrElse(sublista, Nil)
          restoFrase <- anagramasAux(complemento(ocurrencias, sublista))
        } yield palabra :: restoFrase
      }
    }

    anagramasAux(lOcFrase(frase))
  }
}