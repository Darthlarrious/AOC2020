(ns days.day7
  (:require [clojure.string :as s]))

(def day7Input (map #(s/replace % #"\." "") (s/split-lines (slurp "inputs/day7.txt")) ))

(defn extractRule [rule]
  {:subject (s/replace (first (s/split rule #" contain ")) #"s$" "")
   :contents (vec (map #(s/replace % #"s$" "") (map #(subs % 2) (s/split (last (s/split rule #" contain ")) #", "))))
   :contentNumbers (vec (map #(try (Integer/parseInt (subs % 0 1)) (catch NumberFormatException e 0)) (s/split (last (s/split rule #" contain ")) #", ")))})

(defn containsBag? [input bag]
  (let [rule (extractRule input)
        contents (:contents rule)]
    (reduce #(or %1 %2) (map #(not (nil? (re-matches (re-pattern (str bag "[s]*")) %))) contents))))

(defn containsList [bag]
  (map #(s/replace % #"s$" "") (map #(:subject (extractRule %)) (filter #(containsBag? % bag) day7Input))))

(def allBags
  (set (map #(s/replace % #"s$" "") (flatten (map #(list (:subject %) (:contents %)) (map extractRule day7Input))))))

(def containsRules (map #(vec (containsList %)) allBags))

(def rules (map extractRule day7Input))

(defn calcBags [startPoint amount]
  (let [
        currBag (filter
                  #(= 0 (compare (:subject %) startPoint))
                  rules)
        contents (zipmap (:contents (first currBag)) (:contentNumbers (first currBag)))]
   (if (nil? currBag)
     amount
     (*
       amount
       (inc (reduce + (map #(calcBags (first %) (second %)) contents)))))))


(defn day7Part1 []  )
(defn day7Part2 [] (dec (calcBags "shiny gold bag" 1)))