# AESExample

AES example in Java. In this example <B>AES/OFB8/PKCS5Padding</B> is used as transformation. IV parameters are derived from key as first 16 bytes. Actual key is generated as <B>SHA-1 digest</B> from secret to encrypt or decrypt. See <I>AES.java</I> source code. 

Download source files *.java with:
<PRE>
git clone https://github.com/idelac3/AESExample.git
</PRE>
Compile with:
<PRE>
 cd src
 javac AES.java
</PRE>
Start with:
<PRE>
 java AES [ encrypt | decrypt ] inputFile outputFile secret
</PRE>
Example:
<PRE>
 java AES encrypt /home/john/report.txt /tmp/report.txt.c mysecret123 
</PRE>
If you want GUI window to appear, just enter first two arguments: mode and input file. Main window should appear on center of screen asking for secret.

![aes](aes.gif)