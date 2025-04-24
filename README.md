# CS5004 HW9

## Separate some Utility Classes
For **HW8**, we asked the utility classes `Look` and `Use` to return all relevant messages,  
including:

- The core message from the `look` or `use` command
- The damage message caused by a monster in the room
- The health status message after the monster's attack

In **HW9**, we refactored this by introducing two new methods in the model:
- `monsterAttack()`
- `getPlayerStatus()`

These methods handle the additional messages (such as damage and health updates),  
which are not part of the core responsibility of `Look` and `Use`.

This change improves code reuse and clarity by ensuring that `Look` and `Use` are  
each responsible for only one task.

---

## Add Picture-Related Information

In **HW9**, we introduced a graphical view, which requires picture information 
that was not needed in HW8.

To support this, we:

- Added `picture` attributes to each object(concrete) and data class
- Implemented corresponding getter methods

We also changed the return types of several utility classes:

- Before: Returned only a message string
- Now: Return a `String[]` array
    - `array[0]` contains the message
    - `array[1]` contains the picture name

This enables both text display and image displaying in the graphical interface.

---

## Improved Controller Design

The controller now supports three modes: Text, Batch, and Graphical.

Since Text and Batch modes are quite similar, we refactored to avoid placing all logic in the `go()` method.

Instead, we created a shared helper method:
`handleCommand(String command, String answer);`

This method processes player input and executes corresponding model actions.

It’s shared by both text and batch modes, which:
- improves code reuse 
- keeps the goText() and goBatch() methods cleaner and more focused.

## Attention:
- Please use the latest Java version to run our game! We recommend using either JDK 23 or JDK 24.


- The name of our jar file is CS5004HW9.jar, so when running in terminal, please enter the directory "jar" and input like "java -jar CS5004HW9.jar museum.json -graphics".


- When exporting the UML class diagram, some lines were missing, so please use the following link to check the class diagram, thanks!
  
  https://lucid.app/lucidchart/bd38dc78-4d1d-469c-825c-6bb62d11ebc4/edit?viewport_loc=-5637%2C-3432%2C9987%2C4571%2C0_0&invitationId=inv_258b0073-af82-4b4f-9953-5ff709caba24# adventrueGame
