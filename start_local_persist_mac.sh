#!/usr/bin/env bash
docker run -d -v /run/docker/plugins/:/run/docker/plugins/ -v /Users/rlozanov/:/var/lib/docker/plugin-data/ -v /Users/rlozanov/:/Users/rlozanov/ cwspear/docker-local-persist-volume-plugin