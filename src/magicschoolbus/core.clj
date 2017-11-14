(ns magicschoolbus.core
  (:require [magicschoolbus.scheduler :as scheduler]
            [magicschoolbus.driver :as driver])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Take chances, make mistakes, and get messy!")
  (scheduler/set-interval 1000
                          driver/pickup-and-dropoff
                          #"fu.*"
                          "/Users/ricky/Projects/Test/Orig"
                          "/Users/ricky/Projects/Test/Copy"))
