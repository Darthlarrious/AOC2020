(ns days.day8
  (:require [clojure.string :as s]))

(def day8Input (s/split-lines (slurp "inputs/day8.txt")))
(def day8Input2 (s/split-lines (slurp "inputs/day8.txt")))

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
      ["p" accumulator pointer]
      (if (>= pointer (count instructions))
        ["d" accumulator pointer]
        (let [[newPointer newAccumulator] (parseInstruction (nth instructions pointer) pointer accumulator)]
          (recur newPointer newAccumulator (conj visited pointer)))))))

(defn swapOp [op] (if (= (subs op 0 3) "jmp") (s/replace op #"jmp" "nop") (s/replace op #"nop" "jmp")))

(defn testProgram [instructions]
  (let [
        indexedInstructions (map-indexed (fn [id itm] [id itm]) instructions)
        filteredInstructions (filter #(re-matches #"(jmp).*|(nop).*" (second %)) indexedInstructions)]
    (loop [attempted '[] current 0]
      (if (= current (count filteredInstructions))
        ["couldn't find" attempted]
        (let [currentOp (swapOp (second (nth filteredInstructions current)))  result (runProgram1 (assoc-in instructions [(first (nth filteredInstructions current))] currentOp))]
          (if (= (first result) "p")
            (recur (conj attempted current) (inc current))
            [result current]))))))



(defn day8Part1 [] (second (runProgram1 day8Input)))
(defn day8Part2 [] (second (first (testProgram day8Input2))))