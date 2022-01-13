# Ninja Battle
<p align="center">
    <img width="100" height="100" src="https://i.imgur.com/bjkhgDQ.png">
</p>
A small java project consisting of Client and Server, that communicate via TCP/UDP protocols.

## Client
The client is equipped with a menu including a button to create a game, a button to display available games, a button for mor information and to turn off the app, made with the help of the swing library.

![](https://i.imgur.com/YQtw7uG.png)

<details><summary>SCREENSHOTS</summary>
<img src="https://i.imgur.com/mNk0EBG.png">
<img src="https://i.imgur.com/7YdYbok.png">
<img src="https://i.imgur.com/gpj6NCp.png">
<img src="https://i.imgur.com/6ee6GcD.png">
</details>

### start command:
```bash
java --enable-preview -jar Client.jar
```

## Server
The server is designed to accept more than one client, and it works with TCP/UDP protocols as the client, creating many games that do not interfere with each other.
It operates as standard on port 6666, and its main function is to transfer information between clients.

### start command:
```bash
java --enable-preview -jar Server.jar [port]
```
### stop command:
```bash
Stop
```

## Language
* Java

## Required version
* java 17.0.1

