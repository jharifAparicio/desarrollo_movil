package com.example.myapplication

class Operation {
    fun Add(a:Int,b:Int,c:Int,d:Int):String{
        //a+bi+c+di -> (a+c)+(b+d)i
        val first:Int = a+c
        val second:Int = b+d
        val result: String = first.toString() +"+"+ second.toString() +"i"
        return result
    }
    fun Subtract(a:Int,b:Int,c:Int,d:Int):String{
        //a-bi-c-di -> (a-c)-(b+d)i
        val first:Int = a-c
        val second:Int = b+d
        val result: String = first.toString() +"-"+ second.toString() +"i"
        return result
    }
    fun Multiply(a:Int,b:Int,c:Int,d:Int):String{
        //(ac-db)+(ad+dc)i
        val multiplying:Int = a*c-b*d
        val multiplier:Int = a*d+b*c
        val result: String = multiplying.toString() +"+"+ multiplier.toString() +"i"
        return result
    }
    fun Split(a:Int, b:Int, c:Int, d:Int):String{
        // (a+bi)/(c+di)
        // dividend
        val x:Int = (a * c) + (b * d)
        val y:Int = (a * d)-(b * c)
        val dividend:String = x.toString() + "-" + y.toString() + "i"

        // divisor
        val divisor:Int = (c * c) + (d * d)
        val result: String = dividend +"/"+ divisor.toString()
        return result
    }
}