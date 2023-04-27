import java.util.Random
import java.io.*

fun processaNomeJogador(texto: String): String? {
    var primeiroNome: String
    var count: Int

    if (texto != "" && texto != " ") {

        //Recolhe apenas o primeiro nome do jogador
        var countLetras = 0
        while (texto[countLetras] == ' ') {
            countLetras++
        }

        primeiroNome = ""

        while (countLetras < texto.length && texto[countLetras] != ' ') {
            val caracter = texto[countLetras]
            primeiroNome = "$primeiroNome$caracter"
            countLetras++
        }

        count = 0

        //Devolve a quantidade de minusculas
        for (caracter in primeiroNome) {
            if (caracter.isLowerCase()) {
                count++
            }
        }

        //verificar se tem algum número
        var eDigito = 0
        for (i in primeiroNome) {
            if (i.isDigit()) {
                eDigito++
            }
        }

        //Validar tudo em conjunto, se o nome não é vazio, se não é tudo minusculas, se não têm digitos
        //e se a primeira letra é maiuscula
        return if (texto != "" && texto.length > 1 && count == primeiroNome.length - 1 && eDigito == 0 && primeiroNome[0].isUpperCase()) {
            primeiroNome
        } else {
            null
        }
    } else {
        return null
    }
}

fun insereNavio(tabuleiro: Array<Char?>, posicaoNavio: Int, dimensaoNavio: Int): Boolean {

    when (dimensaoNavio) {
        1 -> {

            if (posicaoNavio > 1 && posicaoNavio < tabuleiro.size) {
                for (idx in 0..tabuleiro.size) {
                    if (idx == posicaoNavio && tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null && tabuleiro[idx] == null) {
                        return true
                    }
                }
            } else if (posicaoNavio == 1) {
                //Verificação das colisões
                for (idx in 0..tabuleiro.size) {
                    if (idx == posicaoNavio && tabuleiro[idx - 1] == null && tabuleiro[idx] == null) {
                        return true
                    }
                }
            } else if (posicaoNavio == tabuleiro.size) {
                //Verificação das colisões
                for (idx in 0..tabuleiro.size) {
                    if (idx == posicaoNavio && tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null) {
                        return true
                    }
                }
            }
            return false
        }
        2 -> { //Verificação de posicionamento
            if (posicaoNavio > 1 && posicaoNavio + 1 < tabuleiro.size) {
                //Verificação das colisões
                for (idx in 0..tabuleiro.size) {
                    if (idx == posicaoNavio && tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null && tabuleiro[idx] == null && tabuleiro[idx + 1] == null) {
                        return true
                    }
                }

            } else if (posicaoNavio == 1) {
                //Verificação das colisões
                for (idx in 0..tabuleiro.size) {
                    if (idx == posicaoNavio && tabuleiro[idx - 1] == null && tabuleiro[idx] == null && tabuleiro[idx + 1] == null) {
                        return true
                    }
                }
            } else if (posicaoNavio == tabuleiro.size - 1) {
                //Verificação das colisões
                for (idx in 0..tabuleiro.size) {
                    if (idx == posicaoNavio && tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null) {
                        return true
                    }
                }
            }
            return false
        }
        3 -> { //Verificação de posicionamento
            if (posicaoNavio > 1 && posicaoNavio + 2 < tabuleiro.size) {
                //Verificação das colisões
                for (idx in 0..tabuleiro.size) {
                    if (idx == posicaoNavio) {
                        if (tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null && tabuleiro[idx] == null && tabuleiro[idx + 1] == null && tabuleiro[idx + 2] == null) {
                            return true
                        }
                    }
                }
            } else if (posicaoNavio == 1) {
                //Verificação das colisões
                for (idx in 0..tabuleiro.size) {
                    if (idx == posicaoNavio && tabuleiro[idx - 1] == null && tabuleiro[idx] == null && tabuleiro[idx + 1] == null && tabuleiro[idx + 2] == null) {
                        return true
                    }
                }
            } else if (posicaoNavio == tabuleiro.size - 2) {
                //Verificação das colisões
                for (idx in 0..tabuleiro.size) {
                    if (idx == posicaoNavio && tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null) {
                        return true
                    }
                }
            }
            return false
        }
        else -> return false
    }
}

