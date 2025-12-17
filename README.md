# Arduino Communicator App

This JavaFX application allows you to communicate with an Arduino board via serial communication. It provides a project-based interface where you can create and manage projects, each containing different modules that send data to the Arduino.

---

## Features

### Serial Communication
- The app **automatically tries to connect to the Arduino on COM9** at startup.
- If no Arduino is found on COM9, the connection must be **manually established** later.
- You can manually set the port using the **"Verbindung aufbauen"** button in the app.

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
   - The app will automatically try to connect to **COM9**.
   - If no Arduino is detected on COM9, use the **"Verbindung aufbauen"** button to set the correct port manually.
3. **Create a new project**:
   - Set a custom name and choose colors.
4. **Add modules** to the project:
   - Select the project you want to add  a module to by clicking on it (activates your selected outline color around the box)
   - Assign names, colors, and a data type for each module.
6. **Send data**:
   - The app sends the module data to the Arduino for processing.
7. **Delete projects** if needed.

---

## Notes
- The app currently supports only **adding modules**; deleting individual modules is not yet implemented.
- Make sure to match the **data types** in your Arduino code with the module configuration in the app.
- The **console for Serial output** is planned but not yet available.
- The app **tries COM9 first** on startup. If unavailable, a manual connection is required.
- The UI is currently only available in German language

---

## Folder Structure
