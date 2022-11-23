#!/usr/bin/env bash

CURRENT_VERSION=`xmllint --xpath '/*[local-name()="project"]/*[local-name()="version"]/text()' pom.xml`

if [[ $CURRENT_VERSION == *-SNAPSHOT ]]; then
	NEW_VERSION=${CURRENT_VERSION%'-SNAPSHOT'}
	NEXT_VERSION=`bash ci/semver.sh -p $NEW_VERSION`
	NEXT_SNAPSHOT="$NEXT_VERSION-SNAPSHOT"
	echo "perform release of $NEW_VERSION from $CURRENT_VERSION and set next develop version $NEXT_SNAPSHOT"

	mvn versions:set -DnewVersion=$NEW_VERSION versions:commit --no-transfer-progress

 	echo "commit new release version"
	git commit -a -m "Release $NEW_VERSION: set main to new release version"

	echo "Update version in README.md"
	sed -i -e "s|<version>[0-9A-Za-z._-]\{1,\}</version>|<version>$NEW_VERSION</version>|g" README.md && rm -f README.md-e
	git commit -a -m "Release $NEW_VERSION: Update README.md"

	echo "create tag for new release"
	git tag -a $NEW_VERSION -m "Release $NEW_VERSION: tag release"

	echo "merge main back to develop"
	git fetch --all
	git checkout develop
	git merge main

	mvn versions:set -DnewVersion=$NEXT_SNAPSHOT versions:commit --no-transfer-progress

	echo "commit new snapshot version"
	git commit -a -m "Release $NEW_VERSION: set develop to next development version $NEXT_SNAPSHOT"

	git push --all
	git push --tags
fi
