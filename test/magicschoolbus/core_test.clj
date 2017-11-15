(ns magicschoolbus.core-test
  (:require [clojure.test :refer :all]
            [magicschoolbus.core :refer :all]))

(deftest get-stops-test
  (testing "Get stops returns all the stops in the config file"
    (println (str (System/getProperty "user.dir") "/config.json"))
    (let [stops
          (first
            (getStops (str (System/getProperty "user.dir") "/config.json")))]
      (is (= (get stops "pattern") "fu.*"))
      (is (= (get stops "source") "/Users/ricky/Projects/Test/Orig"))
      (is (= (get stops "destination") "/Users/ricky/Projects/Test/Copy")))))
