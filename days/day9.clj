(ns days.day9
  (:require [clojure.string :as s]))

(def day9Input (vec (map #(Long/parseLong %) (s/split-lines (slurp "inputs/day9.txt")))))

(defn validate [item preamble]
  (filter
    (fn [item2]
      (not
        (nil?
          (some
            #(=
               (+ % item2)
               item)
            preamble))))
    preamble))

(defn findInvalid []
  (filter
    #(empty? (second %))
    (map-indexed
      (fn [id item] [id
                     (validate item (subvec day9Input id (+ id 25)))
                     item])
      (subvec day9Input 25))))



(defn day9Part1 [] (last (first (findInvalid))))
(defn day9Part2 [] nil)