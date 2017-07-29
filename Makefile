SUB_MAKEFILE=SubMakefile

default:
	@if [ $(pkg) ]; then \
	 make -s compilepkg; \
	 make -s runpkg; \
	 make -s cleanpkg; \
	else \
	 make -s compile; \
	 make -s run; \
	 make -s clean; \
	fi

.PHONY: _*

_*:
	@make -s pkg=${shell echo `expr "$@" : "^[_]\(.*\)"`}

compile:
	@make -s loop cmd=compile premsg="> Compiling "

run:
	@make -s loop cmd=run premsg="> Running "

clean:
	@make -s loop cmd=clean premsg="> Cleaning "

compilepkg:
	@make -s single cmd=compile

runpkg:
	@make -s single cmd=run

cleanpkg:
	@make -s single cmd=clean

single: backup
	if [ $(pkg) ]; then \
	 if [ $(cmd) ]; then \
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
	if [ $(cmd) ]; then \
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
	cp Makefile .TempMakefile
