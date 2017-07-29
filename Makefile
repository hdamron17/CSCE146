SUB_MAKEFILE=SubMakefile
DEFAULT_MAIN=default-Main.java
WEBPAGE="https://cse.sc.edu/~shephejj/csce146/"

pkg=hello_world

default:
	@make -s compilepkg
	@make -s runpkg
	@make -s cleanpkg

all:
	@make -s compile
	@make -s run
	@make -s clean

_*:
	@make -s pkg=${shell echo `expr "$@" : "^[_]\(.*\)"`}

compile:
	@make -s loop cmd=compile premsg="> Compiling "

run:
	@make -s loop cmd=run premsg="> Running "

clean:
	@make -s loop cmd=clean premsg="> Cleaning "

compilepkg:
	@make -s single cmd=compile premsg="> Compiling "

runpkg:
	@make -s single cmd=run premsg="> Running "

cleanpkg:
	@make -s single cmd=clean premsg="> Cleaning "

single: backup
	@if [ $(pkg) ]; then \
	 if [ $(cmd) ]; then \
	  echo "$(premsg) _$(pkg)"; \
	  cp $(SUB_MAKEFILE) _$(pkg)/Makefile; \
	  cd _$(pkg); \
	  make $(cmd); \
	  rm Makefile; \
	  cd ..; \
	 else echo "Error: No command provided"; \
	 fi; \
	else echo "Error: No package provided"; \
	fi

loop: backup
	@if [ $(cmd) ]; then \
	 for dir in _*; do \
	  echo "$(premsg) $$dir"; \
	  cp $(SUB_MAKEFILE) $$dir/Makefile; \
	  cd $$dir; \
	  make $(cmd); \
	  rm Makefile; \
	  cd ..; \
	  done; \
	else echo "Error: no command provided"; \
	fi

backup:
	@cp Makefile .TempMakefile

package:
	@if [ $(pkg) ]; then \
	 echo "> Creating package $(pkg)"; \
	 mkdir -p "_$(pkg)/src"; \
	 mkdir -p "_$(pkg)/bin"; \
	 cp $(DEFAULT_MAIN) _$(pkg)/src/Main.java; \
	else echo "Error: no package provided"; \
	fi

webpage:
	@x-www-browser $(WEBPAGE)

.PHONY: default all _* compile run clean compilepkg runpkg cleanpkg webpage
