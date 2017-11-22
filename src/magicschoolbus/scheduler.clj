(ns magicschoolbus.scheduler
  (:require [clojure.tools.logging :as log]))

(def started (atom true))

(defn- set-interval
  "`ms` time in miliseconds, `f` is a function,
  and `args` is a list of arguements for `f`."
  [ms f args]
  (future
    (while (true? @started)
      (do
        (Thread/sleep ms)
        (apply f args)))))

(defn schedule [ms f & args]
  (log/info "Scheduling task with interval: " ms
            "Function: " f
            "Args: " args)
  (reset! started true)
  (set-interval ms f args))

(defn stop []
  (log/info "Stopping scheduler.")
  (reset! started false))
