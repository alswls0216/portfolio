#ggsK가 안 되길래 만든거
import os
from google.cloud import speech
import json
import upload_gs as gs
from collections import OrderedDict
from micstream import MicrophoneStream

os.environ["GOOGLE_APPLICATION_CREDENTIALS"] = \
"moodi-322006-c13e2a44d83e.json"

# Audio recording parameters
RATE =44100
CHUNK =int(RATE /10)  # 100ms

def listen_print_loop(responses): 

    for response in responses:
        result = response.results[0]
        transcript = result.alternatives[0].transcript
        
        gs.upload2(transcript)
        print(transcript)

        if u'종료'in transcript or u'그만'in transcript:
            print('종료합니다..')
            break
        if u'예지'in transcript or u'예지야'in transcript:
            gs.upload(transcript)

language_code ='ko-KR'  # a BCP-47 language tag

client = speech.SpeechClient()
config = speech.RecognitionConfig(
	encoding =speech.RecognitionConfig.AudioEncoding.LINEAR16,
	sample_rate_hertz =RATE,
	language_code =language_code)
streaming_config = speech.StreamingRecognitionConfig(config =config)

with MicrophoneStream(RATE, CHUNK) as stream:
	audio_generator = stream.generator()
	requests = (speech.StreamingRecognizeRequest(audio_content =content)
				for content in audio_generator)
	responses = client.streaming_recognize(streaming_config, requests)
	
	listen_print_loop(responses)