(ns magicschoolbus.school-bus-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [magicschoolbus.school-bus :as bus]))

(def orig-path "test/magicschoolbus/workspace/orig/orig.txt")

(def copy-path "test/magicschoolbus/workspace/copy/copy.txt")

(defn write-file []
  (spit orig-path "hello"))

(defn delete-file []
  (if (.exists (io/file copy-path))
    (io/delete-file copy-path)))

(defn setup [f]
  (write-file)
  (f)
  (delete-file))

(use-fixtures :each setup)

(deftest school-bus-test
  (testing "School bus moves files from one stop to another"
    (bus/move orig-path copy-path)
    (is (io/as-file copy-path))
    (is (false? (.exists (io/as-file orig-path))))))
