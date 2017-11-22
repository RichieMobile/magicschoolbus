(ns magicschoolbus.core-test
  (:require [clojure.test :refer :all]
            [magicschoolbus.core :refer :all]
            [clojure.java.io :as io]))

(def fun_orig-dir "/Users/ricky/Projects/Test/Orig")
(def fun_orig-path (str fun_orig-dir "/fun_orig.txt"))
(def fun_orig-path-two (str fun_orig-dir "/fun_orig2.txt"))
(def fake-path (str fun_orig-dir "/fake.txt"))

(def copy-dir "/Users/ricky/Projects/Test/Copy")

(defn write-file []
  (spit fun_orig-path "hello")
  (spit fun_orig-path-two "hello again")
  (spit fake-path "fake"))

(defn delete [file]
  (if (.exists (io/file file))
    (io/delete-file file)))

(defn delete-file []
  (delete fun_orig-path)
  (delete fun_orig-path-two)
  (delete fake-path))

(defn setup [f]
  (write-file)
  (f)
  (delete-file))

(use-fixtures :each setup)

(deftest get-stops-test
  (testing "Get stops returns all the stops in the config file"
    (println (str (System/getProperty "user.dir") "/config.json"))
    (let [stops
          (first
            (getStops (str (System/getProperty "user.dir") "/config.json")))]
      (is (= (get stops "pattern") "fu.*"))
      (is (= (get stops "source") "/Users/ricky/Projects/Test/Orig"))
      (is (= (get stops "destination") "/Users/ricky/Projects/Test/Copy")))))

(deftest main-starts-up-the-bus
  (testing "Main method loads config and starts picking up files"
    (let [config (str (System/getProperty "user.dir") "/config.json")]
      (schedule-stops-with-config config))
    (Thread/sleep 2000)
    (is (true? (.exists (io/file (str copy-dir "/fun_orig.txt")))))
    (is (true? (.exists (io/file (str copy-dir "/fun_orig2.txt")))))
    (is (false? (.exists (io/file (str fun_orig-dir "/fun_orig.txt")))))
    (is (false? (.exists (io/file (str fun_orig-dir "/fun_orig2.txt")))))))
