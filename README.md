# Adventure Game — CS5004 HW9

A compact **Java adventure game engine** developed as a course project.  
This repository demonstrates:  
- Object-oriented design and MVC architecture  
- JSON-driven, data-oriented game world loading  
- Multiple UI modes (console + graphical)  
- Automated testing with JUnit  

It is intended to serve as a clear **portfolio piece for internship applications**.  

---

## Table of Contents
- [Project Summary](#project-summary)
- [Screenshots & Videos](#screenshots--videos)
- [Project Structure](#project-structure)   
- [Quick Demo (Jar)](#quick-demo-jar)  
- [Run from Source (Development)](#run-from-source-development)  
- [Architecture & Design](#architecture--design)  

---

## Project Summary  
The engine loads **game world definitions** from JSON files (rooms, items, monsters, puzzles) and runs a turn-based adventure game loop.  

Key responsibilities are separated into:  
- **Model** — game state and domain objects  
- **Controller** — command parsing and game flow  
- **View** — text view (`TextView`) and graphical view (`VisualView`)  

This modular design makes the engine **extensible, testable, and easy to understand**.  

---

## Screenshots & Videos  

- **Console Mode**  
  ![Text Mode Demo](assets/text_mode.gif)  

- **Graphical Mode**  
  ![Graphical Mode Demo](assets/graphical_mode.gif)  

- **Batch Mode (Automated)**  
  ![Batch Mode Demo](assets/batch_mode.gif)  

*(GIFs/videos are stored in the `assets/` folder — keep each demo short for quick loading. For longer demos, consider linking to YouTube.)*  

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
 │   └─ view/                   # TextView & VisualView
 │
 ├─ resources/                  # JSON worlds + images
 ├─ test/                       # JUnit tests
 └─ jar/CS5004HW9.jar           # Prebuilt demo jar
```  

---

## Quick Demo (Jar)  

**Prerequisite:** Java 11 or later  

Run examples from the `jar/` folder:  

**Text mode**  
```bash
cd CS5004HW9/hw9/jar
java -jar CS5004HW9.jar ../src/resources/simple_hallway.json -text
```  
![Text Mode Example](assets/text_mode.gif)  

**Graphical mode**  
```bash
cd CS5004HW9/hw9/jar
java -jar CS5004HW9.jar ../src/resources/museum.json -graphics
```  
![Graphical Mode Example](assets/graphical_mode.gif)  

**Batch mode** (commands from file)  
```bash
cd CS5004HW9/hw9/jar
java -jar CS5004HW9.jar ../src/resources/alignquest.json -batch ../test/controllerTest/batch_input.txt
```  
![Batch Mode Example](assets/batch_mode.gif)  

---

## Run from Source (Development)  
1. Open project in IntelliJ IDEA / Eclipse / VS Code with Java.  
2. Add dependencies:  
   - `gson-2.10.1.jar` (JSON parsing)  
   - `junit` & `mockito-core-5.11.0.jar` (testing)  
   - or equivalent Maven/Gradle coordinates.  
3. Run `GameEngineApp` with a JSON file + mode flag (see examples).  

---

## Architecture & Design  
- **MVC-inspired separation**  
  - `model` — domain logic  
  - `view` — rendering (console + GUI)  
  - `controller` — mediates input/output  
- **Data-driven worlds** — all content in `resources/*.json`, editable without touching code.  
- **Command/action system** — small, focused classes (Move, Look, TakeItem, Use, Attack, Save, etc.) that manipulate the model.  
- **Graphical mode** — image support via `VisualView`; JSON data classes include optional `picture` fields.  
