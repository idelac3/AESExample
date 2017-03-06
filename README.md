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

## Installation example

<a href="http://www.youtube.com/watch?feature=player_embedded&v=F_qGHkNohAc
" target="_blank"><img src="http://img.youtube.com/vi/F_qGHkNohAc/0.jpg" 
alt="Installation Example" width="240" height="180" border="10" /></a>
<BR>
Youtube video shows how to install AES into home folder.

## Thunar integration example

<a href="http://www.youtube.com/watch?feature=player_embedded&v=Yp0JzcDz_Y78
" target="_blank"><img src="http://img.youtube.com/vi/p0JzcDz_Y78/0.jpg" 
alt="Xfce Thunar integration Example" width="240" height="180" border="10" /></a>
<BR>
Youtube video shows how to integrate AES into Thunar File Manager.

## Author

![aes](aes.gif)