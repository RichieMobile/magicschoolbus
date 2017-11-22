(ns magicschoolbus.driver
  (:import java.io.File)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.tools.logging :as log]
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
  `directory` is `clojure.string` and `regex` is `clojure.regex`"
  [directory regex]
  (log/info (str "Picking up files in " directory " with pattern " regex))
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
  (doseq [file files]
    (bus/move file (str destination "/" (getName file)))))

(defn pickup-and-dropoff
  "Picks up files in `src-dir` matching `regex` and drops them off in `dest-dir`.
  `src-dir` and `dest-dir` are of type `clojure.string` and `regex` of type
  `clojure.regex`"
  [regex src-dir dest-dir]
  (log/info (str "Picking up files in: " src-dir
            " with pattern: " regex
            " Dropping off files in: " dest-dir))
  (->> regex
       (pickup src-dir)
       (dropoff dest-dir)))