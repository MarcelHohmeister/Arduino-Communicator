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

---

### Projects

* Create multiple projects with **custom names**.
* Customize each project’s appearance:

  * Background color
  * Text color
  * Border / outline color
* Projects can be **deleted** when no longer needed.

Projects serve as containers for modules and allow different Arduino setups or ideas to be managed independently.

---

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

---

### Data Persistence

* All changes to the **project and module structure** are automatically saved.
* The current application state is persisted in a file called **`properties.json`**.
* This ensures that projects, modules, and their configurations are **restored on the next application start** without requiring manual reconfiguration.

---

### Projects

* Create multiple projects with **custom names**.
* Customize each project’s appearance:

  * Background color
  * Text color
  * Border / outline color
* Projects can be **deleted** when no longer needed.

Projects serve as containers for modules and allow different Arduino setups or ideas to be managed independently.

---

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

---

### Arduino Integration

* The serial communication has currently been **tested only with an Arduino Uno (R1)**.
* Other Arduino boards may work as well, but have **not yet been explicitly tested**.
* An example Arduino Sketch is provided in the `arduino/` folder.
* The Java application sends **structured data (type + value)** to the Arduino.
* The Arduino processes incoming data based on the data type defined in the module.

This separation makes it easy to adapt the Arduino code to custom module types or behaviors.

---

### Planned Features

* A **console window** to display `Serial.print()` output from the Arduino directly in the app.
* Improved **serial port configuration** (no hardcoded port).
* Module management improvements (e.g. deleting or reordering modules).

---

## Usage

1. **Connect the Arduino** to your computer via USB.
2. **Launch the JavaFX application**.

   * The app will automatically attempt to connect to **COM9**.
   * If no Arduino is detected, use **"Verbindung aufbauen"** to select the correct port manually.
3. **Create a new project**:

   * Choose a project name and colors.
4. **Add modules** to a project:

   * Select a project by clicking on it (the outline color indicates the active project).
   * Configure module name, colors, and data type.
5. **Send data**:

   * Module data is sent to the Arduino via serial communication.
6. **Delete projects** when they are no longer needed.

---

## Notes & Limitations

* Only **adding modules** is currently supported; deleting individual modules is not implemented yet.
* The Arduino sketch must handle the **same data types** that are configured in the app.
* The **serial console** feature is planned but not yet available.
* The application UI is currently **German only**.
* The app **tries COM9 first** on startup; manual selection is required if another port is used.
* Modules contain an **"Einstellungen"** button, which will in further updates give the possibility to configure alreadyy added modules.

---

## Status

This project is actively used as a **learning project** and will evolve over time as new features are implemented and the architecture is refined.
