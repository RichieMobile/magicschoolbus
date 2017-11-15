(ns magicschoolbus.config-parser
  (:require [clojure.data.json :as json]))

(defn parse
  "`config` is a file path to the configuration json file.
  Returns a hashmap matching the config."
  [config]
  (json/read-str (slurp config)))
