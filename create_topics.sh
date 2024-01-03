docker compose -p implementation -f compose.yml exec -e JMX_PORT= kafka-0 kafka-topics.sh --bootstrap-server kafka-0:9092 --create --topic view-video --replication-factor 3 --partitions 6

docker compose -p implementation -f compose.yml exec -e JMX_PORT= kafka-0 kafka-topics.sh --bootstrap-server kafka-0:9092 --create --topic upload-video --replication-factor 3 --partitions 6

docker compose -p implementation -f compose.yml exec -e JMX_PORT= kafka-0 kafka-topics.sh --bootstrap-server kafka-0:9092 --create --topic dislike-video --replication-factor 3 --partitions 6

docker compose -p implementation -f compose.yml exec -e JMX_PORT= kafka-0 kafka-topics.sh --bootstrap-server kafka-0:9092 --create --topic like-video --replication-factor 3 --partitions 6

docker compose -p implementation -f compose.yml exec -e JMX_PORT= kafka-0 kafka-topics.sh --bootstrap-server kafka-0:9092 --create --topic subscribe-hashtag --replication-factor 3 --partitions 6

docker compose -p implementation -f compose.yml exec -e JMX_PORT= kafka-0 kafka-topics.sh --bootstrap-server kafka-0:9092 --create --topic unsubscribe-hashtag --replication-factor 3 --partitions 6

docker compose -p implementation -f compose.yml exec -e JMX_PORT= kafka-0 kafka-topics.sh --bootstrap-server kafka-0:9092 --create --topic new-user --replication-factor 3 --partitions 6

docker compose -p implementation -f compose.yml exec -e JMX_PORT= kafka-0 kafka-topics.sh --bootstrap-server kafka-0:9092 --create --topic video-tags --replication-factor 3 --partitions 6
