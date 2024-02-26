import boto3
import json

def download():

    #txt = '' 화면에 띄우기용
    file_name = '/home/pi/result.json'
    bucket = 'babotest1'
    key = 'result.json'

    s3 = boto3.client('s3', 
                      region_name="ap-northeast-2", 
                      aws_access_key_id='AKIATFXBTZ2AKZWYNPPN', 
                      aws_secret_access_key='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')
    s3.download_file(bucket, key, file_name)

    with open('/home/pi/result.json', 'r') as f:
        result = json.load(f)
        #txt = result 화면에 띄우기용
        
    #print(result['txt']) 화면에 띄우기용
    return result['txt']
 
 
def download_time():

    file_name = '/home/pi/time.json'
    bucket = 'babotest1'
    key = 'time.json'

    s3 = boto3.client('s3', 
                      region_name="ap-northeast-2", 
                      # endpoint_url=S3_LOCATION, 
                      aws_access_key_id='AKIATFXBTZ2AKZWYNPPN', 
                      aws_secret_access_key='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')
    s3.download_file(bucket, key, file_name)

    with open('/home/pi/time.json', 'r') as f:
        time = json.load(f)
        t = [time['a'], time['b'], time['c'], time['d']]
        
    print(t)
    return t
    
#download()   
#download_time()
