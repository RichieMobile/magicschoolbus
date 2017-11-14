(ns magicschoolbus.scheduler)

(defn schedule
  "Call f with args every ms. First call will be after ms"
  [ms f & args]

  (future
    (doseq [f (repeatedly #(apply f args))]
      (println "Running scheduled function")
      (Thread/sleep ms)
      (f))))

(defn set-interval [ms f & args]
  (future (while true (do (Thread/sleep ms) (apply f args)))))