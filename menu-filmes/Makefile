SRC_DIR = src
OUT_DIR = ../out/production/menu-filmes
MAIN_CLASS = Main

JAVA_FILES = $(shell find $(SRC_DIR) -type f -name "*.java")

JAVAC = javac
JAVA = java

all: compile

compile:
	@mkdir -p $(OUT_DIR)
	$(JAVAC) -d $(OUT_DIR) $(JAVA_FILES)

run: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS)

clean:
	rm -rf ../out

re: clean compile

.PHONY: all compile run clean re
