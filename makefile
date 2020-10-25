JC = javac
.SUFFIXES: .java .class
.java.class:
        $(JC) $*.java

CLASSES = \
        chat.java \
        functions.java \
        Peer.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class
