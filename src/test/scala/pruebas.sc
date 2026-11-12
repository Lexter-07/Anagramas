import Anagramas._

lOcPal ( "casa" )
combinaciones ( lOcPal ( "casa" ) )
val palabra = "cosa"
anagramasDePalabra( palabra )
val frase = List( "cosas", "como", "yo" )
combinaciones ( lOcFrase ( frase ) )
anagramasDeFrase ( frase )




// Función auxiliar para validación
def test[A](nombre: String, obtenido: A, esperado: A): Unit = {
  val exito = obtenido == esperado
  println(s"[$nombre] ${if (exito) "ÉXITO" else "FALLO"}")
  assert(exito, s"Esperado: $esperado, Obtenido: $obtenido")
}

// 1. Pruebas lOcPal
test("lOcPal - 1", lOcPal("scala"), List(('a', 2), ('c', 1), ('l', 1), ('s', 1)))
test("lOcPal - 2", lOcPal("hola"), List(('a', 1), ('h', 1), ('l', 1), ('o', 1)))
test("lOcPal - 3", lOcPal("murcielago"), List(('a',1), ('c',1), ('e',1), ('g',1), ('i',1), ('l',1), ('m',1), ('o',1), ('r',1), ('u',1)))
test("lOcPal - 4", lOcPal("zzzz"), List(('z', 4)))
test("lOcPal - 5", lOcPal(""), List())

// 2. Pruebas lOcFrase
test("lOcFrase - 1", lOcFrase(List("hola", "mundo")), List(('a',1), ('d',1), ('h',1), ('l',1), ('m',1), ('n',1), ('o',2), ('u',1)))
test("lOcFrase - 2", lOcFrase(List("yo", "y", "tu")), List(('o',1), ('t',1), ('u',1), ('y',2)))
test("lOcFrase - 3", lOcFrase(List("a", "b", "c")), List(('a',1), ('b',1), ('c',1)))
test("lOcFrase - 4", lOcFrase(List("moco", "sos")), List(('c',1), ('m',1), ('o',3), ('s',2)))
test("lOcFrase - 5", lOcFrase(List()), List())

// 3. Pruebas diccionarioPorOcurrencias
test("diccionarioPorOcurrencias - cayo", diccionarioPorOcurrencias(List(('a',1), ('c',1), ('o',1), ('y',1))), List("cayo"))
test("diccionarioPorOcurrencias - y", diccionarioPorOcurrencias(List(('y', 1))), List("y"))
test("diccionarioPorOcurrencias - roca", diccionarioPorOcurrencias(List(('a',1), ('c',1), ('o',1), ('r',1))), List("roca"))
test("diccionarioPorOcurrencias - como", diccionarioPorOcurrencias(List(('c',1), ('m',1), ('o',2))), List("como"))
test("diccionarioPorOcurrencias - cosas", diccionarioPorOcurrencias(List(('a',1), ('c',1), ('o',1), ('s',2))), List("cosas"))

// 4. Pruebas anagramasDePalabra
test("anagramasDePalabra - cosas", anagramasDePalabra("sacos").toSet, Set("cosas")) // "sacos" tiene las letras de "cosas"
test("anagramasDePalabra - moco", anagramasDePalabra("como").toSet, Set("como"))
test("anagramasDePalabra - sos", anagramasDePalabra("oss").toSet, Set("sos"))
test("anagramasDePalabra - no_existe", anagramasDePalabra("xyz"), List())
test("anagramasDePalabra - vacio", anagramasDePalabra(""), List())

// 5. Pruebas combinaciones
val combTest = combinaciones(List(('a', 1), ('b', 1)))
test("combinaciones - 1 (tamaño)", combTest.size, 4)
test("combinaciones - 2 (contiene vacio)", combTest.contains(List()), true)
test("combinaciones - 3 (contiene a)", combTest.contains(List(('a', 1))), true)
test("combinaciones - 4 (contiene b)", combTest.contains(List(('b', 1))), true)
test("combinaciones - 5 (contiene ab)", combTest.contains(List(('a', 1), ('b', 1))), true)

// 6. Pruebas complemento
test("complemento - 1", complemento(List(('a', 2), ('b', 1)), List(('a', 1))), List(('a', 1), ('b', 1)))
test("complemento - 2", complemento(List(('a', 2), ('b', 1)), List(('a', 2))), List(('b', 1)))
test("complemento - 3", complemento(List(('x', 3)), List(('x', 1))), List(('x', 2)))
test("complemento - 4", complemento(List(('c',1), ('o',4), ('s',2)), List(('o',2), ('s',1))), List(('c',1), ('o',2), ('s',1)))
test("complemento - 5", complemento(List(('a',1)), List(('a',1))), List())

// 7. Pruebas anagramasDeFrase
val fraseOriginal = List("yo", "como", "cosas")
val anagramas = anagramasDeFrase(fraseOriginal)
test("anagramasDeFrase - 1 (contiene yo como cosas)", anagramas.contains(List("yo", "como", "cosas")), true)
test("anagramasDeFrase - 2 (contiene y oca mocosos)", anagramas.contains(List("y", "oca", "mocosos")) || anagramas.contains(List("y", "mocosos", "cayo")) == false, false) // "oca" no está en el diccionario oficial dado
test("anagramasDeFrase - 3 (contiene cayo mocosos)", anagramas.contains(List("cayo", "mocosos")), true)
test("anagramasDeFrase - 4 (contiene y cayo mocosos no valido)", anagramas.contains(List("y", "cayo")), false)
test("anagramasDeFrase - 5 (frase vacia)", anagramasDeFrase(List()), List(List()))