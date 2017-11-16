(ns magicschoolbus.scheduler)

(def started (atom false))

(defn- set-interval [ms f & args]
  (future (while (true? @started) (do (Thread/sleep ms) (apply f args)))))

(defn schedule [ms f & args]
  (reset! started true)
  (set-interval ms f args))

(defn stop [] (reset! started false))
