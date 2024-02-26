#-*- coding:utf-8 -*-
# module 2, scent 3
import json
import s3download as down
import RPi.GPIO as GPIO
import time
import schedule

def diffuse():
   
    result = down.download()
    print(result)
    #result = input()
    #result = 'happy'
    
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(17, GPIO.OUT)
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(27, GPIO.OUT)
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(22, GPIO.OUT)

    
    if result == "happy": 
        GPIO.output(17, 1)
        time.sleep(5)
        GPIO.output(17, 0)

    elif result == "sad": 
        GPIO.output(27, 1)
        time.sleep(5)
        GPIO.output(27, 0)
        
    elif result == "angry": 
        GPIO.output(22, 1)
        time.sleep(5)
        GPIO.output(22, 0)
 
    elif result == "disgust": 
        GPIO.output(17, 1)
        GPIO.output(27, 1)
        time.sleep(5)
        GPIO.output(17, 0)
        GPIO.output(27, 0)
        
    elif result == "fear": 
        GPIO.output(22, 1)
        GPIO.output(27, 1)
        time.sleep(5)
        GPIO.output(22, 0)
        GPIO.output(27, 0)
    
    elif result == "surprised": 
        GPIO.output(22, 1)
        GPIO.output(17, 1)
        time.sleep(5)
        GPIO.output(22, 0)
        GPIO.output(17, 0)
        
    GPIO.cleanup()

    
#schedule.every().day.at("23:11").do(diffuse()) 
#diffuse()