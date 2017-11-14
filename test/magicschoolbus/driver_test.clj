(ns magicschoolbus.driver-test
  (:import java.io.File)
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [magicschoolbus.driver :as driver]))

(def orig-dir "test/magicschoolbus/workspace/orig")
(def orig-path (str orig-dir "/orig.txt"))
(def orig-path-two (str orig-dir "/orig2.txt"))
(def fake-path (str orig-dir "/fake.txt"))

(def copy-dir "test/magicschoolbus/workspace/copy")

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

(deftest driver-regex-matches-file-name-test
  (testing "Driver file name with matching regex returns true"
    (is (true? (driver/match? #"or.*" "orig.txt")))))

(deftest driver-reads-all-matching-files-from-directory-test
  (testing "Driver gets all the file names from a given directory"
    (is (pos? (compare [orig-path orig-path-two]
                       (driver/pickup orig-dir #"or.*"))))))

(deftest driver-drops-off-files-test
  (testing "Driver moves/deletes files after pickup"
    (driver/dropoff copy-dir [orig-path orig-path-two])
    (is (true? (.exists (io/file (str copy-dir "/orig.txt")))))
    (is (true? (.exists (io/file (str copy-dir "/orig2.txt")))))
    (is (false? (.exists (io/file (str orig-dir "/orig.txt")))))
    (is (false? (.exists (io/file (str orig-dir "/orig2.txt")))))))

(deftest driver-picks-up-and-drops-off-files-test
  (testing "Driver drops off files it picks up"
    (driver/pickup-and-dropoff #"or.*" orig-dir copy-dir)
    (is (true? (.exists (io/file (str copy-dir "/orig.txt")))))
    (is (true? (.exists (io/file (str copy-dir "/orig2.txt")))))
    (is (false? (.exists (io/file (str orig-dir "/orig.txt")))))
    (is (false? (.exists (io/file (str orig-dir "/orig2.txt")))))))