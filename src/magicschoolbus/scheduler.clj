(ns magicschoolbus.scheduler)

(defn set-interval [ms f & args]
  (future (while true (do (Thread/sleep ms) (apply f args)))))