#include <Arduino.h>

/*
Example code to show how data from the Arduino-Communicator Java application
can be processed for the Arduino
*/

String datatypes[] = {"RGB", "SWITCH", "TEXT"}; //The Datatypes that you set in the project-modules

String input = "";
bool isConnected = false;
bool isRgbActive = false;

int rPin = 3;
int gPin = 6;
int bPin = 9;

int lastRValue;
int lastGValue;
int lastBValue;

void setup() {
  Serial.begin(9600);
  delay(1500); // Zeit für den Reset

  pinMode(rPin, OUTPUT);
  pinMode(gPin, OUTPUT);
  pinMode(bPin, OUTPUT);

  Serial.println("ARDUINO_READY"); // Signal Java that Arduino is ready
}

// ====================== Hilfsmethoden ======================
String getDataType(String s) {
  int colonIndex = s.indexOf(':');
  if (colonIndex == -1) return "";
  return s.substring(0, colonIndex);
}

String getPayload(String s) {
  int colonIndex = s.indexOf(':');
  if (colonIndex == -1) return "";
  return s.substring(colonIndex + 1);
}

// ====================== Handler ======================
void handleRGB(String payload) {
  //Write your RGB code here
}

void handleSwitch(String payload) {
  //Write your switch code here, for example:
  isRgbActive = payload.toInt() != 0;
}

void handleText(String payload) {
  //write your text processing code here
}

// ====================== Main-Loop ======================
void loop() {
  if (Serial.available()) {
    input = Serial.readStringUntil('\n');
    input.trim();

    if (!isConnected) {
      if (input == "HELLO") {
        Serial.println("ARDUINO_READY");
        isConnected = true;
      }
    } else {
      String datatype = getDataType(input);
      String payload = getPayload(input);

      if (datatype == datatypes[0]) {
        handleRGB(payload);
      } else if (datatype == datatypes[1]) {
        handleSwitch(payload);
      } else if (datatype == datatypes[2]) {
        handleText(payload);
      }
    }
  }
}