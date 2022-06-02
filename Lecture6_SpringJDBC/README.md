


# Exam question

1. Describe how does Spring JDBC interact with relational database
    - Spring JDBC is API that is used to interact with relational database on low level efficiently.
    - To communicate with specific DB specific driver is needed.
    - Automatically takes care of managing db connection, executing queries, transactions, exceptions handling.
    - User defines the connection parameters and SQL queries and its parameters
    - To transfer data from relational database to Java object user need to create RowMapper< T >
2. Describe Spring transactions and their properties
    - @Transactional - marks a method as transactional.
    - A database transaction is a sequence of actions that are treated as a single unit of work
    - ACID:
        - <b>Atomicity</b>: A transaction should be treated as a single unit of operation, which means either the entire sequence of operations is successful or unsuccessful.
        - <b>Consistency</b>: This represents the consistency of the referential integrity of the database, unique primary keys in tables, etc
        - <b>Isolation</b>:  There may be many transaction processing with the same data set at the same time. Each transaction should be isolated from others to prevent data corruption.
        - <b>Durability</b>: Once a transaction has completed, the results of this transaction have to be made permanent and cannot be erased from the database due to system failure.

 
