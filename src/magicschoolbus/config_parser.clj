(ns magicschoolbus.config-parser
  (:require [clojure.data.json :as json]
            [clojure.tools.logging :as log]))

(defn parse
  "`config` is a file path to the configuration json file.
  Returns a hashmap matching the config."
  [config]
  (log/info (str "Parsing config at path: " config))
  (json/read-str (slurp config)))
