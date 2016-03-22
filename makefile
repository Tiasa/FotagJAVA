default: ; @echo "Compiling..." ; javac -cp Fotag.jar *.java


run: default ; java -cp "Fotag.jar:." Fotag

clean: ; rm -f *.class
