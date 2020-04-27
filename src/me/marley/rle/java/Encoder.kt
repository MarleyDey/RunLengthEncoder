package me.marley.rle.java

import java.lang.StringBuilder

class Encoder(private var input: InputParser) {

    fun encode() : String {
        val resultBuilder =  StringBuilder();
        val inputString = input.getInput();

        if (inputString.isEmpty()) throw Exception("Input cannot be empty.")
        if (inputString.contains("""([0-9])+""".toRegex())) throw Exception("Cannot encode numbers.")

        var a = 0
        var cc = inputString[0]

        for (c in inputString){
            if (c != cc){
                resultBuilder.append("$a$cc")

                a = 1
                cc = c
                continue
            }
            a++
        }
        resultBuilder.append(a, cc + "")
        return resultBuilder.toString()
    }

    fun decode() : String {
        val resultBuilder =  StringBuilder()
        val inputString = input.getInput()

        if (inputString.isEmpty()) throw Exception("Input cannot be empty.")

        var i = 0
        while (true){
            if (inputString.length <= i)break;

            var c = inputString[i]
            var a = 0;

            if (c.isDigit()){
                var shift = 0
                while (true){

                    if (inputString.length >= i + shift || !inputString[i + shift].isDigit()){
                        var ac = ""

                        println("Shift: " + shift)
                        for (s in (i + (shift - 1)) downTo i){
                            ac += inputString[s]
                            println("Adding: " + inputString[s])
                            break
                        }

                        a = ac.toInt()
                        println("Amount: $a")
                        i += shift
                    }
                    if (shift++ >= inputString.length) break
                    println("Shift after: " + shift)

                }
                resultBuilder.append(("" + inputString[i - shift]).repeat(a))
                continue
            }

            i++;
        }

        return resultBuilder.toString();
    }
}