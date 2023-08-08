#!/usr/bin/env bash
set -e
set -x
SERVICE_NAME=redis-api
GCLOUD_PROJECT=redis-api
IMAGE_NAME=gcr.io/$GCLOUD_PROJECT/$SERVICE_NAME

mvn clean package
gcloud builds submit --tag $IMAGE_NAME .
gcloud run deploy $SERVICE_NAME \
--ingress internal \
--allow-unauthenticated \
--image $IMAGE_NAME \
--platform managed --region us-central1 \
--cpu=1 --memory=512Mi \
--vpc-connector cloudrun-to-db \
--vpc-egress private-ranges-only