fun geraMapaComputador(tabuleiro: Array<Char?>, numNaviosTanque: Int, numContratorpedeiros: Int, numSubmarinos: Int) {

    var qtdNaviosTanque = numNaviosTanque
    var qtdContratorpedeiros = numContratorpedeiros
    var qtdNumSubmarinos = numSubmarinos
    var count = 0

    while (qtdNaviosTanque != 0 || qtdContratorpedeiros != 0 || qtdNumSubmarinos != 0) {

        //Reset no tabuleiro para não dar loop infinito
        if (count == 7) {
            for (i in 0 until tabuleiro.size) {
                tabuleiro[i] = null
            }
            qtdNaviosTanque = numNaviosTanque
            qtdContratorpedeiros = numContratorpedeiros
            qtdNumSubmarinos = numSubmarinos
            count = 0
        }

        val random1 = Random().nextInt(tabuleiro.size)
        val random2 = Random().nextInt(tabuleiro.size)
        val random3 = Random().nextInt(tabuleiro.size)


        if (qtdNaviosTanque != 0) {
            if (random3 > 1 && random3 + 2 < tabuleiro.size) {
                //Verificação das colisões
                for (idx in 0 until tabuleiro.size) {
                    if (idx == random3) {
                        if (tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null && tabuleiro[idx] == null && tabuleiro[idx + 1] == null && tabuleiro[idx + 2] == null) {
                            tabuleiro[random3 - 1] = '3'
                            tabuleiro[random3] = '3'
                            tabuleiro[random3 + 1] = '3'
                            qtdNaviosTanque--
                        }
                    }
                }
            } else if (random3 == 1) {
                //Verificação das colisões
                for (idx in 0 until tabuleiro.size) {
                    if (idx == random3 && tabuleiro[idx - 1] == null && tabuleiro[idx] == null && tabuleiro[idx + 1] == null && tabuleiro[idx + 2] == null) {
                        tabuleiro[random3 - 1] = '3'
                        tabuleiro[random3] = '3'
                        tabuleiro[random3 + 1] = '3'
                        qtdNaviosTanque--
                    }
                }
            } else if (random3 == tabuleiro.size - 2) {
                //Verificação das colisões
                for (idx in 0 until tabuleiro.size) {
                    if (idx == random3 && tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null && tabuleiro[idx] == null && tabuleiro[idx + 1] == null) {
                        tabuleiro[random3 - 1] = '3'
                        tabuleiro[random3] = '3'
                        tabuleiro[random3 + 1] = '3'
                        qtdNaviosTanque--
                    }
                }
            }
        }

        if (qtdContratorpedeiros != 0) {
            if (random2 > 1 && random2 + 1 < tabuleiro.size) {
                //Verificação das colisões
                for (idx in 0 until tabuleiro.size) {
                    if (idx == random2 && tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null && tabuleiro[idx] == null && tabuleiro[idx + 1] == null) {
                        tabuleiro[random2 - 1] = '2'
                        tabuleiro[random2] = '2'
                        qtdContratorpedeiros--
                    }
                }

            } else if (random2 == 1) {
                //Verificação das colisões
                for (idx in 0 until tabuleiro.size) {
                    if (idx == random2 && tabuleiro[idx - 1] == null && tabuleiro[idx] == null && tabuleiro[idx + 1] == null) {
                        tabuleiro[random2 - 1] = '2'
                        tabuleiro[random2] = '2'
                        qtdContratorpedeiros--
                    }
                }
            } else if (random2 == tabuleiro.size - 1) {
                //Verificação das colisões
                for (idx in 0 until tabuleiro.size) {
                    if (idx == random2 && tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null) {
                        tabuleiro[random2 - 1] = '2'
                        tabuleiro[random2] = '2'
                        qtdContratorpedeiros--
                    }
                }
            }
        }

        //Submarino
        if (qtdNumSubmarinos != 0) {
            if (random1 > 1 && random1 < tabuleiro.size) {
                for (idx in 0 until tabuleiro.size) {
                    if (idx == random1 && tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null && tabuleiro[idx] == null) {
                        tabuleiro[random1 - 1] = '1'
                        qtdNumSubmarinos--
                    }
                }
            } else if (random1 == 1) {
                //Verificação das colisões
                for (idx in 0 until tabuleiro.size) {
                    if (idx == random1 && tabuleiro[idx - 1] == null && tabuleiro[idx] == null) {
                        tabuleiro[random1 - 1] = '1'
                        qtdNumSubmarinos--
                    }
                }
            } else if (random1 == tabuleiro.size) {
                //Verificação das colisões
                for (idx in 0 until tabuleiro.size) {
                    if (idx == random1 && tabuleiro[idx - 2] == null && tabuleiro[idx - 1] == null) {
                        tabuleiro[random1 - 1] = '1'
                        qtdNumSubmarinos--
                    }
                }
            }
        }
        count++
    }
}

fun obtemMapa(tabuleiro: Array<Char?>, real: Boolean): Array<String> {

    var stringDecimais = ""
    var stringPos = ""
    var stringTabuleiro = ""
    val retorna: Array<String>
    var count1 = 0
    var dezenas = 0
    var novoTabuleiro: Array<Char?> = arrayOf()

    novoTabuleiro += tabuleiro

    //Faz os dezenas
    for (countMapa in 0 until novoTabuleiro.size) {
        count1++

        when (count1) {
            10 -> {
                dezenas++
                stringDecimais += dezenas
                count1 = 0
            }
            else -> {
                stringDecimais += ' '
            }
        }
    }

    //Faz as unidades
    var count = 0
    for (countMapa in 0 until novoTabuleiro.size) {
        count++
        stringPos += count
        when (count) {
            9 -> count = -1
        }
    }

    //Destinguir tabuleiro real e colocar os devidos char
    if (real) {
        for (i in 0 until novoTabuleiro.size) {
            if (novoTabuleiro[i] == null) {
                novoTabuleiro[i] = '~'
            }
        }
    } else {
        for (i in 0 until novoTabuleiro.size) {
            if (novoTabuleiro[i] == null) {
                novoTabuleiro[i] = '?'
            }

            if (novoTabuleiro[i] == '3') {
                novoTabuleiro[i] = '\u2083'
                if (i >= 2 && i <= novoTabuleiro.size - 1) {
                    if (novoTabuleiro[i] == '\u2083' && novoTabuleiro[i - 1] == '\u2083' && novoTabuleiro[i - 2] == '\u2083') {
                        novoTabuleiro[i] = '3'
                        novoTabuleiro[i - 1] = '3'
                        novoTabuleiro[i - 2] = '3'
                    }
                }
                //Verificar sempre para a frente para não rebentar e zero
                if (i >= 0 && i <= novoTabuleiro.size - 3) {
                    if (novoTabuleiro[i] == '\u2083' && novoTabuleiro[i + 1] == '\u2083' && novoTabuleiro[i + 2] == '\u2083') {
                        novoTabuleiro[i] = '3'
                        novoTabuleiro[i + 1] = '3'
                        novoTabuleiro[i + 2] = '3'
                    }
                }
                if (i >= 1 && i <= novoTabuleiro.size - 2) {
                    if (novoTabuleiro[i] == '\u2083' && novoTabuleiro[i - 1] == '\u2083' && novoTabuleiro[i + 1] == '\u2083') {
                        novoTabuleiro[i] = '3'
                        novoTabuleiro[i - 1] = '3'
                        novoTabuleiro[i + 1] = '3'
                    }
                }
            }
            if (novoTabuleiro[i] == '2') {
                novoTabuleiro[i] = '\u2082'
                //Verificar sempre para trás para não rebentar
                if (i >= 1 && i <= novoTabuleiro.size - 1) {
                    if (novoTabuleiro[i] == '\u2082' && novoTabuleiro[i - 1] == '\u2082') {
                        novoTabuleiro[i] = '2'
                        novoTabuleiro[i - 1] = '2'
                    }
                }
                //Verificar sempre para a frente para não rebentar e zero
                if (i >= 0 && i <= novoTabuleiro.size - 2) {
                    if (novoTabuleiro[i] == '\u2082' && novoTabuleiro[i + 1] == '\u2082') {
                        novoTabuleiro[i] = '2'
                        novoTabuleiro[i + 1] = '2'
                    }
                }
            }
        }
    }
    //Colocar o tabuleiro recebido já com as devidas modificações em string
    for (i in 0 until novoTabuleiro.size) {
        stringTabuleiro += novoTabuleiro[i]
    }

    retorna = arrayOf(stringTabuleiro, stringPos, stringDecimais)

    return retorna
}

