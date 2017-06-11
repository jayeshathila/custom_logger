# custom_logger

This logger library can be used to implement custom implementations of loggers along with two default given implementations:
1. File based logger to log into a file with given path in config (implementation class: FileSink.java)
2. Mongo based logger to dump logs to mongo database with given instance config (implementation class: DatabaseSinkImpl.java)

## Writing Custom Implementation:
To write custom implementation you will have to simply implement Sink interface and agree to the contract in interface by
implementing methods. For implementation details one can follow documentation given in Sink interface.

The configuration needed can be added to resource folder and the path can be provided to LogLevel if a user want consume it
in custom implementation.

## Logging
To log to any log level client simply needs to call Logger.call(Message).

## Creating and using jar
To create jar simple execute `gradle clean build jar`. This will create jar with name "logger-0.1.0.jar" in builds folder.
To start consuming this library one can simply import the jar as external library and start using it.

## Testing
This library has been tested and verified on Mac and Ubuntu, using JUnit and manual verification byepassing asserts.
