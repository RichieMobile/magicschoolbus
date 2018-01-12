(ns magicschoolbus.core
  (:require [magicschoolbus.scheduler :as scheduler]
            [magicschoolbus.driver :as driver]
            [magicschoolbus.config-parser :as parser]
            [clojure.tools.logging :as log])
  (:gen-class))

(defn getStops [config]
  (get (parser/parse config) "stops"))

(defn schedule-stops [stops]
  (doseq [stop stops]
    (log/info (str "Stop: " stop))
    (scheduler/schedule 1000
                        driver/pickup-and-dropoff
                        (re-pattern (get stop "pattern"))
                        (get stop "source")
                        (get stop "destination"))))

(defn schedule-stops-with-config [config]
    (->> config
       (getStops)
       (schedule-stops)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (log/info "Take chances, make mistakes, and get messy!")
  (schedule-stops-with-config "config.json"))

