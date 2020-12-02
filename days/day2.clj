(ns days.day2
  (:require [clojure.string :as s]))

(def day2Input (s/split-lines (slurp "inputs/day2.txt")))

(defn buildRegex [string-input]
  (let [pieces (s/split string-input #" ")
        string (last pieces)
        min (first (s/split (first pieces) #"\-"))
        max (last (s/split (first pieces) #"\-"))]
    (re-pattern (str '"^([^" string '"]*" string '"){" min '"," max '"}[^" string '"]*$"))  ))

(defn validPassword? [item]
  (let [pieces (s/split item #":")]
    (not (nil? (re-matches (buildRegex (first pieces)) (last pieces)))) ))

(defn validPassword2? [string-input]
  (let [initial (s/split string-input #":")
        pieces (s/split (first initial) #" ")
        character (.charAt (last pieces) 0)
        firstIndex (Integer/parseInt (first (s/split (first pieces) #"\-")))
        secondIndex (Integer/parseInt (last (s/split (first pieces) #"\-")))]
    (and
      (or                                                   ; XOR only one of the characters can match
        (= character (.charAt (last initial) firstIndex))
        (= character (.charAt (last initial) secondIndex)))
      (not
        (=
          (.charAt (last initial) firstIndex)
          (.charAt (last initial) secondIndex))))))

(defn day2Part1 [] (count (filter validPassword? day2Input)))
(defn day2Part2 [] (count (filter validPassword2? day2Input)))