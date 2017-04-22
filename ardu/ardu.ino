#include <SPI.h>
#include <MFRC522.h>
#include <ESP8266WiFi.h>
#include <WiFiClientSecure.h>
#include <ESP8266HTTPClient.h>


#define D0 16
#define D1 5 // I2C Bus SCL (clock)
#define D2 4 // I2C Bus SDA (data)
#define D3 0
#define D4 2 // Same as "LED_BUILTIN", but inverted logic
#define D5 14 // SPI Bus SCK (clock)
#define D6 12 // SPI Bus MISO 
#define D7 13 // SPI Bus MOSI
#define D8 15 // SPI Bus SS (CS)
#define D9 3 // RX0 (Serial console)
#define D10 1 // TX0 (Serial console)

//const char* ssid = "robo";
//const char* password = "airoairo";
//const char* host = "172.31.98.237";

String url = "http://192.168.1.107:8080/log";

#define RST_PIN         D3
#define SS_PIN          D8
MFRC522 mfrc522(SS_PIN, RST_PIN);

void setup() {

  Serial.begin(115200);
  Serial.println();

  SPI.begin();           // Init SPI bus
  mfrc522.PCD_Init();    // Init MFRC522

  
  //connect to wifi
  Serial.println("connecting to " +String(ssid));
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(2000);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  //NFC setup
  SPI.begin();      // Init SPI bus
  mfrc522.PCD_Init();   // Init MFRC522
  mfrc522.PCD_DumpVersionToSerial();  // Show details of PCD - MFRC522 Card Reader details
  Serial.println(F("Scan PICC to see UID, SAK, type, and data blocks..."));
}

void loop() {

// Look for new cards
  if ( ! mfrc522.PICC_IsNewCardPresent()) {
    return;
  }
  
  // Select one of the cards
  if ( ! mfrc522.PICC_ReadCardSerial()) {
    return;
  }

  //get card Id
  Serial.print(F("Card UID: "));
  //dump_byte_array(mfrc522.uid.uidByte, mfrc522.uid.size);
  String cardId = getName(mfrc522.uid.uidByte, mfrc522.uid.size);
  Serial.println(cardId);

  String response = sendLog(cardId);
  //todo return if responce is empty

  /**
   * 
   * Extra: leds: every 15 min check serv, if need show unused (blink led 5-1s)
   * when scanned: blink green 1-1
   * when sent to server: green led on for 10 sec
   * when not sent: red led on for 15 sec
   * 
   * 
   * Buttons: any time press good or bad
   * 5 buttons: very bad, bad, normal, good, verygood (or just 2: good/bad)
   * 
   * 
   */
  
}

/**
 * return http code
 */
String sendLog(String cardId) {

  //send data to server
  String messageBody = "{\"deviceId\": \""+cardId+"\"}\r\n";   

  HTTPClient http;
  http.begin(url);      //Specify request destination
  http.addHeader("Content-Type", "application/json");  //Specify content-type header

  int httpCode = http.POST(messageBody);   //Send the request
  String payload = http.getString();                                        //Get the response payload

  Serial.println(httpCode);   //Print HTTP return code
  Serial.println(payload);    //Print request response payload

  http.end();  //Close connection

  return httpCode;
}

/**
 * Helper routine to dump a byte array as hex values to Serial.
 */
String getName(byte *buffer, byte bufferSize) {
    String name = "";
    for (byte i = 0; i < bufferSize; i++) {
        name += buffer[i] < 0x10 ? "0" : "";
        name += buffer[i];
    }
    return name;
}




