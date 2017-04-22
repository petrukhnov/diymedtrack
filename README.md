# diymedtrack

DIY medication tracker (Ultrahack 2017 project)

# Problem aka WHY

When inhaler user come to doctor, he may not remember how often he used device. 

# Solution

Simple DIY tracking could provide exact data, and show trends.

E.g. User was supposed to use inhaler twice a day, but lsat 2 month he skipped half of mornings. Doctor may adjust dose and when medication should be taken based on that data.

# How

User add NFC sticker to inhaler. Each time when he use, he scan it with IoT device (arduino based). Data is stored on server, and could be retreived or analyzed.
Patien completely owns the data.

See concept.png

# Possible pivots

Adding 2 leds, could add reminder to take medicine (based on analyzed data).

Adding few buttons (very bad, bad, normal, good, very good) will allow to track patient condition and map it to inhaler use. This could provide valuable insight to doctors and facilitate discussion.

Kids could be more tempted to use medication properly, as it provide feedback, and they could build system themselves.



# Original description from Ultrahack

DIY medication tracker

Patients themselves are responsible for using inhalers. There are no way to force them, especially kids. With modern technologies we could inspire and assist them. 
Aim of this project is to make inexpensive DIY tools available to consumers to enhance their treatment. 
One way would be to make inhaler usage tracking system based on nfc tag/reader and UI that show nice charts. Opensource API, possibility of self hosted/DIY. Plan to use NFC and d3.js Visualizing data may help patients and doctor to discover anomalies in usage and find why it was not used in certain days. (Analytics done right)
In return patients could share data about usages for society benefit on-demand basis.

Technologies (draft)
 * NFC (no need to modify devices)
 * arduino/raspberyPi/smartphone code as NFC reader
 * d3.js for visualization
 * java (or something else) for backend

Possible pivots based on team skills:
 * aimed for kids, showing them their usage and nice charts, maybe show when others used it, to remove fears
 * aimed for elderly people, include notifications, alerts, etc
 * what else?

