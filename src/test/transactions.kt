   
   fun main() {
      val accounts = generateAccounts(25)
      val transactions = generateTransactions(accounts)
  
    
    transactions.forEachIndexed { ex, transaction -> 

      Transaction(transaction.fromWho, transaction.toWho, transaction.amount,transaction.ex)

      }

   }
   fun generateAccounts(count: Int): List<Account> {
      val accounts = mutableListOf<Account>()
      for (i in 1..count) {
          accounts.add(Account("User$i", 10000 + i, 100000 + i * 500, mutableListOf()))
      }
      return accounts
  }

  fun generateUniqueID(prefix: String, length: Int): String {
   val characters = ('A'..'Z') + ('0'..'9')
   var randomID = (1..length)
       .map { characters.random() }
       .joinToString("")

   return "${prefix}_${randomID}"
}



  fun generateTransactions(accounts: List<Account>): List<Transaction> {
   val transactions = mutableListOf<Transaction>()
   for (i in 0 until accounts.size - 1) {
       for (j in i + 1 until accounts.size) {
           transactions.add(Transaction(accounts[i], accounts[j], (i + j) * 1000,"${generateUniqueID("FINNBANK",8)}"))
       }
   }
   return transactions
}


  class Transaction constructor(val fromWho: Account, val toWho: Account, val amount: Int,val ex:String) {
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
          println("TRANSACTION-ID: [${ex}]- Sender: ${singleTransaction.fromWho.accountName} \nReceiver: ${singleTransaction.toWho.accountName}\nAmount: ${singleTransaction.amount}")
      
         }
  
      logger("RECEIVER HISTORY")
      for (singleTransaction in toWhoTransactions) {
          println("InTRANSACTION-IDdex: [${ex}]- Sender: ${singleTransaction.fromWho.accountName} \nReceiver: ${singleTransaction.toWho.accountName}\nAmount: ${singleTransaction.amount}")
          logger("===")

         }
      
      logger("CLOSING TRANSACTION HISTORY")
      logger("DONE")
  }
  

    fun send() {
       if (!fromWho.hasSufficientBalance(amount)) {
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

