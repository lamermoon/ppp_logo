# Raspberry Pi & WS2812 LED-Strips
Anleitung gefunden auf [https://dordnung.de/raspberrypi-ledstrip/ws2812](https://dordnung.de/raspberrypi-ledstrip/ws2812).

Die WS281X Bibliothek benötigt Pakete, die mit
```bash
user:~$ sudo apt-get install build-essential python-dev python-pip unzip wget scons swig
```
installiert werden.
Anschließend die Lib mittels
```bash
user:~$ wget https://github.com/jgarff/rpi_ws281x/archive/master.zip
user:~$ unzip master.zip
user:~$ cd rpi_ws281x-master
user:~$ sudo scons
user:~$ sudo pip install rpi_ws281x
```
herunterladen und installieren.

# Bluetooth
Anleitung auf [http://it-in-der-hosentasche.blogspot.com/2014/03/bluetooth-zwischen-raspberry-pi-und.html](http://it-in-der-hosentasche.blogspot.com/2014/03/bluetooth-zwischen-raspberry-pi-und.html),
