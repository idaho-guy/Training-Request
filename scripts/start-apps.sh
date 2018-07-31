#!/usr/bin/env bash
mkdir logs
cd ../discovery
echo "Discovery server starting"
gradle bootRun > ../scripts/logs/discovery.log &
sleep 10
cd ../employee
echo "Employee service starting"
gradle bootRun > ../scripts/logs/employee.log &
cd ../training-request
echo "Training request service starting"
gradle bootRun > ../scripts/logs/training-request.log &
cd ../api-gateway
echo "API gateway starting"
gradle bootRun > ../scripts/logs/api-gateway.log &
cd ../scripts
