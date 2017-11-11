(ns magicschoolbus.driver
  (:import java.io.File)
  (:require [clojure.java.io :as io]))

(defn match?
  "Determines if `regex` matches `string` input"
  [regex string]
  (boolean (re-matches regex string)))

(defn read-files [directory]
  (file-seq (io/file directory)))

(defn file-name-matches? [regex filename]
  (match? regex (.getFileName filename)))

(defn pickup
  "Read all files in `directory` that matches `regex`.
  `directory` and `regex` are of type `string`"
  [directory regex]
  (->> directory
       read-files
       (filter #(.isFile %))
       (filter #(file-name-matches? regex %))
       (mapv #(.getAbsolutePath %))))
