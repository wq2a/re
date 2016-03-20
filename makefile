all:	ta

ta:		ta/*.java
		javac -cp lib/model.jar ta/*.java -d .
		java -cp .:lib/model.jar re.ta.TA

clean:
		rm -rf re

.PHONY:	all ta clean
