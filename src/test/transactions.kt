fun main() {

   val act1 = Account("Jesse", 90688, 200000, mutableListOf<TransactionSend>())
   val act2 = Account("Samuel", 80877, 10000, mutableListOf<TransactionSend>())

    Transaction(act1, act2, 30000)
}



  class Transaction(val fromWho: Account, val toWho: Account, val amount: Int) {

   init {
       send()
   }

    fun send() {
       if (!fromWho.hasSufficientBalance(amount)) {
         transferFailed()
           return
       }else{
         transfer()
         logTransaction()
         return
       }
      
   }
   fun transferFailed(){
      println("Insufficient balance from Sender ${fromWho.accountName} with account Number: ${fromWho.accountNumber}\nAccount Balance is #${fromWho.accountBalance}")
      println("Transaction failed\n")
      logTransaction()
      Handler("Insuficient Balance",false)
   }

   fun transfer(){
      var transaction = TransactionSend(fromWho,toWho,amount)
   
      transaction.fromWho.accountBalance = transaction.fromWho.accountBalance?.minus(amount)
      transaction.toWho.accountBalance = transaction.toWho.accountBalance?.plus(amount)
      
      logger("SENDING")
      println("RECIEVER DETAILS: \nTransactions Recieved by ${transaction.toWho.accountName}\n Balance: ${transaction.toWho.accountBalance}\n")
      println("SENDER DETAILS: \nTransactions Sent by  ${transaction.fromWho.accountName}\n Balance: ${transaction.fromWho.accountBalance} \n")
      println("\nAmount Transfered: ${transaction.amount}\n")

      logger("SENT")

   }

   

    fun logTransaction(){
      var transaction = TransactionSend(fromWho,toWho,amount);
      fromWho.transactions.add(transaction);
      toWho.transactions.add(transaction);
      logger("ADDING TO HISTORY")
      println("Transaction added to history.\nDetails: \nTransactions Recieved From ${transaction.fromWho.accountName} - ${transaction.toWho.accountName}\n")
      println("Transaction added to history.\nDetails: \nTransactions Sent: Sender ${transaction.fromWho.accountName} - Reciever: ${transaction.toWho.accountName}\n")
      println("\nAmount Transfered: ${transaction.amount}\n")
      logger("ADDED TO HISTORY")
    }


}
data class Handler (
   val message:String="Not Handled [Message Not Passed to Handler]",
   val sucessfull:Boolean=true,
){
   init{
      if(sucessfull){
         logger("SUCESSFUL TRANSACTION ${message}")
      }else{
         logger("UNSUCESSFUL TRANSACTION ${message}")

      }
   }
}

fun logger(log:String){
 println("==================================[${log}]========================================")
}

data class Account(
   var accountName: String?,
   var accountNumber: Int?,
   var accountBalance: Int?,
   var transactions: MutableList<TransactionSend>
) {
   fun hasSufficientBalance(amount: Int): Boolean {
       return accountBalance ?: 0 >= amount
   }
}

data class TransactionSend constructor(
   var fromWho: Account,
   var  toWho: Account,
   var amount: Int
)

