#include <SPI.h>
#include <MFRC522.h>
#include <ESP8266WiFi.h>
#include <WiFiClientSecure.h>

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

const char* ssid = "robo";
const char* password = "airoairo";
const char* host = "172.31.98.237";
const int httpPort = 80;

#define RST_PIN         D3
#define SS_PIN          D8
MFRC522 mfrc522(SS_PIN, RST_PIN);

void setup() {

  Serial.begin(115200);
  Serial.println();

  SPI.begin();           // Init SPI bus
  mfrc522.PCD_Init();    // Init MFRC522

  
  //http example
  Serial.print("connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  // Use WiFiClientSecure class to create TLS connection
  WiFiClientSecure client;
  Serial.print("connecting to ");
  Serial.println(host);
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }

  if (client.verify(fingerprint, host)) {
    Serial.println("certificate matches");
  } else {
    Serial.println("certificate doesn't match");
  }

  String url = "/log";
  Serial.print("requesting URL: ");
  Serial.println(url);

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

  Serial.print(F("Card UID: "));
  //dump_byte_array(mfrc522.uid.uidByte, mfrc522.uid.size);
  String name = getName(mfrc522.uid.uidByte, mfrc522.uid.size);
  Serial.println(name);

  //send data to server
  String messagebody = "{\"deviceId\": \""+name+"\"}\r\n";

  client.print(String("POST ") + url + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" +
               "Content-Type: application/json\r\n" +
               "User-Agent: diymedtrack\r\n" +
               "Connection: close\r\n\r\n" +
               "Content-Length: " +  String(messagebody.length()) + "\r\n\r\n");
  //do post            
  client.print(messagebody);
  
  

  /**
   * 
   * Extra: leds: every 15 min check serv, if need show unused (blink led 5-1s)
   * Buttons: any time press good or bad
   */
  
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




