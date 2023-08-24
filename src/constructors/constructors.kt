fun main(args: Array<String>) {
    // var a = GrandChild()
    // Male("Jesse",32).talk();
    Card().draw();
}

open class Human  {
  open  val name:String? = null;
  open  val age:Int? = null;
  open  val gender:String? = null;

    open  fun talk(){
        println("Override this Text !")
    }

    open  fun walk(){
        println("Override this Text !")
    }
 

}


class Male  constructor( val _name:String, val _age:Int):Human() {
    override  val name:String?;
    override  val age:Int?;
    override  val gender:String?;

   init{
        this.name = _name;
        this.age = _age;
        this.gender = "Male";
    }

    override fun talk(){
        super.talk()
        println("${this.name} talks !")
    }

    override fun walk(){
        println("${this.name} walks !")
    }
}

///  Derived Class Initialization Order
open class Base {
    init{
       println("I am in Base class")
    }
 }
 open class Child: Base() {
    init{
       println("I am in Child class")
    }
 }
 class GrandChild: Child() {
    init{
       println("I am in Grand Child class")
    }
 }



    open class Rectangle {
        open fun draw() { println("Rectangle Class") }
    }

    open class Square {
        open fun draw() { println("Square Class") }
    }

    interface Polygon {
        open fun draw() { println("Polygon Interface") } 
    }

class Card() : Rectangle(), Polygon {

    override fun draw() {

        super<Rectangle>.draw() // call to Rectangle.draw()
        super<Polygon>.draw() // call to Polygon.draw()
        // super<Square>.draw() // call to Square.draw()

        }

    }




    