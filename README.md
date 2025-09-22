# Adventure Game Engine  
*Java | MVC | JSON-Driven | GUI + Console*  

Developed as part of **CS5004: Object-Oriented Design**, this project demonstrates applied software engineering practices through a fully functional adventure game engine.

This repository highlights:  
- Object-oriented design and MVC architecture  
- JSON-driven, data-oriented game world loading  
- Multiple UI modes (console + graphical)  
- Automated testing with JUnit  
---

## Table of Contents
- [Project Summary](#project-summary)
- [Features](#features)
- [Feature Showcase](#feature-showcase)
- [Project Structure](#project-structure)   
- [Quick Demo (Jar)](#quick-demo-jar)
- [Tech Stack](#tech-stack) 
- [Architecture & Design](#architecture--design)  

---

## Project Summary  
The engine loads **game world definitions** from JSON files (rooms, items, monsters, puzzles) and runs a turn-based adventure game loop.  

Key responsibilities are separated into:  
- **Model** — game state and domain objects  
- **Controller** — command parsing and game flow  
- **View** — text view (`TextView`) and graphical view (`VisualView`)  

This modular design makes the engine **extensible, testable, and easy to maintain**.

---

## Features  
- **Inventory Management** – take, use, and drop items  
- **Puzzle Interaction** – examine items and solve in-game puzzles  
- **Persistence** – save and reload progress at any point  
- **Multiple Modes**  
  - Graphical (Swing GUI with images)  
  - Text (console-based)  
  - Batch (automated input from file)

---

## Feature Showcase
- **Graphical Mode**  
  Take, use, and drop items:
  ![take](https://github.com/user-attachments/assets/bec8bf47-b492-4eda-ae68-273323bd7a75)
  ![use_drop](https://github.com/user-attachments/assets/5ef40571-da55-4fa3-9f02-d90b32f9f542)

  Examine room items and solve puzzles:
  ![answer](https://github.com/user-attachments/assets/2588b225-26fe-4e31-b880-86e908c96429)

  Save and reload progress at any point:
  ![save](https://github.com/user-attachments/assets/81a61664-4bc5-45ec-8d09-343a0245a40f)
  ![reload](https://github.com/user-attachments/assets/93553f4f-2e94-4d3b-80fa-2ba5fc89377b)

- **Text Mode**  
  ![text](https://github.com/user-attachments/assets/d00d2f6d-a042-4662-8f79-060454481ae7)

- **Batch Mode (Automated)**  
  ![batch](https://github.com/user-attachments/assets/79fda7b2-455b-46e6-a01d-b691dfa6764b)
---

## Project Structure  
```
hw9/
 ├─ src/
 │   ├─ GameEngineApp.java      # Main entry point
 │   ├─ controller/             # GameController, command readers
 │   ├─ model/                  # Domain logic
 │   │   ├─ dataClasses/        # JSON → Java data parsing
 │   │   ├─ gameModel/          # Core GameModel
 │   │   ├─ objects/            # Player, Room, Monster, Item, etc.
 │   │   └─ utilityClass/       # Command/action classes
 │   ├─ view/                   # TextView & VisualView
 │   └─ resources/              # JSON worlds + images
 │
 ├─ test/                       # JUnit tests
 └─ jar/CS5004HW9.jar           # Prebuilt demo jar

```  

---

## Quick Demo (Jar)  

**Prerequisite:** Java 11 or later  
Run examples from the `jar/` folder:  

**Run in Text Mode**  
```bash
cd CS5004HW9/hw9/jar
java -jar CS5004HW9.jar <filename> -text
```

**Run in Graphical Mode**  
```bash
cd CS5004HW9/hw9/jar
java -jar CS5004HW9.jar <filename> -graphics
```

**Run in Batch Mode**   
```bash
cd CS5004HW9/hw9/jar
java -jar CS5004HW9.jar <filename> -batch <source> 
```
---
## Tech Stack  
- Java 11  
- Swing (GUI)  
- Gson (JSON parsing)  
- JUnit (testing)  

---
## Architecture & Design  
- **MVC-inspired separation**  
  - `model` — domain logic  
  - `view` — rendering (console + GUI)  
  - `controller` — mediates input/output  
- **Data-driven worlds** — all content in `resources/*.json`, editable without touching code.  
- **Command/action system** — modular classes for actions (Move, Look, TakeItem, Save, etc.)  
- **Graphical mode** – supports optional image fields in JSON for GUI rendering.
- **Testing** – JUnit coverage for core components  
