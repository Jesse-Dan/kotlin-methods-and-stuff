fun main(args: Array<String>) {
// val person = Person("Jesse",22)
// person.createSentence();
println(userInfo())
}


class Person constructor (val _name:String,val _age:Int){ 
val name:String;
val age:Int;


init{
this.name = _name;
this.age = _age;
println("Name: ${name}")
println("Age: ${age}:")
}

fun createSentence(){
    println("This is a test \nName:${name}\nAge:${age}");
}
 }


 typealias User = Triple<String, String, Int>


 fun userInfo():User{
    return Triple("Zara","Ali",21)
 }