# Тестовое задание на должность Junior Java developer
В этом проэкте я пытался потренироваться в том, чтобы по-максимуму придерживаться принципов SOLID, чтобы код был читаемым, понятным и без повторений. Вышло, на мой взгляд, достаточно хорошо.

Вот, собственно, само задание:
https://dataox.notion.site/Junior-Java-Developer-2208946fe5384e359f5d6a01a8286ad9


# English version:

# Test task for the position of Junior Java developer
In this project, I tried to practice how to adhere to the SOLID principles as much as possible so that the code is readable, understandable and without repetition. It came out pretty good, in my opinion.

Here is the task itself:
https://dataox.notion.site/Junior-Java-Developer-2208946fe5384e359f5d6a01a8286ad9

Here is translation of this task:
# Junior Java Developer test task

## Elevator in the building

It is necessary to write an application without UI (console), or with a minimal UI.

Use OOP and adhere to the principles of SOLID, DRY and ETC.

The building consists of the nth number of floors, where n is a random number generated at the start of the program in the range 5 <= n <= 20.

There are k number of passengers on each floor, where k is a random number generated at the start of the program in the range 0 <= k <= 10.

Each passenger wants to arrive at a certain floor different from the one on which he is.

Each floor has two buttons to call the elevator "Up" and "Down". the exception is the lower and upper floors.

The elevator has a capacity limit of 5 people maximum.

The first time the elevator is loaded with people from the first floor, and travels from the first (current) to the largest of those that passengers need.

On the way, the elevator stops at those floors where passengers need to drop them off and pick up people who need to go in the same direction in which the elevator is moving.

Also, if the elevator is not fully loaded, it can stop on the floor where there are people who need to go in the same direction.

When boarding new passengers, the maximum floor is recalculated.

Here is an example

Building of 10 floors

*Step 1*

3 people sat on the first floor:

the 1st one needs to go to the 2nd floor;

2nd to the 4th floor;

3rd on the 6th floor;

At this stage, the elevator will have 3 passengers, 2 free seats and direction up to the 6th floor.

*Step 2*

The elevator came to the second floor because one of the passengers of the elevator needed to go to this floor.

The passenger got off in the elevator, there were 2 people left, that is, 3 empty seats.

The exiting passenger is assigned a new random floor and joins the people who are waiting for the elevator

There are 5 people on the second floor, two of them need to go up one to 7 the other to 4 floors

These two get into the elevator, so there are 4 people in the elevator and one empty seat. now the elevator will have an upward direction until it arrives at the 7th floor, since the new passenger needs to go to the 7th floor

*Step 3*

The elevator moves on. none of the passengers need to go to the third floor, but there is one empty seat in the elevator and on the third floor, four out of seven people want to go up.

So the elevator must stop on the floor and pick up only one passenger from those who want to go up, as the elevator has only one free seat.

Then the elevator moves with all the necessary stops according to the already known algorithm.

Let's say the maximum floor that the user needs to arrive at has not changed and the elevator has reached the 7th floor and dropped off all the remaining passengers there. Now people on the floor, based on the majority, choose who will sit in the elevator. For example, on a floor, 6 people want to go down and 3 people want to go up. 5 out of 6 who want to go down will sit in the elevator. And the elevator will move down according to the algorithm described below.

Try to use the language as much as possible when writing a program.

All ambiguities can be interpreted independently (for example, where to go next if everyone left on a certain floor, but there are no more passengers on this floor).

For the console program, make the output frame-by-frame - for each movement of the elevator one frame.

Example - image on page https://dataox.notion.site/Junior-Java-Developer-2208946fe5384e359f5d6a01a8286ad9
