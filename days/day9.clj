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

(defn findSet [target]
  (loop [currentStart 0
         current 1
         currentVal (reduce + (subvec day9Input currentStart current))]
    (cond
      (= currentVal target)
      (subvec day9Input currentStart current)
      (> currentVal target)
      (recur (inc currentStart) (+ 2 currentStart) (reduce + (subvec day9Input (inc currentStart) (+ 2 currentStart))))
      true
      (recur currentStart (inc current) (reduce + (subvec day9Input currentStart (inc current)))))))

(defn getWeakness [target]
  (let [set (findSet target)
        min (apply min set)
        max (apply max set)]
    (+ min max)))

(defn day9Part1 [] (last (first (findInvalid))))
(defn day9Part2 [] (getWeakness (last (first (findInvalid)))))