fun calculaNumNavios(tamanhoTabuleiro: Int): Array<Int> {
    val umArray: Array<Int>

    when {
        tamanhoTabuleiro in 81..99 -> {
            umArray = arrayOf(2, 3, 6)
            return umArray

        }
        tamanhoTabuleiro in 51..80 -> {
            umArray = arrayOf(1, 2, 5)
            return umArray

        }
        tamanhoTabuleiro in 31..50 -> {
            umArray = arrayOf(1, 1, 3)
            return umArray

        }
        tamanhoTabuleiro <= 30 -> {
            umArray = arrayOf(1, 1, 1)
            return umArray
        }
        else -> {
            umArray = arrayOf(-1, -1, -1)
            return umArray
        }
    }
}

fun lancarTiro(tabuleiroReal: Array<Char?>, tabuleiroPalpites: Array<Char?>, posicaoTiro: Int): String {

    val tiroAgua = "Agua."
    val tiroNavioTanque = "Tiro num navio-tanque."
    val tiroContrapedeiro = "Tiro num contratorpedeiro."
    val tiroSubmarino = "Tiro num submarino."
    val afundou = " Navio ao fundo!"

    if (tabuleiroReal[posicaoTiro] == null) {
        tabuleiroPalpites[posicaoTiro] = 'X'
        return tiroAgua
    }
    if (tabuleiroReal[posicaoTiro] == '3') {
        if (posicaoTiro >= 2 && posicaoTiro <= tabuleiroReal.size - 1) {
            tabuleiroPalpites[posicaoTiro] = '3'
            if (tabuleiroPalpites[posicaoTiro] == '3' && tabuleiroPalpites[posicaoTiro - 1] == '3' && tabuleiroPalpites[posicaoTiro - 2] == '3') {
                return tiroNavioTanque + afundou
            }
        }
        //Verificar sempre para a frente para não rebentar e zero
        if (posicaoTiro >= 0 && posicaoTiro <= tabuleiroReal.size - 3) {
            tabuleiroPalpites[posicaoTiro] = '3'
            if (tabuleiroPalpites[posicaoTiro] == '3' && tabuleiroPalpites[posicaoTiro + 1] == '3' && tabuleiroPalpites[posicaoTiro + 2] == '3') {
                return tiroNavioTanque + afundou
            }
        }
        return tiroNavioTanque
    }
    if (tabuleiroReal[posicaoTiro] == '2') {
        //Verificar sempre para trás para não rebentar
        if (posicaoTiro >= 1 && posicaoTiro <= tabuleiroReal.size - 1) {
            tabuleiroPalpites[posicaoTiro] = '2'
            if (tabuleiroPalpites[posicaoTiro] == '2' && tabuleiroPalpites[posicaoTiro - 1] == '2') {
                return tiroContrapedeiro + afundou
            }
        }
        //Verificar sempre para a frente para não rebentar e zero
        if (posicaoTiro >= 0 && posicaoTiro <= tabuleiroReal.size - 2) {
            tabuleiroPalpites[posicaoTiro] = '2'
            if (tabuleiroPalpites[posicaoTiro] == '2' && tabuleiroPalpites[posicaoTiro + 1] == '2') {
                return tiroContrapedeiro + afundou
            }
        }
        return tiroContrapedeiro
    }
    if (tabuleiroReal[posicaoTiro] == '1') {
        tabuleiroPalpites[posicaoTiro] = '1'
        return tiroSubmarino + afundou
    }

    return "asd"
}

fun geraJogadaComputador(tabuleiroPalpites: Array<Char?>): Int {

    val valida: Boolean = false
    var random1: Int
    val tamanhoTabuleiro = tabuleiroPalpites.size

    do {
        random1 = Random().nextInt(tamanhoTabuleiro)

        if (tabuleiroPalpites[random1] == null) {
            return random1
        }
    } while (!valida)

    return random1
}

