#!/bin/bash

table="s109524012"

./put_and_get.sh $table > "$table.output"
cat "$table.output"
