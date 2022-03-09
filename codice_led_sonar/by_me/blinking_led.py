#variante che fa blinkare il led
import RPi.GPIO as GPIO
import time
import sys

GPIO.setmode(GPIO.BCM)
GPIO.setup(25,GPIO.OUT)

for line in sys.stdin:
   print(line)
   try:
           v = float(line)
           if v <= 10 :
              GPIO.output(25,GPIO.HIGH)
              time.sleep(0.01)
              GPIO.output(25,GPIO.LOW)
           else:
              GPIO.output(25,GPIO.LOW)
   except:
                print("An exception occurred")
