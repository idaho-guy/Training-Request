#!/usr/bin/env bash
ps -ef | grep bootRun | awk '{print $2}' | xargs kill