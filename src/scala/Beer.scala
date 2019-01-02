package scala

/**
  * Created by yangst on 2018/7/26.
  */
object Beer {

  def hello1() : Unit = {2 to 6 foreach { n => println(s"Hello ${n} bottles of beer")}}

  def hello2() : Unit = {
    var n : Int =2
    while(n <= 6){
      println(s"Hello ${n} bottles of beer")
      n += 1;
    }
  }

  def main(args: Array[String]): Unit = {
    var helloMap : Map[String,Int] = Map("a"->1,"b"->3)
    println(helloMap)
    hello1()
    hello2()
  }
}
