
//  val fruits = arrayOf<String>("Apple", "Mango", "Banana", "Orange")

/*  fun main(){
 adder(fruits);
 arrayMethods(fruits)
 checkIfElementOfArrayExist(fruits)
 }

  working with arrays
 fun adder(args: Array<String>) {
     println( args [0])
     println( args [3])
  }

  working with array methods
  fun arrayMethods(args: Array<String>) {
  
     println("++++++++++++++++++++++")
     println( args [0])
     println( args [3])
     println("++++++++++++++++++++++")
    println( args.get(0))
    println( args.get(3))
   
     Set the value at 3rd index
    args.set(3, "Guava")
    println( args.get(3)) 
    println("++++++++++++++++++++++")
 println(args.size)

 }

 fun loops(last:Array<String>){

     for (i in last) {
         println(i)
     }
 }

 fun checkIfElementOfArrayExist(listOfData:Array){
     if("Guava" in listOfData){
 println("the said dat exist")
     }else{

     }

 }
*/
/*Distinct Values from Array
Kotlin allows to store duplicate values in an array, but same 
time you can get a set of distinct values stored in the array using distinct() member function.
 
  fun main() {
     val fruits = arrayOf<String>("Apple", "Mango", "Banana", "Orange", "Apple")
    
     val distinct = fruits.distinct()
     for( item in distinct ){
        println( item )
     }
  }
Check is an array is empty

 fun main(args: Array<String>) {
     val fruits = arrayOf<String>()
     println( "Array is empty : " + fruits.isEmpty())  
  }

Ranges

fun main(args: Array<String>) {
    for ( num in 1.rangeTo(4) ) {
      println(num)
    }
 }
Ranges in .. form

fun main(args: Array<String>) {
    for ( num in 1..(9) ) {
      println(num)
    }
 }

Ranges using DownTO

 fun main(args: Array<String>) {
    for ( num in 4 downTo 1 ) {
      println(num)
    }
 }
- Kotlin step() Function
- We can use step() function to define the distance between the values of the range. Let's have a look at the following example:
 
fun main(args: Array<String>) {
   for ( num in 1..10 step 2 ) {
     println(num)
   }
}
- Kotlin range of Characters
- Ranges can be created for characters like we have created them for integer values.
 

 fun main(args: Array<String>) {
    for ( value in 'a'..'k' step 2) {
      println(value)
    }
 }
 
- Kotlin reversed() Function
- The function reversed() can be used to reverse the values of a range.

fun main(args: Array<String>) {
    for ( num in (1..5).reversed() ) {
      println(num)
    }
 }

- The last, first, step Elements
- We can use first, last and step properties of a range to find the first, the last value or the step of a range.



fun main(args: Array<String>) {
    println((5..10).first)
    println((5..10 step 2).step)
    println((5..10).reversed().last)
 }



 fun main(args: Array<String>) {
    val a = 1..10
    val f = a.filter { T -> T % 2 == 0 }
    
    println(f)
 }


 fun sumTwo(a:Int, b:Int):Unit{
    val x = a + b
    
    println( x )
 } 
   the code above is similar to this in dart 
void sumTwo(int a,int b){
    var x  = a + b;
    print(x)
}


fun main(args: Array<String>) {
    val a = 4
    
    val result = factorial(a)
    println( result )
    
 }
 
 fun factorial(a:Int):Int{
    val result:Int
    
    if(a <= 1){
       result = a
    }else{
       result = a*factorial(a-1)
    }
    
    return result
 }
*/
 fun main(args: Array<String>) {
   
    val result = calculate(4, 5, ::sun) 
    println( result )
    
 }
 fun sum(a: Int, b: Int) = a + b 
 
 fun calculate(a: Int, b: Int, operation:(Int, Int) -> Int): Int {
     return operation(a, b)                                       
 }

