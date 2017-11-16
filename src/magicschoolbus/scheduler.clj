(ns magicschoolbus.scheduler
  (:require [clojure.tools.logging :as log]))

(def started (atom false))

(defn- set-interval [ms f & args]
  (future (while (true? @started) (do (Thread/sleep ms) (apply f args)))))

(defn schedule [ms f & args]
  (log/info "Scheduling task with interval: " ms)
  (reset! started true)
  (set-interval ms f args))

(defn stop []
  (log/info "Stopping scheduler.")
  (reset! started false))
