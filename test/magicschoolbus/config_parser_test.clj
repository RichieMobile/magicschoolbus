(ns magicschoolbus.config-parser-test
  (:require [clojure.test :refer :all]
            [magicschoolbus.config-parser :as parser]))

(defn getStops []
  (get (parser/parse "config.json") "stops"))

(deftest config-is-parsed-by-config-parser-test
  (testing "Config is properly parsed by parser"
    (let [stop (first (getStops))]
      (is (= (get stop "pattern") "fu.*"))
      (is (= (get stop "source") "/Users/ricky/Projects/Test/Orig"))
      (is (= (get stop "destination") "/Users/ricky/Projects/Test/Copy")))))
