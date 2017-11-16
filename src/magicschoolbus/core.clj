(ns magicschoolbus.core
  (:require [magicschoolbus.scheduler :as scheduler]
            [magicschoolbus.driver :as driver]
            [magicschoolbus.config-parser :as parser]
            [clojure.tools.logging :as log])
  (:gen-class))

(defn getStops [config]
  (get (parser/parse config) "stops"))

(defn scheduleStops [stops]
  (doseq [stop stops]
    (scheduler/schedule 1000
                        driver/pickup-and-dropoff
                        (re-pattern (get stop "pattern"))
                        (get stop "source")
                        (get stop "destination"))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (log/info "Take chances, make mistakes, and get messy!")
  (->> "config.json"
       (getStops)
       (scheduleStops)))
