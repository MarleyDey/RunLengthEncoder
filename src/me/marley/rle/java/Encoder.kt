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
        return ""
    }

}