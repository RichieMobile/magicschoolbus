(ns magicschoolbus.school-bus
  (:require [clojure.java.io :as io]))

(defn move
  "Moves file at `src` to `dest`"
  [src dest]
  (let [src-file (io/file src) dest-file (io/file dest)]
    (io/copy src-file dest-file)
    (io/delete-file src-file)))
