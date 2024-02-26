import schedule
import time
import boto3

def upload_bin():
    
    filename = '/home/pi/output-t.json'
    bucket = 'babotest1'
    key = 'output-t.json'

    s3 = boto3.client('s3', 
                      region_name="ap-northeast-2",  
                      aws_access_key_id='AKIATFXBTZ2AKZWYNPPN', 
                      aws_secret_access_key='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')
    s3.upload_file(filename, bucket, key)

#schedule.every().hours.at(":00").do(upload_bin)

if __name__ == "__main__":
    schedule.every().day.at("00:00").do(upload_bin)
    schedule.every().day.at("01:00").do(upload_bin)
    schedule.every().day.at("02:00").do(upload_bin)
    schedule.every().day.at("03:00").do(upload_bin)
    schedule.every().day.at("05:00").do(upload_bin)
    schedule.every().day.at("06:00").do(upload_bin)
    schedule.every().day.at("07:00").do(upload_bin)
    schedule.every().day.at("08:00").do(upload_bin)
    schedule.every().day.at("09:00").do(upload_bin)
    schedule.every().day.at("10:00").do(upload_bin)
    schedule.every().day.at("11:00").do(upload_bin)
    schedule.every().day.at("12:00").do(upload_bin)
    schedule.every().day.at("13:00").do(upload_bin)
    schedule.every().day.at("14:00").do(upload_bin)
    schedule.every().day.at("15:00").do(upload_bin)
    schedule.every().day.at("16:00").do(upload_bin)
    schedule.every().day.at("17:00").do(upload_bin)
    schedule.every().day.at("18:00").do(upload_bin)
    schedule.every().day.at("19:00").do(upload_bin)
    schedule.every().day.at("20:00").do(upload_bin)
    schedule.every().day.at("21:00").do(upload_bin)
    schedule.every().day.at("22:00").do(upload_bin)
    schedule.every().day.at("23:00").do(upload_bin)
    #schedule.every().day.at("15:38").do(upload_bin)
    
    while True:
        schedule.run_pending()
        #print("bin uploaded")
        time.sleep(1)
