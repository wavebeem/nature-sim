all:
	javac *.java

clean:
	rm -f *.class

jar:
	jar -cmf manifest.txt nature-sim.jar *.class

run: jar
	java -jar nature-sim.jar
