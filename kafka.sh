docker exec -it kafka bash
kafka-topics.sh --create --topic test --bootstrap-server localhost:9092
kafka-console-producer.sh --topic test --bootstrap-server localhost:9092
kafka-console-consumer.sh --topic test --from-beginning --bootstrap-server localhost:9092