fun venceu(tabuleiroPalpites: Array<Char?>): Boolean {
    //Obtem as quantidades de navios
    val qtdTanque: Int = calculaNumNavios(tabuleiroPalpites.size)[0]
    val qtdContratorpedeiro: Int = calculaNumNavios(tabuleiroPalpites.size)[1]
    val qtdSubmarino: Int = calculaNumNavios(tabuleiroPalpites.size)[2]
    var countSub = 0
    var countContr = 0
    var countTanq = 0

    for (i in 0 until tabuleiroPalpites.size) {
        when {
            tabuleiroPalpites[i] == '1' -> countSub++
            tabuleiroPalpites[i] == '2' -> countContr++
            tabuleiroPalpites[i] == '3' -> countTanq++
        }
    }

    if (countSub == qtdSubmarino && countContr == qtdContratorpedeiro * 2 && countTanq == qtdTanque * 3) {
        return true
    }
    return false
}

fun leMapaDoFicheiro(nomeFicheiro: String, tipoMapa: Int): Array<Char?> {

    var mapaFicheiro: Array<Char?>
    var leMapa = ""

    try {
        val lines = File(nomeFicheiro).readLines()

        for (i in 0 until lines[tipoMapa].length) {
            leMapa = leMapa + lines[tipoMapa][i]
        }
        mapaFicheiro = arrayOfNulls(lines[tipoMapa].length)

        for (i in 0 until lines[tipoMapa].length) {
            if (lines[tipoMapa][i] != '~' && lines[tipoMapa][i] != '?') {
                mapaFicheiro[i] = leMapa[i]
            }
        }
        return mapaFicheiro

    } catch (e: FileNotFoundException) {
        mapaFicheiro = emptyArray()
        return mapaFicheiro
    }
}

fun leNomeJogadorDoFicheiro(nomeFicheiro: String): String {

    var retorno = ""

    try {
        val lines = File(nomeFicheiro).readLines()
        retorno += lines[0]
        return retorno
    } catch (e: FileNotFoundException) {
        retorno = ""
        return retorno
    }
}

fun gravaJogoEmFicheiro(
    nomeFicheiro: String,
    nomeJogador: String?,
    tabuleiroRealHumano: Array<Char?>,
    tabuleiroPalpitesHumano: Array<Char?>,
    tabuleiroRealComputador: Array<Char?>,
    tabuleiroPalpitesComputador: Array<Char?>
) {
    var novoPalpitesPc: Array<Char?> = arrayOf()
    var novoPalpitesHum: Array<Char?> = arrayOf()

    novoPalpitesPc += tabuleiroPalpitesComputador
    novoPalpitesHum += tabuleiroPalpitesHumano

    val writer = File(nomeFicheiro).printWriter()

    for (i in 0 until tabuleiroRealHumano.size) {
        if (novoPalpitesPc[i] == null) {
            novoPalpitesPc[i] = '?'
        }
        if (novoPalpitesHum[i] == null) {
            novoPalpitesHum[i] = '?'
        }
    }
    writer.println(nomeJogador)
    writer.println("Real")
    writer.println(obtemMapa(tabuleiroRealHumano, true)[0])

    writer.println("Palpites")
    for (i in 0 until novoPalpitesHum.size) {
        writer.print(novoPalpitesHum[i])
    }
    writer.println()
    writer.println()
    writer.println("Computador")
    writer.println("Real")
    writer.println(obtemMapa(tabuleiroRealComputador, true)[0])

    writer.println("Palpites")
    for (i in 0 until novoPalpitesPc.size) {
        writer.print(novoPalpitesPc[i])
    }
    //writer.println()
    writer.close()
}

