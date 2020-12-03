(ns days.day3
  (:require [clojure.string :as s]))

(def day3Input (s/split-lines (slurp "inputs/day3.txt")))
(defn isTreeAt [map x y]
  (if (= \# (nth (nth map (mod y (count map))) (mod x (count (nth map 0)))))
    1
    0))

(defn countTreesAlgo [map incX incY]
  (let [targetY (count map)]
    (loop [currX 0 currY 0 treeCount 0]
      (if (> (inc currY) targetY)
        treeCount
        (recur (+ incX currX) (+ incY currY) (+ (isTreeAt map currX currY) treeCount)))
      )))

(def addAlgos
  (*
    (countTreesAlgo day3Input 1 1)
    (countTreesAlgo day3Input 3 1)
    (countTreesAlgo day3Input 5 1)
    (countTreesAlgo day3Input 7 1)
    (countTreesAlgo day3Input 1 2)))

(defn day3Part1 [] (countTreesAlgo day3Input 3 1))
(defn day3Part2 [] addAlgos)