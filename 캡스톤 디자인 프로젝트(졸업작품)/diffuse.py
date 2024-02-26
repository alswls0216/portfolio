import boto3
import json
import time
import schedule
import RPi.GPIO as GPIO
import s3download as down

mood = ''

def diffuse(result):
    
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(17, GPIO.OUT)
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(27, GPIO.OUT)
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(22, GPIO.OUT)

    
    if result == "happy" or "happy*": 
        GPIO.output(17, 1)
        time.sleep(5)
        GPIO.output(17, 0)

    elif result == "sad" or "sad*": 
        GPIO.output(27, 1)
        time.sleep(5)
        GPIO.output(27, 0)
        
    elif result == "angry" or "angry*": 
        GPIO.output(22, 1)
        time.sleep(5)
        GPIO.output(22, 0)
 
    elif result == "disgust" or "disgust*": 
        GPIO.output(17, 1)
        GPIO.output(27, 1)
        time.sleep(5)
        GPIO.output(17, 0)
        GPIO.output(27, 0)
        
    elif result == "fear" or "fear*": 
        GPIO.output(22, 1)
        GPIO.output(27, 1)
        time.sleep(5)
        GPIO.output(22, 0)
        GPIO.output(27, 0)
    
    elif result == "surprised" or "surprised*": 
        GPIO.output(22, 1)
        GPIO.output(17, 1)
        time.sleep(5)
        GPIO.output(22, 0)
        GPIO.output(17, 0)
        
    GPIO.cleanup()


def downmood():
    global mood
    latestmood = down.download()
    if mood != latestmood: #두 값이 다르면
        #diffuse(latestmood) #향 분사 코드
        mood = latestmood
        print("ok")
    else:
        print("no")
        


if __name__ == "__main__":
    schedule.every(3).seconds.do(downmood)
    while True:
        schedule.run_pending()
        #time.sleep(1)