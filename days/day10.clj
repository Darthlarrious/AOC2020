(ns days.day10
  (:require [clojure.string :as s]
            [clojure.math.combinatorics :as m]
            [clojure.core.reducers :as reducers]))


(def day10Input (conj (sort (map #(Integer/parseInt %) (s/split-lines (slurp "inputs/day10.txt")))) 0))

(def gaps (map #(- (nth day10Input (inc %)) (nth day10Input %)) (range (dec (count day10Input)))))

(def deviceVoltage (+ 3 (apply max day10Input)))

(def day10Input2 (sort (conj day10Input deviceVoltage)))

(defn day10
  [voltages]
    (let [ways (reduce #(assoc %1 %2 (->> (range (- %2 3) (inc %2))
                                          (keep %1)
                                          (reduce +)))
                       {(first voltages) 1}
                       (rest voltages))]
      (ways (last voltages))))


(defn day10Part1 [] (let [nums (frequencies gaps)]
                      (* (nums 1) (inc (nums 3)))))
(defn day10Part2 [] (day10 day10Input2))