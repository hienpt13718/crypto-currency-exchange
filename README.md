# crypto-currency-exchange
Build a cryptocurrency exchange to allow its customers to buy and sell cryptocurrencies.

# Noted: Assume that This guide is used for Windows system.
# 1. Requirements
  * Download Kafka (binary version) at: https://kafka.apache.org/downloads, and extract to a folder (I assume the folder is C:/myapp/Kafka and I used version 2.12-2.7.0, so root Kafka full path is C:/myapp/Kafka/kafka_2.12-2.7.0)
  * Download and install JDK with a version >= 11 (Please add java environment variable as well https://docs.oracle.com/javase/tutorial/essential/environment/paths.html)

# 2. Run application:
  # 2.1. Run Kafka:
   * Open Command Prompt at root Kafka folder (C:/myapp/Kafka/kafka_2.12-2.7.0)
   * Start Zookeeper by running command: .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
   * Start Kafka by running command: .\bin\windows\kafka-server-start.bat .\config\server.properties
   
   # Noted: 
    a. Zookeeper connection default is: localhost:2181. You can change at C:/myapp/Kafka/kafka_2.12-2.7.0/config/zookeeper.properties
    b. If you change Zookeeper connection, so you have to update the Zookeeper connection for Kafka server at: C:/myapp/Kafka/kafka_2.12-2.7.0/config/server.properties
    c. Default consumer and producer connection is: localhost:9092. So, if you want to change it, you can found them at: C:/myapp/Kafka/kafka_2.12-2.7.0/config/consumer.properties and C:/myapp/Kafka/kafka_2.12-2.7.0/config/producer.properties
     
  # 2.2. Run application
  * Open Command Prompt at root project folder and run the following command:
    mvnw.cmd spring-boot:run
  * If the application started successfully, you can test it's working or not by 
    open this link `http://localhost:8087/cce-exchange/api/ping` on your browser.
    
  # 2.3. Testing output:
  Noted that we'll use Postman for testing the result
  * How much NZD does a customer pay when buying {X} BTC?
    Open the link `localhost:8087/cce-exchange/api/buy` in Postman with the following parameters: <br />
    Method: GET <br />
    Body type: JSON <br />
    Body data: <br />
    { <br />
        "currency": "", <br />
        "amount": 4 <br />
    }<br />
    
  * How much NZD does a customer receive when selling {X} BTC?
