lein uberjar
cp ./config.json ./target/uberjar/
mkdir ./target/uberjar/logs
cd ./target/uberjar && tar -czvf magicschoolbus.tar.gz magicschoo*stand* config.json logs
