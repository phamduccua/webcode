#!/bin/bash

lang=$1
RTE=0
CE=0

memArr=(3500 7500 95000 19000)
initMem=0

if [ "$lang" = "c" ]; then
    initMem=${memArr[0]}
    if gcc -o solution solution.c &> "$2"; then
        if { cat testcase.txt | /usr/bin/time -f "%e %M" -o "$3" timeout "$4"s ./solution &> "$2"; } || { RTE=1; }; then
            :
        fi
    else
        CE=1
    fi
elif [ "$lang" = "cpp" ]; then
    initMem=${memArr[1]}
    if g++ -o solution solution.cpp &> "$2"; then
        if { cat testcase.txt | /usr/bin/time -f "%e %M" -o "$3" timeout "$4"s ./solution &> "$2"; } || { RTE=1; }; then
            :
        fi
    else
        CE=1
    fi
elif [ "$lang" = "java" ]; then
    initMem=${memArr[2]}
    if javac solution.java &> "$2"; then
        if { cat testcase.txt | /usr/bin/time -f "%e %M" -o "$3" timeout "$4"s java solution &> "$2"; } || { RTE=1; }; then
            :
        fi
    else
        CE=1
    fi
elif [ "$lang" = "py" ]; then
    initMem=${memArr[3]}
    if { cat testcase.txt | /usr/bin/time -f "%e %M" -o "$3" timeout "$4"s python3 solution.py &> "$2"; } || { RTE=1; }; then
        :
    fi
fi

if [ "$CE" -ne 0 ]; then
    echo "COMPILATION ERROR" >> "$2"
fi

if [ "$RTE" -ne 0 ]; then
    echo -e "RUNTIME ERROR" >> "$2"
fi
# shellcheck disable=SC2154
echo -e "\n$time" >> "$3"
# shellcheck disable=SC2154
echo "$memory" >> "$3"