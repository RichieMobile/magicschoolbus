(ns magicschoolbus.school-bus-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [magicschoolbus.school-bus :as bus]))

(deftest school-bus-test
  (testing "School bus moves files from one stop to another"
    (bus/move "test/magicschoolbus/workspace/orig/orig.txt"
              "test/magicschoolbus/workspace/orig/copy.txt")
    (is (io/as-file "test/magicschoolbus/workspace/orig/copy.txt"))))
