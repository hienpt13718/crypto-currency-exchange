# crypto-currency-exchange
Build a crypto currency exchange to allow its customers to buy and sell crypto currencies.

# Noted: Assum that This guide is used for Windows system.
# 1. Requirements
  * Donwload Kafka (binary version) at: https://kafka.apache.org/downloads, and extract to a folder (I assum the folder is C:/myapp/Kafka and I used version 2.12-2.7.0, so root Kafka full path is C:/myapp/Kafka/kafka_2.12-2.7.0)
  * Download and install JDK with version >= 11 (Please add java environment variable as well https://docs.oracle.com/javase/tutorial/essential/environment/paths.html)

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