fun calculaEstatisticas(tabuleiroPalpites: Array<Char?>): Array<Int> {
    val tamanhoTabuleiroPalpites = tabuleiroPalpites.size
    val dados: Array<Int> = arrayOf(0, 0, 0, 0, 0)
    var countT = 0
    var countCT = 0
    var countS = 0
    var countFalhas = 0
    when {
        tamanhoTabuleiroPalpites in 81..99 -> {

            for (i in tabuleiroPalpites) {
                when (i) {
                    '3' -> countT++
                    '2' -> countCT++
                    '1' -> countS++
                    'X' -> countFalhas++
                }
            }
            when (countT) {
                6 -> {
                    dados[0] = 2
                    dados[4] += countT
                }
                5 -> {
                    dados[0] = 1
                    dados[4] += countT
                }
                4 -> {
                    dados[0] = 1
                    dados[4] += countT
                }
                3 -> {
                    dados[0] = 1
                    dados[4] += countT
                }
                2 -> {
                    dados[0] = 0
                    dados[4] += countT
                }
                1 -> {
                    dados[0] = 0
                    dados[4] += countT
                }
            }

            when (countCT) {
                6 -> {
                    dados[1] = 3
                    dados[4] += countCT
                }
                5 -> {
                    dados[1] = 2
                    dados[4] += countCT
                }
                4 -> {
                    dados[1] = 2
                    dados[4] += countCT
                }
                3 -> {
                    dados[1] = 1
                    dados[4] += countCT
                }
                2 -> {
                    dados[1] = 1
                    dados[4] += countCT
                }
                1 -> {
                    dados[1] = 0
                    dados[4] += countCT
                }
            }
            when (countS) {
                6 -> {
                    dados[2] = 6
                    dados[4] += countS
                }
                5 -> {
                    dados[2] = 5
                    dados[4] += countS
                }
                4 -> {
                    dados[2] = 4
                    dados[4] += countS
                }
                3 -> {
                    dados[2] = 3
                    dados[4] += countS
                }
                2 -> {
                    dados[2] = 2
                    dados[4] += countS
                }
                1 -> {
                    dados[2] = 1
                    dados[4] += countS
                }
            }
        }
        tamanhoTabuleiroPalpites in 51..80 -> {

            for (i in tabuleiroPalpites) {
                when (i) {
                    '3' -> countT++
                    '2' -> countCT++
                    '1' -> countS++
                    'X' -> countFalhas++
                }
            }
            when (countT) {
                3 -> {
                    dados[0] = 1
                    dados[4] += countT
                }
                2 -> {
                    dados[0] = 0
                    dados[4] += countT
                }
                1 -> {
                    dados[0] = 0
                    dados[4] += countT
                }
            }

            when (countCT) {
                4 -> {
                    dados[1] = 2
                    dados[4] += countCT
                }
                3 -> {
                    dados[1] = 1
                    dados[4] += countCT
                }
                2 -> {
                    dados[1] = 1
                    dados[4] += countCT
                }
                1 -> {
                    dados[1] = 0
                    dados[4] += countCT
                }
            }
            when (countS) {
                5 -> {
                    dados[2] = 5
                    dados[4] += countS
                }
                4 -> {
                    dados[2] = 4
                    dados[4] += countS
                }
                3 -> {
                    dados[2] = 3
                    dados[4] += countS
                }
                2 -> {
                    dados[2] = 2
                    dados[4] += countS
                }
                1 -> {
                    dados[2] = 1
                    dados[4] += countS
                }
            }
        }
        tamanhoTabuleiroPalpites in 31..50 -> {

            for (i in tabuleiroPalpites) {
                when (i) {
                    '3' -> countT++
                    '2' -> countCT++
                    '1' -> countS++
                    'X' -> countFalhas++
                }
            }
            when (countT) {
                3 -> {
                    dados[0] = 1
                    dados[4] += countT
                }
                2 -> {
                    dados[0] = 0
                    dados[4] += countT
                }
                1 -> {
                    dados[0] = 0
                    dados[4] += countT
                }
            }

            when (countCT) {
                2 -> {
                    dados[1] = 1
                    dados[4] += countCT
                }
                1 -> {
                    dados[1] = 0
                    dados[4] += countCT
                }
            }
            when (countS) {
                3 -> {
                    dados[2] = 3
                    dados[4] += countS
                }
                2 -> {
                    dados[2] = 2
                    dados[4] += countS
                }
                1 -> {
                    dados[2] = 1
                    dados[4] += countS
                }
            }
        }
        tamanhoTabuleiroPalpites <= 30 -> {

            for (i in tabuleiroPalpites) {
                when (i) {
                    '3' -> countT++
                    '2' -> countCT++
                    '1' -> countS++
                    'X' -> countFalhas++
                }
            }
            when (countT) {
                3 -> {
                    dados[0] = 1
                    dados[4] += countT
                }
                2 -> {
                    dados[0] = 0
                    dados[4] += countT
                }
                1 -> {
                    dados[0] = 0
                    dados[4] += countT
                }
            }

            when (countCT) {
                2 -> {
                    dados[1] = 1
                    dados[4] += countCT
                }
                1 -> {
                    dados[1] = 0
                    dados[4] += countCT
                }
            }
            when (countS) {
                1 -> {
                    dados[2] = 1
                    dados[4] += countS
                }
            }
        }
    }
    dados[3] = dados[4] + countFalhas
    dados[4] = ((dados[4].toDouble() / dados[3].toDouble()) * 100).toInt()
    return dados
}

fun tabuleiroInicialSemNavios(
    dimTabuleiro: Int,
    tabuleiro: Array<Char?>,
    tabuleiroPc: Array<Char?>,
    msgBatalhaNaval: String,
    msgNomeMapa: String
) {

    val mapaArray: Array<String> = obtemMapa(tabuleiro, true)
    val qtdTanque: Int = calculaNumNavios(dimTabuleiro)[0]
    val qtdContratorpedeiro: Int = calculaNumNavios(dimTabuleiro)[1]
    val qtdSubmarino: Int = calculaNumNavios(dimTabuleiro)[2]

    println()
    println(msgBatalhaNaval)
    println()

    println(msgNomeMapa)

    println(mapaArray[0])
    println(mapaArray[1])
    println(mapaArray[2])

    geraMapaComputador(
        tabuleiroPc,
        qtdTanque,
        qtdContratorpedeiro,
        qtdSubmarino
    )
}

fun trataNavio3(
    msgDadoInvalido: String,
    msgPosInvalido: String,
    dimTabuleiro: Int,
    primeiroNome: String?,
    tabuleiro: Array<Char?>,
    msgNomeMapa: String
): Int {

    var navio3: Int?
    var validar4: Boolean
    var mapaArray: Array<String>
    val qtdTanque: Int = calculaNumNavios(dimTabuleiro)[0]

    for (idx in 1..qtdTanque) {
        println()
        println("$primeiroNome, introduz a posicao para 1 navio-tanque:")
        navio3 = readLine()!!.toIntOrNull()

        do {
            if (navio3 != -1) {
                if (navio3 != null) {
                    validar4 = insereNavio(tabuleiro, navio3, 3)

                    if (validar4) {
                        for (i in 0 until tabuleiro.size) {
                            if (i == navio3 - 1 || i == navio3 || i == navio3 + 1) {
                                tabuleiro[i] = '3'
                            }
                        }
                        println()
                    } else {
                        println(msgPosInvalido)
                        navio3 = readLine()!!.toIntOrNull()
                    }
                } else {
                    println(msgDadoInvalido)
                    navio3 = readLine()!!.toIntOrNull()
                    validar4 = false
                }
            } else {
                return -1
            }
        } while (!validar4)

        println(msgNomeMapa)
        mapaArray = obtemMapa(tabuleiro, true)
        println(mapaArray[0])
        println(mapaArray[1])
        println(mapaArray[2])
    }
    return 1
}

