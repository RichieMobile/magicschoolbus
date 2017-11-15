lein uberjar
cp ./config.json ./target/uberjar/
cd ./target/uberjar && tar -czvf magicschoolbus.tar.gz magicschoo* config.json