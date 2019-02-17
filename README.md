# TokenManager
Token manager allows you to issue tokens of various counter available in bank. 

Problem statement:
You are a developer who has been assigned to design a bank branch token system. To create a market compelling product you have been given the following specifications:
- A customer arrives to a bank will get a token from token machine.
- token will be displayed on the token display machine with the bank counter number.
- privileged customer of the bank would get higher priority by the bank.

1) Please design your object model for a token system.
Application mainly consist of following object models
    - **Client**: 
      Represents the client application which will issue token request.

    - **TokenService**: 
     Responsible for accepting token request and add created Token to queue. Also, responsible for creating Counter threads which will process the Tokens.
     
    - **Counter**: Thread responsible to process Token, it takes Token from queue to process.
     
    - **QueueService**: Priority blocking queue responsible for prioritizing queue based on privilege status of customer.  
    
    - **Token** : Represents token issued to customer.
    
    - **Customer**: Represents the customer of the bank.


2) Please define the methods for your token system. 
   - See code for details
   
3) Describe how your objects and methods would interact to create a token system.
   - Client like UI or API, will use TokenService to issue token. TokenService initialized the system with Counter threads which will process the issued Tokens. 
   On receiving token, token service adds token to priority queue using QueueService. QueueService responsible for prioritizing queue based on privilege status.  
4) implement the same as a demoable java application.
   - See code for details
 
 
 #Potential Enhancements
 Current application contains queue which is shared between multiple counters, to enhance the experience we can maintain separate queue for each Counter.