fun trataNavio2(
    msgDadoInvalido: String,
    msgPosInvalido: String,
    dimTabuleiro: Int,
    primeiroNome: String?,
    tabuleiro: Array<Char?>,
    msgNomeMapa: String
): Int {

    var validar2: Boolean
    val qtdContratorpedeiro: Int = calculaNumNavios(dimTabuleiro)[1]
    var navio2: Int?
    var mapaArray: Array<String>

    for (idx in 1..qtdContratorpedeiro) {
        println()
        println("$primeiroNome, introduz a posicao para 1 contratorpedeiro:")
        navio2 = readLine()!!.toIntOrNull()

        do {
            if (navio2 != -1) {
                if (navio2 != null) {
                    validar2 = insereNavio(tabuleiro, navio2, 2)

                    if (validar2) {
                        for (i in 0 until tabuleiro.size) {
                            if (i == navio2 - 1 || i == navio2) {
                                tabuleiro[i] = '2'
                            }
                            //   print(tabuleiro[i])
                        }
                        println()
                    } else {
                        println(msgPosInvalido)
                        navio2 = readLine()!!.toIntOrNull()
                    }
                } else {
                    println(msgDadoInvalido)
                    navio2 = readLine()!!.toIntOrNull()
                    validar2 = false
                }
            } else {
                return -1
            }
        } while (!validar2)

        println(msgNomeMapa)
        mapaArray = obtemMapa(tabuleiro, true)
        println(mapaArray[0])
        println(mapaArray[1])
        println(mapaArray[2])
    }
    return 1
}

fun trataNavio1(
    msgDadoInvalido: String,
    msgPosInvalido: String,
    dimTabuleiro: Int,
    primeiroNome: String?,
    tabuleiro: Array<Char?>,
    msgNomeMapa: String
): Int {

    var validar3: Boolean
    val qtdSubmarino: Int = calculaNumNavios(dimTabuleiro)[2]
    var navio1: Int?
    var mapaArray: Array<String>

    for (idx in 1..qtdSubmarino) {
        println()
        println("$primeiroNome, introduz a posicao para 1 submarino:")
        navio1 = readLine()!!.toIntOrNull()

        do {
            if (navio1 != -1) {
                if (navio1 != null) {
                    validar3 = insereNavio(tabuleiro, navio1, 1)

                    if (validar3) {
                        for (i in 0 until tabuleiro.size) {
                            if (i == navio1 - 1) {
                                tabuleiro[i] = '1'
                            }
                            //   print(tabuleiro[i])
                        }
                        println()
                    } else {
                        println(msgPosInvalido)
                        navio1 = readLine()!!.toIntOrNull()
                    }
                } else {
                    println(msgDadoInvalido)
                    navio1 = readLine()!!.toIntOrNull()
                    validar3 = false
                }
            } else {
                return -1
            }
        } while (!validar3)

        println(msgNomeMapa)
        mapaArray = obtemMapa(tabuleiro, true)
        println(mapaArray[0])
        println(mapaArray[1])
        println(mapaArray[2])
    }
    return 1
}

fun trataMapaPc(msgBatalhaNaval: String, msgDadoInvalido: String, tabuleiroPc: Array<Char?>): Int {

    var validaEscolha: Boolean
    var escolha: String

    println()
    println(msgBatalhaNaval)
    println()
    println("Pretende ver o mapa gerado para o Computador? (S/N)")
    do {
        escolha = readLine()!!.toString()
        if (escolha != "-1") {
            if (escolha == "N" || escolha == "S") {
                validaEscolha = true
            } else {
                validaEscolha = false
                println(msgDadoInvalido)
            }
        } else {
            return -1
        }
    } while (!validaEscolha)

    if (escolha == "S") {
        println("Mapa do Computador:")

        println(obtemMapa(tabuleiroPc, true)[0])
        println(obtemMapa(tabuleiroPc, true)[1])
        println(obtemMapa(tabuleiroPc, true)[2])
    }
    return 1
}

fun jogadas(
    tabuleiroPalpites: Array<Char?>,
    primeiroNome: String?,
    tabuleiro: Array<Char?>,
    tabuleiroPalpitesPc: Array<Char?>,
    tabuleiroPc: Array<Char?>
): Boolean {

    var aprova: Boolean
    var aprova2 = false
    val msgErroTiro = ">>> Posicao invalida. Tente novamente"
    var retoma = false
    var jogadaPc: Int
    val msgEnter = "Prima enter para voltar ao menu principal"

    do {
        println()
        println("Mapa do computador:")
        println(obtemMapa(tabuleiroPalpites, false)[0])
        println(obtemMapa(tabuleiroPalpites, false)[1])
        println(obtemMapa(tabuleiroPalpites, false)[2])

        println("$primeiroNome, indique a posicao onde pretende atirar")

        do {
            var tiro = readLine()!!.toIntOrNull()
            if (tiro != null) {
                if (tiro >= 1 && tiro <= tabuleiro.size || tiro == -1) {
                    if (tiro != -1) {
                        tiro--
                        //   if (tabuleiroPalpites[tiro] == null) {
                        aprova = true
                        retoma = true
                        println(">>> " + lancarTiro(tabuleiroPc, tabuleiroPalpites, tiro))
                        aprova2 = venceu(tabuleiroPalpites)

                        if (aprova2) {
                            println("PARABENS! $primeiroNome venceu o jogo!")
                            println(msgEnter)

                            aprova = true
                            aprova2 = true
                            readLine()!!.toString()
                        } else {
                            //Jogada do PC (truque)
                            jogadaPc = 0
                            jogadaPc += geraJogadaComputador(tabuleiroPalpitesPc)
                            jogadaPc += 1

                            println()
                            println("Computador lancou tiro para a posicao $jogadaPc")
                            jogadaPc--
                            println(">>> " + lancarTiro(tabuleiro, tabuleiroPalpitesPc, jogadaPc))

                            if (venceu(tabuleiroPalpitesPc)) {
                                println("OPS! O computador venceu o jogo!")
                                println(msgEnter)

                                aprova = true
                                aprova2 = true
                            } else {
                                println("Prima enter para continuar")
                            }
                            readLine()!!.toString()
                        }
                        //  } else {
                        //    println(msgErroTiro)
                        //  aprova = false
                        //}
                    } else {
                        aprova = true
                        aprova2 = true
                    }
                } else {
                    println(msgErroTiro)
                    aprova = false
                }
            } else {
                println(msgErroTiro)
                aprova = false
            }
        } while (!aprova)

    } while (!aprova2)
    return retoma
}

