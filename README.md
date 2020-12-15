# 2D_Chess

## Introduction 
2D Chess is a networking implementation of the popular 2-player board game, chess, using server-client architecture to allow for multiplayer gaming.

## Features
1.	Allow client to join a game
2.	Allow client to make a move
3.	Allow client to leave the game
4.	Server broadcasts messages such as: client has joined, which client has won, client has left, etc

## Getting Started
### Installation and Setup
1.	Install Eclipse IDE for Java Developers
2.	Clone this repository and import it into Eclipse

### Run
1.	This game is run from Eclipse, the game starts from the ChessServer class.
2.	Run a ChessClient, the server will send a message to the client, or player, asking for their name.
3.	After entering a name, the server will send a message saying hello, with the instruction that the player can enter {quit} to leave the game.
4.	The game does not start until there is a 2nd player that joins, and the joining process is also repeated with them.

## Demo Video
https://youtu.be/l5WLvmp7xvU 

## Contributors
Yuliya Smilyanova and 
Tiffany Phan
