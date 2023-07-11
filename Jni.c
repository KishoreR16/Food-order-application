#include<stdio.h>
#include<stdlib.h>
#include <jni.h>
#include "Jni.h"
#include <conio.h>



JNIEXPORT void JNICALL Java_Jni_Clean
  (JNIEnv *, jclass){
	  system("cls");

  }