fun main(args: Array<String>) {
    var tabuleiro: Array<Char?> = arrayOf('s')
    var tabuleiroPc: Array<Char?> = arrayOf('s')
    var tabuleiroPalpites: Array<Char?> = arrayOf('s')
    var tabuleiroPalpitesPc: Array<Char?> = arrayOf('s')

    var cicloPrincipal = 1
    var primeiroNome: String? = ""
    var dimTabuleiro: Int? = 0
    var observa = false

    var aprova: Boolean
    var sai: Int
    var aprovMenu: Boolean
    var opcao: Int?
    var nomeFicheiro: String
    var retomar = false
    var enter: String


    var msgNomeMapa = ""
    val msgBatalhaNaval = "*** BATALHA NAVAL ***"
    val msgPosInvalido = ">>> Posicao do navio invalida, tente novamente"
    val msgDadoInvalido = ">>> Dados de entrada invalidos, tente novamente"

    do {
        println()
        println(msgBatalhaNaval)
        println()
        println("1 - Definir mapa Humano vs Computador")
        if (!retomar) {
            println("2 - Iniciar jogo")
        } else {
            println("2 - Retomar jogo")
        }
        println("3 - Gravar ficheiro")
        println("4 - Ler ficheiro")
        println("5 - Estatisticas")
        println("0 - Sair")
        println()

        do {
            opcao = readLine()!!.toIntOrNull()
            if (opcao != null) {
                aprovMenu = if (opcao < -1 || opcao > 5) {
                    println(msgDadoInvalido)
                    false
                } else {
                    true
                }

                if (!observa && (opcao == 3 || opcao == 2)) {
                    println(">>> Mapas dos jogadores ainda nao definidos, tente novamente")
                    aprovMenu = false
                }

                if (!retomar && opcao == 5) {
                    println(">>> Jogo nao iniciado, tente novamente")
                    aprovMenu = false
                }

            } else {
                println(msgDadoInvalido)
                aprovMenu = false
            }
        } while (!aprovMenu)

        if (opcao != -1) {
            sai = 0


            when (opcao) {
                1 -> {
                    retomar = false
                    println()
                    println(msgBatalhaNaval)
                    println()
                    println("Escolha o tamanho do tabuleiro:")

                    dimTabuleiro = readLine()!!.toIntOrNull()
                    if (dimTabuleiro != -1) {
//------------------Validação do Tabuleiro------------------------------------------------------------------------
                        var validar: Boolean
                        do {

                            if (dimTabuleiro != null) {
                                if (dimTabuleiro < 8 || dimTabuleiro > 99) { //Se for inválido...
                                    println(">>> Tamanho de tabuleiro invalido, tente novamente")
                                    //println()

                                    dimTabuleiro = readLine()!!.toIntOrNull()
                                    if (dimTabuleiro != -1) {
                                        validar = false
                                    } else {
                                        validar = true
                                        sai = -1
                                    }

                                } else {
                                    validar = true
                                } //Se estiver correto...

                            } else {
                                println(msgDadoInvalido)

                                dimTabuleiro = readLine()!!.toIntOrNull()
                                if (dimTabuleiro != -1) {
                                    validar = false
                                } else {
                                    validar = true
                                    sai = -1
                                }
                            }
                        } while (!validar) //Enquanto for inválido...

                        if (sai != -1) {

                            if (dimTabuleiro != null) {

                                //Criar array do tabuleiro do tamanho da dimensão do tabuleiro
                                tabuleiro = arrayOfNulls(dimTabuleiro)
                                tabuleiroPalpites = arrayOfNulls(dimTabuleiro)
                                tabuleiroPc = arrayOfNulls(dimTabuleiro)
                                tabuleiroPalpitesPc = arrayOfNulls(dimTabuleiro)

//------------------Validação do Nome do Jogador------------------------------------------------------------------------

                                println("Introduza o nome do jogador(a):")
                                do {
                                    val nomeJogador = readLine()!!.toString()
                                    if (nomeJogador != "-1") {

                                        if (processaNomeJogador(nomeJogador) == null) {
                                            println(">>> Nome invalido, tente novamente")
                                            aprova = false
                                        } else {
                                            primeiroNome = processaNomeJogador(nomeJogador)
                                            msgNomeMapa = "Mapa do $primeiroNome:"
                                            aprova = true
                                        }

                                    } else {
                                        sai = -1
                                        aprova = true
                                    }
                                } while (!aprova)

//------------------Geração de Mapas-------------------------------------------------------------------------------------
                                if (sai != -1) {
                                    //Desenha o tabuleiro inicial sem navios
                                    tabuleiroInicialSemNavios(
                                        dimTabuleiro,
                                        tabuleiro,
                                        tabuleiroPc,
                                        msgBatalhaNaval,
                                        msgNomeMapa
                                    )
                                }

                                if (sai != -1) {
                                    // Colocar Navio 3
                                    if (trataNavio3(
                                            msgDadoInvalido,
                                            msgPosInvalido,
                                            dimTabuleiro,
                                            primeiroNome,
                                            tabuleiro,
                                            msgNomeMapa
                                        ) == -1
                                    ) {
                                        sai = -1
                                    }
                                }

                                if (sai != -1) {
                                    //Colocar Navio 2
                                    if (trataNavio2(
                                            msgDadoInvalido,
                                            msgPosInvalido,
                                            dimTabuleiro,
                                            primeiroNome,
                                            tabuleiro,
                                            msgNomeMapa
                                        ) == -1
                                    ) {
                                        sai = -1
                                    }
                                }
                                if (sai != -1) {
                                    //Colocar Navio 1
                                    if (trataNavio1(
                                            msgDadoInvalido,
                                            msgPosInvalido,
                                            dimTabuleiro,
                                            primeiroNome,
                                            tabuleiro,
                                            msgNomeMapa
                                        ) == -1
                                    ) {
                                        sai = -1
                                    }
                                    observa = true
                                }

                                if (sai != -1) {
                                    //Ver mapa do computador
                                    if (trataMapaPc(msgBatalhaNaval, msgDadoInvalido, tabuleiroPc) == -1) {
                                    }
                                }
                            }
                        }
                    }
                }

                2 -> {
                    //Jogadas
                    if (jogadas(
                            tabuleiroPalpites,
                            primeiroNome,
                            tabuleiro,
                            tabuleiroPalpitesPc,
                            tabuleiroPc
                        ) == true
                    ) {
                        retomar = true
                    }
                }

                3 -> {
                    //Gravar ficheiros de jogo
                    println()
                    println(msgBatalhaNaval)
                    println()
                    println("Indique o nome do ficheiro onde pretende gravar")
                    nomeFicheiro = readLine()!!.toString()
                    gravaJogoEmFicheiro(
                        nomeFicheiro,
                        primeiroNome,
                        tabuleiro,
                        tabuleiroPalpites,
                        tabuleiroPc,
                        tabuleiroPalpitesPc
                    )
                    println(">>> Ficheiro gravado com sucesso. Prima enter para voltar ao menu.")
                    readLine()!!.toString()
                }

                4 -> {
                    //Carregar ficheiros de jogo
                    println()
                    println(msgBatalhaNaval)
                    println()
                    println("Indique o nome do ficheiro que pretende ler")
                    nomeFicheiro = readLine()!!.toString()
                    retomar = false
                    if (!leMapaDoFicheiro(nomeFicheiro, 2).isEmpty()) {
                        //Dá load dos tabuleiros vindos do ficheiro
                        val retornado = leMapaDoFicheiro(nomeFicheiro, 2).size

                        tabuleiro = leMapaDoFicheiro(nomeFicheiro, 2)
                        tabuleiroPalpites = leMapaDoFicheiro(nomeFicheiro, 4)
                        tabuleiroPc = leMapaDoFicheiro(nomeFicheiro, 8)
                        tabuleiroPalpitesPc = leMapaDoFicheiro(nomeFicheiro, 10)

                        for (i in 0..retornado - 1) {
                            if (tabuleiroPalpitesPc[i] != null) {
                                retomar = true
                            }
                        }
                        primeiroNome = leNomeJogadorDoFicheiro(nomeFicheiro)
                        println(">>> Ficheiro lido com sucesso. Tamanho do tabuleiro: $retornado. Prima enter para voltar ao menu.")
                        observa = true
                        readLine()!!.toString()
                    } else {
                        println(">>> Ocorreu um erro na leitura do ficheiro. Prima enter para voltar ao menu.")
                        readLine()!!.toString()
                    }
                }

                5 -> {
                    //Estatísticas
                    val qtdTanque = calculaNumNavios(tabuleiroPalpites.size)[0]
                    val qtdContratorpedeiro = calculaNumNavios(tabuleiroPalpites.size)[1]
                    val qtdSubmarino = calculaNumNavios(tabuleiroPalpites.size)[2]
                    var dados = calculaEstatisticas(tabuleiroPalpites)
                    println()
                    println(msgBatalhaNaval)
                    println()
                    println("Tamanho do tabuleiro: $dimTabuleiro")
                    println()
                    println(primeiroNome)
                    println("Numero de jogadas: ${dados[3]}")
                    println("Navios-tanque:     ${dados[0]} de $qtdTanque")
                    println("Contratorpedeiros: ${dados[1]} de $qtdContratorpedeiro")
                    println("Submarinos:        ${dados[2]} de $qtdSubmarino")
                    println("Precisao tiro:     ${dados[4]}%")
                    println()
                    dados = calculaEstatisticas(tabuleiroPalpitesPc)
                    println("Computador")
                    println("Numero de jogadas: ${dados[3]}")
                    println("Navios-tanque:     ${dados[0]} de $qtdTanque")
                    println("Contratorpedeiros: ${dados[1]} de $qtdContratorpedeiro")
                    println("Submarinos:        ${dados[2]} de $qtdSubmarino")
                    println("Precisao tiro:     ${dados[4]}%")
                    println()
                    println("Prima enter para voltar ao menu principal")
                    enter = readLine()!!.toString()
                }

                0 -> cicloPrincipal = 0 //Se o Utilizador escolher 0 no menu acaba o jogo.
            }
        }
    } while (cicloPrincipal != 0)
}