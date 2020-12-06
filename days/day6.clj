(ns days.day6
  (:require [clojure.string :as s]))

(def day6Input (map #(s/replace % #"\r\n" '"") (s/split (slurp "inputs/day6.txt") #"\r\n\r\n")))

(defn numFields [report]
  (count (frequencies report)))

(def day6Input2 (map #(s/split % #"\r\n") (s/split (slurp "inputs/day6.txt") #"\r\n\r\n")))

(defn numSimilarFields [report]
  (count (reduce #(select-keys %1 (keys %2)) (map frequencies report))))

(defn day6Part1 [] (reduce + (map numFields day6Input)))
(defn day6Part2 [] (reduce + (map numSimilarFields day6Input2)))