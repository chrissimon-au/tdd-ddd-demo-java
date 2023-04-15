git checkout .
git clean -fd
git checkout $(git log --pretty=%H --parents -n 2 | tail -n 1)