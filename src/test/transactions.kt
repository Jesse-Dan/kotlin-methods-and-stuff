   
   fun main() {
      val accounts = listOf(
        Account("Jesse", 90688, 200000, mutableListOf()),
        Account("Samuel", 80877, 10000, mutableListOf())
    )

    val transactions = generateTransactions(accounts)
    transactions.forEachIndexed { ex, transaction -> 

      Transaction(transaction.fromWho, transaction.toWho, transaction.amount,ex)

      }

   }

   


fun generateTransactions(accounts: List<Account>): List<Transaction> {
    val transactions = mutableListOf<Transaction>()

    for (account1 in accounts) {
        for (account2 in accounts) {
            if (account1 != account2) {
                transactions.add(Transaction(account1, account2, 30000,0))
            }
        }
    }

    return transactions
}

  class Transaction constructor(val fromWho: Account, val toWho: Account, val amount: Int,val ex:Int) {
   val transaction: TransactionSend;

   init {
      transaction =  TransactionSend(fromWho,toWho,amount);
       send()
   }
   fun viewTransactionHistory() {
      val fromWhoTransactions = transaction.fromWho.transactions
      val toWhoTransactions = transaction.toWho.transactions
  
      logger("OPENING TRANSACTION HISTORY")
      logger("SENDER HISTORY")
  
      for (singleTransaction in fromWhoTransactions) {
          println("Index: [${ex}]- Sender: ${singleTransaction.fromWho.accountName} \nReceiver: ${singleTransaction.toWho.accountName}\nAmount: ${singleTransaction.amount}")
      }
  
      logger("RECEIVER HISTORY")
      for (singleTransaction in toWhoTransactions) {
          println("Index: [${ex}]- Sender: ${singleTransaction.fromWho.accountName} \nReceiver: ${singleTransaction.toWho.accountName}\nAmount: ${singleTransaction.amount}")
      }
      
      logger("CLOSING TRANSACTION HISTORY")
      logger("DONE")
  }
  

    fun send() {
       if (fromWho.hasSufficientBalance(amount)) {
         transferFailed()
           return
       }else{
         transfer()
         logTransaction()
         viewTransactionHistory()
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
   
      transaction.fromWho.accountBalance = transaction.fromWho.accountBalance?.minus(amount)
      transaction.toWho.accountBalance = transaction.toWho.accountBalance?.plus(amount)
      
      logger("SENDING")
      println("RECIEVER DETAILS: \nTransactions Recieved by ${transaction.toWho.accountName}\n Balance: ${transaction.toWho.accountBalance}\n")
      println("SENDER DETAILS: \nTransactions Sent by  ${transaction.fromWho.accountName}\n Balance: ${transaction.fromWho.accountBalance} \n")
      println("\nAmount Transfered: ${transaction.amount}\n")

      logger("SENT")

   }

   fun saveTransaction():Boolean{
      try {
         fromWho.transactions.add(transaction);
         toWho.transactions.add(transaction);
         return true
      }
      catch(e:Exception) {
         logger("${e.message}")
         return false
      }
     

   }
   

    fun logTransaction(){
      logger("ADDING TO HISTORY")
      if(saveTransaction()){
      println("Transaction added to history.\nDetails: \nTransactions Recieved From ${transaction.fromWho.accountName} - ${transaction.toWho.accountName}\n")
      println("Transaction added to history.\nDetails: \nTransactions Sent: Sender ${transaction.fromWho.accountName} - Reciever: ${transaction.toWho.accountName}\n")
      println("\nAmount Transfered: ${transaction.amount}\n")
      logger("ADDED TO HISTORY")
   }else{
      logger("FAILED TO ADD HISTORY")
      }
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

