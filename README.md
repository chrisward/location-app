# Location App - by Chris Ward
This app gives you basic up-to-date info about your GPS and Network location services.

## Configuration
Cloning this repository should give you all you need, but you should amend the API Key entry in AndroidManifest.xml to include your own Android Maps SDK API key for the map to work.

## What are location services?
Your Android device uses a series of services to determine your most accurate location, these are:

### GPS
Your device works out your location via GPS through the speed of the signal journey to and from various satelites which will triangulate your position. This is quite accurate, but not great indoors.

### Network
Google's Location Services uses WiFi access points, cellular towers, etc. and their subsequent locations to triangulate your device's position.
