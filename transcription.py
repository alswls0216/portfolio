from __future__ import print_function
from urllib import request
from ast import literal_eval
from collections import OrderedDict

import time
import boto3
import json


def transcribe():

    file_name = '/home/pi/output.wav'
    bucket = 'babotest1'
    key = 'output.wav'
    
    transcribe = boto3.client('transcribe', 
                          region_name="ap-northeast-2", 
                          # endpoint_url=S3_LOCATION, 
                          aws_access_key_id='AKIATFXBTZ2AKZWYNPPN', 
                          aws_secret_access_key='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')
    job_name = "t1"
    job_uri = "https://babotest1.s3.ap-northeast-2.amazonaws.com/output.wav".format(bucket,file_name)
    transcribe.start_transcription_job(
        TranscriptionJobName=job_name,
        Media={'MediaFileUri': job_uri},
        MediaFormat='wav',
        LanguageCode='ko-KR'
        )
        
    while True:
        status = transcribe.get_transcription_job(TranscriptionJobName=job_name)
        if status['TranscriptionJob']['TranscriptionJobStatus'] in ['COMPLETED', 'FAILED']:
            save_json_uri = status['TranscriptionJob']['Transcript']['TranscriptFileUri']
            break
        print("Not ready yet...")
        time.sleep(1)
    time.sleep(1)
    save_json_uri = status['TranscriptionJob']['Transcript']['TranscriptFileUri'] 

    '''
    txtfile = save_json_uri
    bucket = 'babotest1'
    key = 'output-t.json'
    s3 = boto3.client('s3', 
                      region_name="ap-northeast-2", 
                      # endpoint_url=S3_LOCATION, 
                      aws_access_key_id='AKIATFXBTZ2AKZWYNPPN', 
                      aws_secret_access_key='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')
    s3.upload_file(file_name, bucket, key)
    ''' 
    #웹서버 결과 파이썬으로 불러오기
    load = request.urlopen(save_json_uri)
    confirm = load.status
    rst = load.read().decode('utf-8')

    # 문자열을 딕셔너리로 변환 후 결과 가져오기
    data = OrderedDict()
    data['txt'] = literal_eval(rst)['results']['transcripts'][0]['transcript'] 
    txtfile = json.dumps(data, ensure_ascii=False, indent='\t').encode('UTF-8')
    bucket = 'babotest1'
    key = 'output-t.json'
    s3 = boto3.client('s3', 
                      region_name="ap-northeast-2", 
                      # endpoint_url=S3_LOCATION, 
                      aws_access_key_id='AKIATFXBTZ2AKZWYNPPN', 
                      aws_secret_access_key='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')
    s3.put_object(Bucket = 'babotest1', Key = key, Body = txtfile)

    print(literal_eval(rst)['results']['transcripts'][0]['transcript'])
    

    #트렌스크라이브 삭제
    del_transcribe =boto3.client('transcribe', region_name="ap-northeast-2", 
                          aws_access_key_id='AKIATFXBTZ2AKZWYNPPN', 
                          aws_secret_access_key='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')
    res = del_transcribe.delete_transcription_job(
        TranscriptionJobName = 't1'
    )
    res['ResponseMetadata']['HTTPStatusCode'] == '200'

