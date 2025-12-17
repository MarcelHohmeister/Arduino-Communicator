# Arduino Communicator App

This JavaFX application allows you to communicate with an Arduino board via serial communication. It provides a project-based interface where you can create and manage projects, each containing different modules that send data to the Arduino.

---

## Features

### Serial Communication
- Connects to an Arduino board over a serial port.
- By default, the app tries to communicate over **COM9** (the port used during development).
- If your Arduino is on a different port, you can manually set the port using the **"Verbindung aufbauen"** button in the app.

### Projects
- Create new projects with **custom names**.
- Customize project appearance: **background color**, **text color**, and **border color**.
- Projects can be deleted if no longer needed.

### Modules
- Add multiple types of modules to a project:
  - **RGB Module**
  - **Text Module**
  - **Switch Module**
- Each module can have a **name** and **custom colors**.
- Each module must be assigned a **data type**, which is sent along with its content to the Arduino for processing.
- ⚠️ Note: Individual modules **cannot be deleted yet**.

### Planned Features
- A **console window** is planned to display `Serial.print` outputs from the Arduino.
- This feature is **not yet implemented**.

### Arduino Integration
- Example Arduino code is provided in the `arduino/` folder.
- Modules send their data and type to the Arduino, which processes it according to the example sketch.

---

## Usage

1. **Connect Arduino** via USB cable to your computer.
2. **Launch the JavaFX App**.
3. **Establish connection**:
   - Click **"Verbindung aufbauen"** to use the default COM port or select a custom port.
4. **Create a new project**:
   - Set a custom name and choose colors.
5. **Add modules** to the project:
   - Assign names, colors, and a data type for each module.
6. **Send data**:
   - The app sends the module data to the Arduino for processing.
7. **Delete projects** if needed.

---

## Notes
- The app currently supports only **adding modules**; deleting individual modules is not yet implemented.
- Make sure to match the **data types** in your Arduino code with the module configuration in the app.
- The **console for Serial output** is planned but not yet available.

---
