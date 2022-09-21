# spring-boot Coworking-space 

API documentation: http://localhost:8080/swagger-ui/index.html
(wichtig Link funktioniert nur wen die Applikation läuft)

Wichtig! Bevor man das Projekt startet, muss man im application.yml die Data source url anpassen.
Hier die Url die sie anpassen müssen:   
datasource: <br>
url: jdbc:h2:file:C:\Users\max.ludwig\Documents\ukabschluss\exampledb

Testdaten: Die Testdaten für dieses Projekt werden nach dem Starten automatisch erstellt.
Sollten sie sich die Daten anschauen wollen, befinden sie sich im data.sql, im resources Ordner. 

Anforderungen: <br>
Vorgegeben war, das sich die User mit E-Mail und Passwort anmelden.
In meinem Projekt gibt es nur einen Username, in diesen man E-Mail wie auch einen einfachen Username einfüllen kann.  

Client Requests: <br>
Um die Client Request ausführen zu könne braucht man ein gültiges Token(bzw 2). 
Diesen kann man sich über den Link der API doku holen.(wichtig Link funktioniert nur wen die Applikation läuft)
Hier zu braucht man die zwei usernames: admin und mitglied. Das Passwort ist bei beiden: password1234.
Logischerweise ist das Token, das mit dem Username admin generiert worden ist, für das Admin Token.
Danach Tokens abfüllen, filename: http-client.env.json (befindet sich auf der Höhe des src Ordners, im http-request Ordner)

Projekt starten: <br>
Um das Projekt zu starten, muss man in der RunConfiguration CrudExampleApplication auswählen.(Links neben dem start button)



