(ns magicschoolbus.config-parser-test
  (:require [clojure.test :refer :all]))

(deftest config-is-parsed-by-config-parser-test
  (testing "Config is properly parsed by parser"
    (parser/parse "config.json")))
