SHELL = /bin/bash

JC = javac
JFLAGS = -g -Xlint:all -Xdiags:verbose
CP=../../libs/*

FILES =\
	Unity\
	RobotColors\
	Point

JVS = $(addsuffix .java , $(FILES))
CLS = $(addsuffix .class , $(FILES))

.PHONY: all build clean
all: build

build: $(CLS)

%.class: $(wildcard *.java) PurpleSquad.team
	@$(JC) $(JFLAGS) -cp .:$(CP) $(JVS)

clean:
	rm -f $(CLS)
