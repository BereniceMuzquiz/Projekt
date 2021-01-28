4 Modelle Maussteuerung mit OpenFace:

Steuerung durch Kopfwewegung:
File Name: head-pitch-roll-controlMouse-openface.acs
OpenFace Einstellungen:
Webcam 1280 x 720
Record: AUs
        pose      
Funktionsweise     :
Mausbewegung: Pitch, Roll
Klick-links: lippen spannen AU_23_r
Doppelklick: Mund öffnen AU_26
Klickrechts: lippen saugen AU_28

Steuerung  durch Position der Nase:
File Name: landmarks_AU_controlMouse.acs
OpenFace Einstellungen:
Webcam 1280 x 720
Record: AUs
        2D-Landmarks      
Funktionsweise     :
Mausbewegung: Änderung der Position der Nase in Bezug auf den Bildschirm
Klick-links: Mund öffnen AU25_c AU26_c
Doppelklick: Mund öffnen AU25_c AU26_c  (2Sek)
Klickrechts: Blinken(Augen zu) AU45_c (2 Sek)

Steuerung  durch Blickrichtug (rechts-links):
File Name:  gaze-AUs-lmk-controlMouse-openFace.acs
OpenFace Einstellungen:
Webcam 1280 x 720
Record: AUs
        2D-Landmarks 
        gaze
Funktionsweise :
Mausbewegung: Blickrichtung rechts-links
Klick-links: Heben der Augenbrauen
Wichtig: um dem Kopf zu zentrieren soll die Wert von Face Centered nahe 0 sein (position der nase in der Mitte des Bildschirms)

Steuerung  Midiplayer durch Blickrichtug (rechts-links):
File Name:  midi_gaze_lmk_AU_openFace.acs
OpenFace Einstellungen:
Webcam 1280 x 720
Record: AUs
        2D-Landmarks 
        gaze
Funktionsweise :
Mausbewegung: Blickrichtung rechts-links
Töne erzeugen: Mund öffnen
Klick-links: Heben der Augenbrauen
Wichtig: um dem Kopf zu zentrieren soll die Wert von Face Centered nahe 0 sein(position der nase in der Mitte des Bildschirms)
