(ns magicschoolbus.driver
  (:import java.io.File)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [magicschoolbus.school-bus :as bus]))

(defn match?
  "Determines if `regex` matches `string` input"
  [regex string]
  (boolean (re-matches regex string)))

(defn read-files [directory]
  (file-seq (io/file directory)))

(defn file-name-matches? [regex filename]
  (match? regex (.getName (io/file filename))))

(def filename-regex #"^\\\\(.+\\\\)*(.+)\\.(.+)$\n")

(defn pickup
  "Read all files in `directory` that matches `regex`.
  `directory` and `regex` are of type `string`"
  [directory regex]
  (->> directory
       read-files
       (filter #(.isFile %))
       (filter #(file-name-matches? regex %))
       (mapv #(.getAbsolutePath %))))

(defn getName [file]
  (.getName (io/file file)))

(defn dropoff
  "Dropping off `files` at `destination`."
  [destination files]
  (doseq [file files] bus/move file (str destination (getName file))))