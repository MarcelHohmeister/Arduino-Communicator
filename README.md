# Arduino Communicator App

The **Arduino Communicator App** is a JavaFX-based desktop application that enables serial communication between a Java application and an Arduino board. The application follows a **project-based and modular design**, allowing users to create projects that contain different modules which send structured data to the Arduino.

This repository is intended as a **learning and experimentation project**, focusing on JavaFX, Java desktop application architecture, and serial communication with external hardware.

---

## Motivation & Background

This project was started with the goal of **learning and deepening knowledge in**:

* **JavaFX** for modern desktop user interfaces
* **Java application architecture** (controllers, modular structure, separation of concerns)
* **Serial communication in Java** and interaction with microcontrollers (Arduino)

Instead of building a single-purpose tool, the app was designed to be **extensible and modular**, so that new module types (e.g. motor control, sensors, displays) can be added with minimal changes to the existing codebase. This makes the project well-suited as a foundation for further experiments and learning.

---

## Features

### Serial Communication

* The app **automatically tries to connect to an Arduino on COM9** at startup.
* The COM port is currently **hardcoded to COM9**, as this was the port used consistently during development.
* If no Arduino is found on COM9, the connection can be **established manually** by entering the correct port via the **"Verbindung aufbauen"** button in the UI.

> ℹ️ The port handling is intentionally kept simple for now, as the focus of this project is on the core application features rather than advanced configuration.

### Projects

* Create multiple projects with **custom names**.
* Customize each project’s appearance:

  * Background color
  * Text color
  * Border / outline color
* Projects can be **deleted** when no longer needed.

Projects serve as containers for modules and allow different Arduino setups or ideas to be managed independently.

### Modules

* Each project can contain multiple modules.
* Currently supported module types:

  * **RGB Module**
  * **Text Module**
  * **Switch Module**
* Each module has:

  * A **custom name**
  * Individual **color settings**
  * An assigned **data type**

The data type is sent together with the module content to the Arduino, where it can be processed accordingly.

⚠️ **Current limitation:** Individual modules **cannot be deleted yet**.

### Data Persistence

* All changes to the **project and module structure** are automatically saved.
* The current application state is persisted in a file called **`properties.json`**.
* This ensures that projects, modules, and their configurations are **restored on the next application start** without requiring manual reconfiguration.

### Arduino Integration

* The serial communication has currently been **tested only with an Arduino Uno (R1)**.
* Other Arduino boards may work as well, but have **not yet been explicitly tested**.
* An example Arduino Sketch is provided in the `arduino/` folder.
* The Java application sends **structured data (type + value)** to the Arduino.
* The Arduino processes incoming data based on the data type defined in the module.

This separation makes it easy to adapt the Arduino code to custom module types or behaviors.

### Planned Features

* A **console window** to display `Serial.print()` output from the Arduino directly in the app.
* Improved **serial port configuration** (no hardcoded port).
* Module management improvements (e.g. deleting or reordering modules).

---

## Requirements & Installation

### Requirements

* **Java 22**
* **JavaFX 25.0.1**
  * Modules: `javafx-controls`, `javafx-fxml`
* **ControlsFX 11.2.2**
* **jSerialComm 2.11.0**
* **Jackson Databind 2.16.2**
* **Maven** (for building and packaging)

### Installation

1. **Clone the repository**
(git clone https://github.com/MarcelHohmeister/Arduino-Communicator.git)
2. **Open the project** in your favorite Java IDE.
3. **Connect your Arduino** to your computer via USB.
4. **Build and run** the application with Maven or just run it in your IDE.
   4.1. Your The app will automatically attempt to connect to COM9.
   4.2. If no Arduino is detetcted, use **"Verbindung aufbauen"** to select the correct port manually.
5. **Create a new project** with your desired modules.

### Notes & Limitations

Only adding modules is currently supported; deleting individual modules is **not implemented yet**.
The Arduino sketch must handle the **same data types** that are configured in the app.
The application UI is currently **German only**.
The app tries COM9 first on startup; manual selection is required if another port is used.
"Einstellungen" button for modules has no function yet.
The serial console feature is planned but not yet available.

### Status

This project is used as a learning project and may evolve over time as new features are implemented and the architecture is refined.
