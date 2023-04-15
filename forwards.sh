git checkout .
git clean -fd
git checkout $(git log --reverse --pretty=%H --ancestry-path HEAD..main | head -n 1)