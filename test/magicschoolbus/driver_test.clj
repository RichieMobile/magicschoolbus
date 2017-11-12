(ns magicschoolbus.driver-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [magicschoolbus.driver :as driver]))

(def orig-dir "test/magicschoolbus/workspace/orig")
(def orig-path (str orig-dir "/orig.txt"))
(def orig-path-two (str orig-dir "/orig2.txt"))
(def fake-path (str orig-dir "/fake.txt"))

(defn write-file []
  (spit orig-path "hello")
  (spit orig-path-two "hello again")
  (spit fake-path "fake"))

(defn delete [file]
  (if (.exists (io/file file))
    (io/delete-file file)))

(defn delete-file []
  (delete orig-path)
  (delete orig-path-two)
  (delete fake-path))

(defn setup [f]
  (write-file)
  (f)
  (delete-file))

(use-fixtures :each setup)

(deftest driver-regex-matches-file-name
  (testing "Driver file name with matching regex returns true"
    (is (true? (driver/match? #"or.*" "orig.txt")))))

(deftest driver-reads-all-matching-files-from-directory
  (testing "Driver gets all the file names from a given directory"
    (is (pos? (compare [orig-path orig-path-two]
                       (driver/pickup orig-dir #"or.*"))))))
