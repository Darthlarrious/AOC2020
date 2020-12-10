(ns days.day10
  (:require [clojure.string :as s]))


(def day10Input (conj (sort (map #(Integer/parseInt %) (s/split-lines (slurp "inputs/day10.txt")))) 0))

(def test (map #(- (nth day10Input (inc %)) (nth day10Input %)) (range (dec (count day10Input)))))
(def gaps (frequencies (map #(- (nth day10Input (inc %)) (nth day10Input %)) (range (dec (count day10Input))))))

(defn day10Part1 [] (* (gaps 1) (inc (gaps 3))))
(defn day10Part2 [] nil)