
#define play 3
#define pause 4
#define reset 7

char str;
String fnal;
bool i;

void setup()
{
  Serial.begin(9600);
  fnal="";
  i=false;
  pinMode(3,INPUT_PULLUP);
  pinMode(4,INPUT_PULLUP);
  pinMode(7,INPUT_PULLUP);
   
}

void loop()
{
  if(digitalRead(7)==LOW)
  i=false;
 
if(digitalRead(3)==LOW && i==false)
{
  play_music();

}

if(digitalRead(4)==LOW && i==false)
{
  pause_music();
}

}

 void play_music()
{
  Serial.print('#');
 
  Serial.print('1');
 
  Serial.print('1');

  Serial.print('~');
 
  delay(100);
  i=true;
}

 void pause_music()
{
  Serial.print('#');
 
  Serial.print('1');
 
  Serial.print('2');

  Serial.print('~');
 
  delay(100);
  i=true;
}
