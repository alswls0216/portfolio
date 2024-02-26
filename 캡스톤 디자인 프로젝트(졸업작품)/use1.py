import pyaudio
import wave
import time
import upload as up
import transcription as tr

SAMPLE_RATE = 44100
FORMAT = pyaudio.paInt16
CHANNELS = 1
CHUNK = 512
RECORD_SECONDS = 5
WAVE_OUTPUT_FILENAME = "output.wav"

p = pyaudio.PyAudio()
print("Start to record the audio")
stream = p.open(format = FORMAT,
                channels = CHANNELS,
                rate = SAMPLE_RATE,
                input = True,
                frames_per_buffer = CHUNK)                   
frames = []
for i in range(0, int(SAMPLE_RATE/CHUNK * RECORD_SECONDS)):
	data = stream.read(CHUNK)
	frames.append(data)
	
print("Recording is finished")

stream.stop_stream()
stream.close()
p.terminate()

wf = wave.open(WAVE_OUTPUT_FILENAME, 'wb')
wf.setnchannels(CHANNELS)
wf.setsampwidth(p.get_sample_size(FORMAT))
wf.setframerate(SAMPLE_RATE)
wf.writeframes(b''.join(frames))
wf.close()

up.upload_a()
tr.transcribe()
'''
import watermodule as wm
time.sleep(20)
wm.diffuse()
'''