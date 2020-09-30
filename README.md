# Java Programming with Sana Odeh Fall 2009

## Program 1

Enter a decimal number between 0 and 255, for example, 55

The binary representation is: 00110111

Binary number 00110111 can be converted back the original decimal number 55

## Program 2

Enter a decimal number between 0 and 255, for example, 255

The binary representation of decimal 255 is 11111111

Binary number 11111111 can be converted back the original decimal number 255

The hexadecimal representation of decimal 255 is FF

## Program 3

[video](https://youtu.be/4RYa8g1v3RQ)

How to play Craps?

Craps is a dice game. Two players take turns. You start with a balance of 100 dollars. You roll two dice each time and the sum ranges from 2 to 12. Before you roll, you bet. 

* If you get 7 or 11, you win.
* If you get 2 or 12, you lose.
* If you get any other numbers, which we call X, you need to keep rolling. If you get X again, you win. If you hit 7 in these subsequent rolls, you lose.

Under two scenarios, the game ends: 
* One player reaches the goal of 300 dollars.
* One player runs out of money.

## Program 4

![](https://github.com/ffmaer/Java-Programming/raw/2020/4.Game-of-Life/images/big-bang.png?raw=true)

>The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970. The "game" is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves.

>The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:

>* Any live cell with fewer than two live neighbours dies, as if caused by under-population.
>* Any live cell with two or three live neighbours lives on to the next generation.
>* Any live cell with more than three live neighbours dies, as if by overcrowding.
>* Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

>The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed—births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick (in other words, each generation is a pure function of the preceding one). The rules continue to be applied repeatedly to create further generations.

>- https://en.wikipedia.org/wiki/Conway's_Game_of_Life

## Program 5

Mastermind is a single-player code-breaking game. The goal is to break a code. The code is made of four big code-pegs. Each code-peg can have either of the 6 colors. Each turn you guess the code. 4 small key-pegs are used to indicate the correctness of your guess.

A black key-peg indicates that a code-peg has both the right color and right spot.
A gray key-peg indicates that a code-peg has the right color but the wrong spot.

You win if you break the code within 12 attempts. The player begins with 360 points. 10 points are deducted for each guess. Use as few guesses as possible to achieve high scores.
