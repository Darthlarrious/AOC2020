(ns days.day8
  (:require [clojure.string :as s]))

(def day8Input (s/split-lines (slurp "inputs/day8.txt")))

(defn parseInstruction [instr pointer accumulator]
  (let [[oper val] (s/split instr #" ")
        sign (subs val 0 1)
        value (try (Integer/parseInt (subs val 1)) (catch NumberFormatException e 0))]
    (cond
      (= oper "acc")
      [(inc pointer) (if (= sign "+")
                        (+ value accumulator)
                        (- accumulator value))]
      (= oper "jmp")
      [(if (= sign "+")
         (+ value pointer)
         (- pointer value))
        accumulator]
      (= oper "nop")
      [(inc pointer) accumulator]
      )))

(defn runProgram1 [instructions]
  (loop [pointer 0 accumulator 0 visited '[]]
    (if (some #(= pointer %) visited)
      accumulator
      (let [[newPointer newAccumulator] (parseInstruction (nth instructions pointer) pointer accumulator)]
        (recur newPointer newAccumulator (conj visited pointer))))))

(defn day8Part1 [] (runProgram1 day8Input))
(defn day8Part2 [] nil)