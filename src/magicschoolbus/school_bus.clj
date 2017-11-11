(ns magicschoolbus.school-bus
  (:require [clojure.java.io :as io]))

(defn move
  "Moves file at `src` to `dest`"
  [src dest]
  (io/copy (io/file src) (io/file dest)))
