CC=javac
SOURCEPATH=src
CLASSPATH=src/com/cs425/HttpExceptions \
	  src/com/cs425/Logger \
	  src/com/cs425/MiniHttpd \
	  src/com/cs425/Request \
	  src/com/cs425/ResourceServers \
	  src/com/cs425/Session

BUILD=build

SRC=src/ServerRunner.java

all: dir www
	javac -sourcepath $(SOURCEPATH) -classpath "$(CLASSPATH)" -d $(BUILD) $(SRC)

dir:
	test -d build || mkdir build

www:
	cp -rv www build/

clean:
	rm -rf build/*

.PHONY: dir www clean
