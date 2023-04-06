#!/usr/bin/env bash

set -e

./gradlew :app:assembleInternalDebug :app:assembleInternalDebugAndroidTest -PisTestCoverageEnabled --stacktrace
