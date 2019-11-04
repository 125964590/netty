#!/bin/bash

YAML=$1
VERSION=$2

sed -e 's/imageVersion/'${VERSION}'/g' ${YAML} > ${YAML}_tpm.yaml

kubectl apply -f ${YAML}_tpm.yaml

rm -rf ${YAML} > ${YAML}_tpm.yaml