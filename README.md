# Homework 42 — Chat Server

## Description
Minimal multi-threaded chat server.

Features:
- Accepts multiple clients
- Broadcasts messages to all connected users
- Random username for each client
- Sender does not receive own message
- Handles client disconnect safely

## Project structure

EchoServer/
 └── src/
     ├── EchoServer.java
     └── ClientHandler.java

## Requirements
Java 8 or higher

## How to compile

Open terminal in folder:

EchoServer/src

Run:

javac *.java

## How to run server

java EchoServer

Server will start on port 12345.

## How to run client

In folder with SwingChatClient.jar run:

java -jar SwingChatClient.jar

You can open multiple clients to test chat.

## Message format

<username>: message
