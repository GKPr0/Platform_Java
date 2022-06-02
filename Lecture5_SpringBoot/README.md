

# Spring Boot

1. Update assignment 4 to use logging and Spring boot
2. Study one open source logging stack
   * Elasticsearch + Logstash + Kibana
   * GrafanaLabs Loki + Grafana
3. Study application containerization using Docker


# Exam questions

1. Describe annotations
   1. @SpringBootApplication - Is shortcut for @Configuration, @EnableAutoConfiguration, @ComponentScan
   2. @EnableAutoConfiguration - Enables Spring Boot to auto-configure the application context. It automatically creates and registers beans based on both the included jar files in the classpath and the beans defined by us. Convention over configuration approach.
   3. @PropertySource - Sets the property file to be used.
   4. @Profile - Desribe in with profile the bean should be registred to app context
   5. @Value - Sets a property value. Source can be for example he application.properties file, raw string
2. Describe basic components of Logback logging system
   - Appender: Where and how the log is written to
   - Encoder: transofrm log message to a specific format and writing out into an OutputStream
   - Layout: How the log is formatted
   - Filter: Filters the log
   - Logger: Used to communicate with loging system. Can be used to filter log messages
3. Describe 3 logging levels with examples when to use them
   - Top most level is Trace and contains the most information
   - TRACE: Used for tracing
   - DEBUG: Used for debugging
   - INFO: Used for general information
   - WARN: Used for warning
   - ERROR: Used for error
   - FATAL: Used for fatal error
4. Difference between SL4F and Logback
   - SL4F: Is logging facade (interface)
   - Logback: Is logging implementation (implementation)
5. Describe one of open source logging stack
   1. ELK
      - <b>Elasticsearch</b>
         - Is a distributed search and analytics engine
         - Support for various languages, high performance, and schema-free JSON documents makes Elasticsearch an ideal choice for various log analytics and search use cases
      - <b>Logstash</b>
         - Is an open-source data ingestion tool that allows you to collect data from a variety of sources, transform it, and send it to your desired destination. 
         - With pre-built filters and support for over 200 plugins, Logstash allows users to easily ingest data regardless of the data source or type
      - <b>Kibana</b>
         - Is a data visualization and exploration tool for reviewing logs and events. 
         - Kibana offers easy-to-use, interactive charts, pre-built aggregations and filters, and geospatial support and making it the preferred choice for visualizing data stored in Elasticsearch.
   2. Grafana + Loki
      - <b>Grafana</b>
         - Allows you to query, visualize, alert on, and understand your metrics no matter where they are stored. 
         - Also allows to filter, sort, and aggregate yours log.
      - <b>Loki</b>
         - Loki is a horizontally scalable, highly available, multi-tenant log aggregation system inspired by Prometheus.
         - It is designed to be very cost effective and easy to operate. It does not index the contents of the logs, but rather a set of labels for each log stream.

6. Describe following application containerization terms
   1. Image
      - Is a lightweight, standalone, executable package of software that includes everything needed to run an application: code, runtime, system tools, system libraries and settings.
   2. Repository
      - Is a collection of images
   3. Container
      - Is dependent on images and use them to construct a run-time environment and run an application

