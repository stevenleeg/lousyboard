# lousyboard
A lousy message board.

## Running
You'll want to start off by running migrations using `lein migrate`. Once the
migrations have been applied you can start the web server with:

    lein start

You will also want to compile/watch sass files, otherwise things will look 
pretty ugly:

    lein watch-sass

