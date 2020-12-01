(ns aoc
  (:require [clojure.string :as s]))

(def day1Input (map #(Integer/parseInt %) (s/split-lines (slurp "inputs/day1.txt")) ))

(defn findSum [sum list]
  (loop [start (first list) left (rest list)]
    (let [found (filter #(= sum (+ start %)) left)]
      (if (> (count found) 0)
        [start (first found) (* start (first found))]
        (if (empty? left) [] (recur (first left) (rest left)))))))

(defn drop-nth [n coll]
  (concat
    (take n coll)
    (drop (inc n) coll)))

(defn day1Part1 [sum list]
  (last (findSum sum list)))

(defn day1Part2 [sum list]
  (loop [currSum (- sum (first list)) currIndex 0]
    (let [found (findSum currSum (drop-nth currIndex list))]
      (if (> (count found) 0)
        (* (nth list currIndex) (last found))
        (if (= currIndex (inc (count list))) [] (recur (- sum (nth list (inc currIndex))) (inc currIndex)))))))

(defn runDays []
  (print "Day 1 Part 1:" (day1Part1 2020 day1Input) "\n")
  (print "Day 1 Part 2:" (day1Part2 2020 day1Input) "\n")
  )

(defn -main [] (runDays))