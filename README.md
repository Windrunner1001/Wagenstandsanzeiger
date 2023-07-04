# Wagenstandsanzeiger

Case Study Wagenstandsanzeiger<br>
Über diesen Webservice lässt sich der Gleisabschnitt eines Wagens an einem bestimmten Bahnhof abrufen.  Über die URL lässt sich eine Zeichenfolge für die Ril100 des Bahnhofs, der Zugnummer sowie der Wagennummer übermitteln.
Der Webservice gibt folgendes zurück:

+ sections: Eine Liste aller Gleisabschnitte, die für den Bahnhof und den Zug & Wagen gefunden wurden. 

Die Anwendung ist mit Java und Spring Boot entwickelt. Der Service ist nach Codeausführung unter folgender URL erreichbar:<br>
http://localhost:8080/station/{ril100}/train/{trainNumber}/waggon/{number}<br>

An der Stelle "ril100" ist es dem Nutzer freigestellt, jede beliebige Zeichenfolge mit Länge 2-5 als Bahnhofabkürzung einzutragen. Zahlen sind nicht zulässig. 
An der Stelle "trainNumber" ist es dem Nutzer freigestellt, jede beliebige positive, zwei- bis vierstellige Zahl als Zugnummer einzutragen. Buchstaben sind nicht zulässig.
An der Stelle "number" ist es dem Nutzer freigestellt, jede beliebige positive, ein- bis zweistellige Zahl als Wagennummer einzutragen. Buchstaben sind nicht zulässig.

Alternativ lässt sich der Service über eine Azurebereitstellung ohne Codeausführung testen:<br>
https://wagenstandsanzeiger.delightfulplant-6edece55.westeurope.azurecontainerapps.io/station/AA/train/2371/waggon/7


