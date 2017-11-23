lein uberjar

mkdir ./target/uberjar/magicschoolbus
mkdir ./target/uberjar/magicschoolbus/logs

cp ./target/uberjar/magicschoo*stand* ./target/uberjar/magicschoolbus
cp ./config.json ./target/uberjar/magicschoolbus

cd ./target/uberjar && tar -czvf magicschoolbus.tar.gz magicschoolbus
