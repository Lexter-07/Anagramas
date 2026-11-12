import Anagramas._

lOcPal ( "casa" )
combinaciones ( lOcPal ( "casa" ) )
val frase = List( "cosas", "como", "yo" )
combinaciones ( lOcFrase ( frase ) )
anagramasDeFrase ( frase )

// CASOS DE PRUEBA PROPIOS
lOcPal("yo")
lOcPal("carro")
lOcPal("ocasos")
lOcPal("piña")
lOcPal("moco")

lOcFrase(List("y", "yo"))
lOcFrase(List("mocos", "roca"))
lOcFrase(List("yo", "como", "moco"))
lOcFrase(List("pala", "saco"))
lOcFrase(List("como", "cayo"))


diccionarioPorOcurrencias.get(lOcPal("cosas"))
diccionarioPorOcurrencias.get(lOcPal("roca"))
diccionarioPorOcurrencias.get(lOcPal("y"))
diccionarioPorOcurrencias.get(lOcPal("calvo"))
diccionarioPorOcurrencias.get(lOcPal("caso"))


anagramasDePalabra("cayo")
anagramasDePalabra("cosa")
anagramasDePalabra("macaron")
anagramasDePalabra("piña")
anagramasDePalabra("caro")


combinaciones(lOcPal("y"))
combinaciones(lOcPal("sos"))
combinaciones(lOcPal("piña"))
combinaciones(lOcFrase(List("roca", "moco")))
combinaciones(lOcFrase(List("sos", "sos")))
combinaciones(lOcFrase(List("yo", "como", "roca")))


complemento(lOcPal("cosas"), lOcPal("sos"))
complemento(lOcPal("moco"), lOcPal("mo"))
complemento(lOcPal("ocasos"), lOcPal("cosa"))
complemento(lOcPal("sara"), lOcPal("h"))
complemento(lOcPal("macaron"), lOcPal("cosa"))


anagramasDeFrase(List("moco", "como"))
anagramasDeFrase(List("mocosos"))
anagramasDeFrase(List("cayo", "sos"))
anagramasDeFrase(List("pizza"))
anagramasDeFrase(List("saco", "moco", "y", "carne"))


// CASOS DE PRUEBA GENERADOS POR LA IA
// ...

// Se nos olvidó y al final no los hicimos debido a falta de tiempo.