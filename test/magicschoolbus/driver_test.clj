(ns magicschoolbus.driver-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [magicschoolbus.driver :as driver]))

(def orig-dir "test/magicschoolbus/workspace/orig")
(def orig-path (str orig-dir "/orig.txt"))
(def orig-path-two (str orig-dir "/orig2.txt"))

(defn write-file []
  (spit orig-path "hello")
  (spit orig-path-two "hello again"))

(defn delete-file []
  (if (.exists (io/file orig-path))
    (io/delete-file orig-path))
  (if (.exists (io/file orig-path-two))
    (io/delete-file orig-path-two)))

(defn setup [f]
  (write-file)
  (f)
  (delete-file))

(use-fixtures :each setup)

(deftest driver-regex-matches-file-name
  (testing "Driver file name with matching regex returns true"
  (is (true? (driver/match? #"or.*" "orig.txt")))))

(deftest driver-reads-all-files-from-directory
  (testing "Driver gets all the file names from a given directory"
    (is ([orig-path orig-path-two]
          (driver/pickup orig-dir "or.*")))))
