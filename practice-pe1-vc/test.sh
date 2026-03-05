#!/usr/bin/env bash
set -euo pipefail

declare -A description1
while IFS= read -r line; do
  i=${line%% *}
  desc=${line#* }
  description1["$i"]="$desc"
done < inputs/TASK1_TESTS

PROG="PE1"

usage() {
  echo "Usage: $0 <task1|task2>"
  echo "Looks for: inputs/${PROG}.<i>.<task>.in and outputs/${PROG}.<i>.<task>.out (i = 1,2,3...)"
}

if [[ $# -ne 1 ]]; then
  usage
  exit 2
fi

TASK="$1"

if [[ "$TASK" != "task1" && "$TASK" != "task2" ]]; then
  echo "ERROR: task must be 'task1' or 'task2' (got: '$TASK')" >&2
  usage
  exit 2
fi

if [[ ! -e "$PROG.class" ]]; then
  echo "ERROR: $PROG.class does not exist. Have you compiled it with make or javac?" >&2
  exit 1
fi

cleanup() {
  if [[ -n "${out:-}" && -e "${out:-}" ]]; then
    rm -f "$out"
  fi
}
trap cleanup INT

num_failed=0
i=1
found_any=0

while true; do
  in_file="inputs/${PROG}.${i}.${TASK}.in"
  exp_file="outputs/${PROG}.${i}.${TASK}.out"

  if [[ ! -e "$in_file" ]]; then
    break
  fi

  found_any=1

  if [[ ! -e "$exp_file" ]]; then
    echo "ERROR: missing expected output file: $exp_file" >&2
    exit 1
  fi

  # create temp output file (portable)
  if [[ "$(uname)" == "Darwin" ]]; then
    out="$(mktemp -t "${PROG}.XXXXXX")"
  else
    out="$(mktemp --suffix=".${PROG}")"
  fi

  # Run program, enforce limits
  if ! timeout 5s java "$PROG" < "$in_file" | head -c 50000000 > "$out"; then
    echo "test $i: ERROR (program failed or timed out)"
    num_failed=$((num_failed + 1))
    rm -f "$out"
    i=$((i + 1))
    continue
  fi

  if [ "$TASK" == "task1" ]; then
	description="${description1[$i]:-no description}"
  else
	description="${description2[$i]:-no description}"
  fi

  if diff -bB "$out" "$exp_file" >/dev/null; then
    echo "test $i: $description: passed"
  else
    echo "test $i: $description: failed"
    num_failed=$((num_failed + 1))
  fi

  rm -f "$out"
  i=$((i + 1))
done

if [[ "$found_any" -eq 0 ]]; then
  echo "ERROR: no test cases found. Expected at least: inputs/${PROG}.1.${TASK}.in" >&2
  exit 1
fi

if [[ "$num_failed" -eq 0 ]]; then
  echo "$PROG ($TASK): passed everything 🎉"
else
  echo "$PROG: failed $num_failed test(s)"
  exit 1
fi
