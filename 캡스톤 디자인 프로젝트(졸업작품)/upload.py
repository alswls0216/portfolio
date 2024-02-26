import boto3

def upload_a():
  
    filename = '/home/pi/output.wav'
    bucket = 'babotest1'
    key = 'output.wav'

    s3 = boto3.client('s3', 
                      region_name="ap-northeast-2",  
                      aws_access_key_id='AKIATFXBTZ2AKZWYNPPN', 
                      aws_secret_access_key='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')
    s3.upload_file(filename, bucket, key)
